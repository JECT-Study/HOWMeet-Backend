package org.chzzk.howmeet.fixture;

import org.chzzk.howmeet.domain.common.embedded.date.impl.ScheduleTime;
import org.chzzk.howmeet.domain.common.model.ScheduleName;
import org.chzzk.howmeet.domain.temporary.schedule.entity.GuestSchedule;
import org.chzzk.howmeet.domain.temporary.schedule.exception.GSException;

import java.time.LocalTime;
import java.util.List;

import static org.chzzk.howmeet.domain.temporary.schedule.exception.GSErrorCode.SCHEDULE_CREATION_FAILED;

public enum GSFixture {
    MEETING_A("Meeting A", List.of("2023-01-01", "2023-01-02"), LocalTime.of(9, 0), LocalTime.of(17, 0)),
    MEETING_B("Meeting B", List.of("2023-02-01", "2023-02-02"), LocalTime.of(10, 0), LocalTime.of(18, 0)),
    MEETING_C("Meeting C", List.of("2023-03-01", "2023-03-02"), LocalTime.of(11, 0), LocalTime.of(19, 0)),
    MEETING_D("Meeting D", List.of("2023-04-01", "2023-04-02"), LocalTime.of(12, 0), LocalTime.of(20, 0));

    private final String name;
    private final List<String> dates;
    private final LocalTime startTime;
    private final LocalTime endTime;

    GSFixture(final String name, final List<String> dates, final LocalTime startTime, final LocalTime endTime) {
        this.name = name;
        this.dates = dates;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public GuestSchedule create(Long id) {
        ScheduleTime scheduleTime = ScheduleTime.of(startTime, endTime);
        ScheduleName scheduleName = ScheduleName.from(name);
        GuestSchedule guestSchedule = GuestSchedule.of(dates, scheduleTime, scheduleName);

        try {
            java.lang.reflect.Field idField = GuestSchedule.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(guestSchedule, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new GSException(SCHEDULE_CREATION_FAILED);
        }

        return guestSchedule;
    }

    public String getName() {
        return name;
    }

    public List<String> getDates() {
        return dates;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
