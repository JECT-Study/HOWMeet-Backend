package org.chzzk.howmeet.domain.regular.record.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.chzzk.howmeet.domain.common.entity.BaseEntity;
import org.chzzk.howmeet.domain.regular.auth.entity.Member;
import org.chzzk.howmeet.domain.regular.schedule.entity.MemberSchedule;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MemberScheduleRecord extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "select_time")
    private LocalDateTime selectTime;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Column(name = "member_schedule_id", nullable = false)
    private Long memberScheduleId;

    private MemberScheduleRecord(final Long memberId, final Long memberScheduleId, final LocalDateTime selectTime) {
        this.selectTime = selectTime;
        this.memberId = memberId;
        this.memberScheduleId = memberScheduleId;
    }

    public static MemberScheduleRecord of(final Member member,
            final MemberSchedule memberSchedule, final LocalDateTime selectTime) {
        return new MemberScheduleRecord(member.getId(), memberSchedule.getId(), selectTime);
    }
}
