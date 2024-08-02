package org.chzzk.howmeet.domain.temporary.schedule.repository;

import org.chzzk.howmeet.domain.temporary.schedule.entity.GuestSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestScheduleRepository extends JpaRepository<GuestSchedule, Long> {
}