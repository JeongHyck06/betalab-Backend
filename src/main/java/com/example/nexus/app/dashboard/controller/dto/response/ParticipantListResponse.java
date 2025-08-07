package com.example.nexus.app.dashboard.controller.dto.response;

import com.example.nexus.app.post.domain.ParticipantReward;
import com.example.nexus.app.post.domain.Participation;
import com.example.nexus.app.post.domain.ParticipationStatus;
import com.example.nexus.app.post.domain.RewardStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record ParticipantListResponse(
        @Schema(description = "참여 ID")
        Long participationId,

        @Schema(description = "참여자 ID")
        Long userId,

        @Schema(description = "참여자 닉네임")
        String nickname,

        @Schema(description = "신청일시")
        LocalDateTime appliedAt,

        @Schema(description = "승인일시")
        LocalDateTime approvedAt,

        @Schema(description = "참여 상태")
        ParticipationStatus participationStatus,

        @Schema(description = "리워드 지급 상태")
        RewardStatus rewardStatus,

        @Schema(description = "리워드 지급일")
        LocalDateTime paidAt,

        @Schema(description = "지급하기 버튼 표시 여부")
        Boolean showPayButton
) {
    public static ParticipantListResponse from(Participation participation, ParticipantReward participantReward) {
        boolean showPayButton = participantReward != null && participantReward.isCompleted() && !participantReward.isRewardPaid();

        return new ParticipantListResponse(
                participation.getId(),
                participation.getUser().getId(),
                participation.getUser().getNickname(),
                participation.getAppliedAt(),
                participation.getApprovedAt(),
                participation.getStatus(),
                participantReward != null ? participantReward.getRewardStatus() : null,
                participantReward != null ? participantReward.getPaidAt() : null,
                showPayButton
        );
    }
}
