package com.ssafy.moida.model.project;

import com.ssafy.moida.api.request.UpdateDonationReqDto;
import jakarta.persistence.*;
import java.time.LocalDateTime;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * [프로젝트 기부 섹션에 해당하는 엔티티]
 * PK : 프로젝트 기부 아이디
 * 모금액(현재, 목표), 일자(시작-종료), 소제목, 설명
 */
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProjectDonation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long amount;

    @Column(nullable = false)
    private Long targetAmount;

    @Column(nullable = false, updatable = false)
    private LocalDateTime startDate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime endDate;

    @Column(nullable = false, length = 1000)
    private String subject;

    @Column(nullable = false, length = 2000)
    private String description;

    @Builder
    public ProjectDonation(Long amount, Long targetAmount, LocalDateTime startDate,
        LocalDateTime endDate, String subject, String description) {
        this.amount = amount;
        this.targetAmount = targetAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subject = subject;
        this.description = description;
    }

    /**
     * [세은] 현재 기부 금액 변경 
     * @param amount
     */
    public void updateAmount(Long amount){
        this.amount = amount;
    }

    /**
     * [세은] 기부 엔티티 데이터 수정
     * @param updateDonationReqDto
     */
    public void updateProjectDonationDetails(UpdateDonationReqDto updateDonationReqDto){
        if(!StringUtils.isBlank(updateDonationReqDto.getSubject())) this.subject = updateDonationReqDto.getSubject();
        if(!StringUtils.isBlank(updateDonationReqDto.getDescription())) this.description = updateDonationReqDto.getDescription();
        if(updateDonationReqDto.getTargetAmount() != null && updateDonationReqDto.getTargetAmount() > 0) this.targetAmount = updateDonationReqDto.getTargetAmount();
    }
}
