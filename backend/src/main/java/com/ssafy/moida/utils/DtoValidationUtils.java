package com.ssafy.moida.utils;

import com.ssafy.moida.api.request.CreateProjectReqDto;
import com.ssafy.moida.api.request.DonationReqDto;
import com.ssafy.moida.api.request.ProjectReqDto;
import com.ssafy.moida.api.request.VolunteerReqDto;
import com.ssafy.moida.utils.error.ErrorCode;
import com.ssafy.moida.utils.exception.CustomException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class DtoValidationUtils {
    /**
     * [세은] ProjectReqDto NOT NULL 검증 함수
     * @param projectReqDto
     */
    public  void validateProjectReqDto(ProjectReqDto projectReqDto){
        // String 타입에 대해서는 null, 빈 문자열, 공백 검사
        if(StringUtils.isBlank(projectReqDto.getCategory())){
            throw new IllegalArgumentException("카테고리는 필수 입력값입니다");
        }

        if(StringUtils.isBlank(projectReqDto.getSubject())){
            throw new IllegalArgumentException("프로젝트명은 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(projectReqDto.getDescription())){
            throw new IllegalArgumentException("상세설명은 필수 입력값입니다.");
        }

        // Long 타입에 대해서는 null, 범위 검사
        if(projectReqDto.getPointPerMoi() == null || projectReqDto.getPointPerMoi() <= 0){
            throw new IllegalArgumentException("곡물가치는 필수 입력값이며 0 이상의 값을 입력해주세요.");
        }
    }

    /**
     * [세은] DonationReqDto NOT NULL 검증
     * @param donationReqDto
     */
    public void validateDonationReqDto(DonationReqDto donationReqDto){
        if(StringUtils.isBlank(donationReqDto.getStartDate()) || StringUtils.isBlank(donationReqDto.getEndDate())){
            throw new IllegalArgumentException("기부일시는 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(donationReqDto.getSubject())){
            throw new IllegalArgumentException("소제목은 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(donationReqDto.getDescription())){
            throw new IllegalArgumentException("상세설명은 필수 입력값입니다.");
        }

        if(donationReqDto.getTargetAmount() == null || donationReqDto.getTargetAmount() <= 0){
            throw new IllegalArgumentException("목표 기부금은 필수 입력값이며 양수 값만 가능합니다");
        }
    }

    /**
     * [세은] VolunteerReqDto NOT NULL 검증
     * @param volunteerReqDto
     */
    public void validateVolunteerReqDto(VolunteerReqDto volunteerReqDto){
        if(StringUtils.isBlank(volunteerReqDto.getStartDate()) || StringUtils.isBlank(volunteerReqDto.getEndDate())){
            throw new IllegalArgumentException("봉사일시는 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(volunteerReqDto.getSubject())){
            throw new IllegalArgumentException("소제목은 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(volunteerReqDto.getDescription())){
            throw new IllegalArgumentException("상세설명은 필수 입력값입니다.");
        }

        if(StringUtils.isBlank(volunteerReqDto.getLocation())){
            throw new IllegalArgumentException("위치값은 필수 입력값입니다.");
        }

        if(volunteerReqDto.getDifficultyLevel() <= 0){
            throw new IllegalArgumentException("초기 봉사 난이도는 필수 입력값이며 양수 값만 가능합니다");
        }

        if(volunteerReqDto.getCapacityPerDate() <= 0){
            throw new IllegalArgumentException("최대 봉사 인원수는 필수 입력값이며 양수 값만 가능합니다");
        }
    }

    /**
     * [세은] 프로젝트 생성 시 NOT NULL 검ㅅ
     * @param createProjectReqDto
     */
    public void validateCreateProjectReqDto(CreateProjectReqDto createProjectReqDto){
        validateProjectReqDto(createProjectReqDto.getProjectReqDto());
        validateDonationReqDto(createProjectReqDto.getDonationReqDto());
        validateVolunteerReqDto(createProjectReqDto.getVolunteerReqDto());
    }
}
