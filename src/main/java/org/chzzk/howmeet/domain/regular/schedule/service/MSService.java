package org.chzzk.howmeet.domain.regular.schedule.service;

import lombok.RequiredArgsConstructor;
import org.chzzk.howmeet.domain.regular.room.entity.Room;
import org.chzzk.howmeet.domain.regular.room.repository.RoomRepository;
import org.chzzk.howmeet.domain.regular.schedule.dto.MSRequest;
import org.chzzk.howmeet.domain.regular.schedule.dto.MSResponse;
import org.chzzk.howmeet.domain.regular.schedule.entity.MemberSchedule;
import org.chzzk.howmeet.domain.regular.schedule.repository.MSRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class MSService {
    private final MSRepository msRepository;
    private final RoomRepository roomRepository;

    @Transactional
    public MSResponse createMemberSchedule(final MSRequest msRequest) {
        Room room = roomRepository.findById(msRequest.roomId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid room ID"));

        MemberSchedule memberSchedule = MemberSchedule.of(msRequest.dates(), msRequest.time(), msRequest.name(), room);

        MemberSchedule savedSchedule = msRepository.save(memberSchedule);

        String inviteLink = "http://localhost:8080/member-schedule/invite/" + savedSchedule.getId();

        return MSResponse.of(savedSchedule, inviteLink);
    }

    public MSResponse getMemberSchedule(final Long memberScheduleId) {
        MemberSchedule memberSchedule = msRepository.findById(memberScheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid schedule ID"));

        String inviteLink = "http://localhost:8080/member-schedule/invite/" + memberSchedule.getId();

        return MSResponse.of(memberSchedule, inviteLink);
    }

    @Transactional
    public void deleteMemberSchedule(final Long memberScheduleId) {
        if (!msRepository.existsById(memberScheduleId)) {
            throw new RuntimeException("Invalid schedule ID");
        }
        msRepository.deleteById(memberScheduleId);
    }
}
