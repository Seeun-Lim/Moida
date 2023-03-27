package com.ssafy.moida.api.controller;

import com.ssafy.moida.api.request.CreateDonationReqDto;
import com.ssafy.moida.api.request.CreateProjectReqDto;
import com.ssafy.moida.api.response.GetProjectDetailResDto;
import com.ssafy.moida.api.response.GetProjectResDto;
import com.ssafy.moida.auth.PrincipalDetails;
import com.ssafy.moida.model.project.Project;
import com.ssafy.moida.model.project.ProjectDonation;
import com.ssafy.moida.model.project.ProjectVolunteer;
import com.ssafy.moida.model.project.VolunteerDateInfo;
import com.ssafy.moida.model.user.Users;
import com.ssafy.moida.service.project.ProjectDonationService;
import com.ssafy.moida.service.project.ProjectPictureService;
import com.ssafy.moida.service.project.ProjectService;
import com.ssafy.moida.service.project.ProjectVolunteerService;
import com.ssafy.moida.service.user.UserDonationService;
import com.ssafy.moida.service.user.UserService;
import com.ssafy.moida.utils.DtoValidationUtils;
import com.ssafy.moida.utils.TokenUtils;
import com.ssafy.moida.utils.error.ErrorCode;
import com.ssafy.moida.utils.exception.CustomException;
import io.swagger.v3.oas.annotations.*;
import java.io.IOException;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * [세은] 프로젝트 컨트롤러
 */
@Tag(name="프로젝트")
@RestController
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService projectService;
    private final UserService userService;
    private final ProjectVolunteerService projectVolunteerService;
    private final ProjectDonationService projectDonationService;
    private final ProjectPictureService projectPictureService;
    private final UserDonationService userDonationService;
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private DtoValidationUtils dtoValidationUtils;

    public ProjectController(ProjectService projectService, UserService userService,
                             ProjectVolunteerService projectVolunteerService, ProjectDonationService projectDonationService, ProjectPictureService projectPictureService, UserDonationService userDonationService){
        this.projectService = projectService;
        this.userService = userService;
        this.projectVolunteerService = projectVolunteerService;
        this.projectDonationService = projectDonationService;
        this.projectPictureService = projectPictureService;
        this.userDonationService = userDonationService;
    }

    @Transactional
    @Operation(summary = "[관리자] 프로젝트 생성", description = "관리자가 새 프로젝트를 생성합니다.")
    @PostMapping(consumes = {
        MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE
    })
    public ResponseEntity<?> createProject(
        @RequestPart(value = "info", required = true) CreateProjectReqDto createProjectReqDto,
        @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail,
        @RequestPart(value = "files", required = false) List<MultipartFile> fileList,
        @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        // 토큰 유효성 검증 및 관리자 확인
        tokenUtils.validateAdminTokenAndGetUser(principalDetails, true);

        // DTO NOT NULL 검증
        dtoValidationUtils.validateCreateProjectReqDto(createProjectReqDto);

        // 기부 데이터베이스에 저장
        ProjectDonation projectDonation = projectDonationService.save(createProjectReqDto.getDonationReqDto(),
            createProjectReqDto.getProjectReqDto().getPointPerMoi());

        // 봉사 데이터베이스에 저장
        ProjectVolunteer projectVolunteer = projectVolunteerService.saveProjectVolunteer(createProjectReqDto.getVolunteerReqDto());

        // 봉사 데이터베이스 저장
        Project project = projectService.save(createProjectReqDto.getProjectReqDto(), projectVolunteer, projectDonation, thumbnail);

        // 봉사일시 데이터베이스 저장
        projectVolunteerService.saveVolunteerDateInfo(project);

        // 프로젝트 상세설명이 들어왔을 때만, 사진 데이터베이스에 저장
        if(fileList != null && fileList.size() > 0){
            try {
                projectPictureService.save(fileList, project);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new ResponseEntity<>("프로젝트 생성 완료", HttpStatus.OK);
    }

    @Operation(summary = "프로젝트 정보 조회", description = "메인페이지에서 조회할 프로젝트의 가벼운 정보를 조회합니다.")
    @GetMapping
    public ResponseEntity<List<GetProjectResDto>> getProject(){
        List<GetProjectResDto> getProjectResDtoList = projectService.getProject();
        return new ResponseEntity<>(getProjectResDtoList, HttpStatus.OK);
    }

    @Operation(summary = "프로젝트 정보 상세 조회", description = "프로젝트 상세 페이지 정보를 조회합니다.")
    @GetMapping("/{projectid}")
    public ResponseEntity<GetProjectDetailResDto> getProjectDetail(@PathVariable(value = "projectid") int projectId){
        GetProjectDetailResDto getProjectDetailResDto = projectService.getProjectDetail((long) projectId);
        return new ResponseEntity<>(getProjectDetailResDto, HttpStatus.OK);
    }

    @Operation(summary = "사용자 기부 신청", description = "사용자가 기부를 신청합니다.")
    @PostMapping(path = "/donation")
    public ResponseEntity<?> createUserDonation(
        @RequestBody CreateDonationReqDto createDonationReqDto,
        @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        // 토큰 유효성 검증
        Users loginUser = tokenUtils.validateAdminTokenAndGetUser(principalDetails, false);

        Project project = projectService.findById(createDonationReqDto.getProjectId());
        Long points = project.getPointPerMoi() * createDonationReqDto.getMoi();

        // 기부하려는 금액이 현재 보유 포인트보다 많은 경우 에러 반환
        if(points > loginUser.getPoint()){
            throw new CustomException(ErrorCode.EXCEED_USER_POINT);
        }

        // 기부 모이 수에 따른 티켓 발급
        int tickets = (int) (Math.log(points) / Math.log(2));

        // Users 테이블 업데이트 : 포인트 차감, 티켓 발급
        userDonationService.updateAfterDonation(loginUser, points, tickets);

        // UsersDonation 테이블 업데이트
        userDonationService.saveUsersDonation(points, tickets, createDonationReqDto.getMoi(), loginUser, project);

        return new ResponseEntity<>("사용자 기부 신청 완료", HttpStatus.OK);
    }

    @Transactional
    @Operation(summary = "사용자 봉사 신청", description = "사용자가 봉사를 신청합니다.")
    @PostMapping(path = "/volunteer")
    public ResponseEntity<?> createUserVolunteer(
        @Schema(description = "봉사 신청 일시 고유아이디", defaultValue = "1") Long vDateInfoId,
        @AuthenticationPrincipal PrincipalDetails principalDetails
    ){
        // 토큰 유효성 검증
        Users loginUser = tokenUtils.validateAdminTokenAndGetUser(principalDetails, false);

        // 해당봉사일 정보
        VolunteerDateInfo volunteerDateInfo = projectVolunteerService.findVolunteerDateInfoById(vDateInfoId);

        // capacity + 1이 max_capacity 를 넘을 경우 에러 발생(400)
        if(volunteerDateInfo.getCapacity() >= volunteerDateInfo.getMaxCapacity()){
            return new ResponseEntity<>(ErrorCode.EXCEED_MAX_CAPACITY, HttpStatus.BAD_REQUEST);
        }

        // 이미 해당 일자에 봉사 신청이 이미 되어있는지 확인
        if(projectVolunteerService.existsByVolunteerDateInfo(volunteerDateInfo)){
            return new ResponseEntity<>(ErrorCode.DUPLICATE_VOLUNTEER_REGISTER, HttpStatus.BAD_REQUEST);
        }

        // UsersVolunteer에 해당 내용 저장
        projectVolunteerService.saveUsersVolunteer(loginUser, volunteerDateInfo);

        // 해당 봉사일에 인원 수 추가
        projectVolunteerService.updateCapacity(volunteerDateInfo);

        return new ResponseEntity<>("사용자 봉사 신청 완료", HttpStatus.OK);
    }
}
