package org.chzzk.howmeet.domain.temporary.schedule.dto;

import org.chzzk.howmeet.domain.common.embedded.date.impl.ScheduleDate;
import org.chzzk.howmeet.domain.temporary.schedule.entity.GuestSchedule;

import java.time.LocalDate;
import java.time.LocalTime;

public record GuestScheduleResponse(Long guestScheduleId, String name, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime, String inviteLink) {
    public static GuestScheduleResponse of(final GuestSchedule guestSchedule, final String inviteLink) {
        ScheduleDate scheduleDate = guestSchedule.getDate();
        return new GuestScheduleResponse(
                guestSchedule.getId(),
                guestSchedule.getName().getValue(),
                scheduleDate.getStartDate().toLocalDate(),
                scheduleDate.getStartDate().toLocalTime(),
                scheduleDate.getEndDate().toLocalDate(),
                scheduleDate.getEndDate().toLocalTime(),
                inviteLink
        );
    }
}
