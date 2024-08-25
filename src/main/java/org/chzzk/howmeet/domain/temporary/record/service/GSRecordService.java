package org.chzzk.howmeet.domain.temporary.record.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.chzzk.howmeet.domain.common.auth.model.AuthPrincipal;
import org.chzzk.howmeet.domain.common.model.Nickname;
import org.chzzk.howmeet.domain.common.model.NicknameList;
import org.chzzk.howmeet.domain.common.model.SelectionDetail;
import org.chzzk.howmeet.domain.temporary.auth.entity.Guest;
import org.chzzk.howmeet.domain.temporary.record.dto.get.response.GSRecordGetResponse;
import org.chzzk.howmeet.domain.temporary.record.dto.post.request.GSRecordPostRequest;
import org.chzzk.howmeet.domain.temporary.record.entity.GuestScheduleRecord;
import org.chzzk.howmeet.domain.temporary.record.model.GSRecordNicknameList;
import org.chzzk.howmeet.domain.temporary.record.model.GSRecordSelectionDetail;
import org.chzzk.howmeet.domain.temporary.record.repository.GSRecordRepository;
import org.chzzk.howmeet.domain.temporary.record.repository.TmpGSRepository;
import org.chzzk.howmeet.domain.temporary.record.repository.TmpGuestRepository;
import org.chzzk.howmeet.domain.temporary.schedule.entity.GuestSchedule;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class GSRecordService {

    private final TmpGSRepository tmpGSRepository;
    private final TmpGuestRepository tmpGuestRepository;
    private final GSRecordRepository gsRecordRepository;

    @Transactional
    public void postGSRecord(final GSRecordPostRequest gsRecordPostRequest, final AuthPrincipal authPrincipal) {
        Guest guest = findGuestByGuestId(authPrincipal.id());
        gsRecordRepository.deleteByGuestId(guest.getId());

        List<LocalDateTime> selectTimes = gsRecordPostRequest.selectTime();
        GuestSchedule gs = findGSByGSId(gsRecordPostRequest.gsId());

        List<GuestScheduleRecord> gsRecords = convertSeletTimesToGSRecords(selectTimes, gs, guest);
        gsRecordRepository.saveAll(gsRecords);
    }

    private List<GuestScheduleRecord> convertSeletTimesToGSRecords(final List<LocalDateTime> selectTimes,
            final GuestSchedule gs, final Guest guest) {

        LocalDateTime startTime = gs.getDate().getStartDate();
        LocalDateTime endTime = gs.getDate().getEndDate();

        List<GuestScheduleRecord> gsRecords = selectTimes.stream().map(selectTime -> {
            validateSelectTime(selectTime, startTime, endTime);
            return GuestScheduleRecord.of(guest, gs, selectTime);
        }).collect(Collectors.toList());
        return gsRecords;
    }

    private void validateSelectTime(final LocalDateTime selectTime, final LocalDateTime startTime,
            final LocalDateTime endTime) {

        LocalDate selectDate = selectTime.toLocalDate();
        LocalTime selectHour = selectTime.toLocalTime();

        if (selectDate.isBefore(startTime.toLocalDate()) || selectDate.isAfter(endTime.toLocalDate())) {
            throw new IllegalArgumentException("유효하지 않은 시간을 선택하셨습니다.");
        }

        if (selectHour.isBefore(startTime.toLocalTime()) || selectHour.isAfter(
                endTime.toLocalTime().minusMinutes(30))) {
            throw new IllegalArgumentException("유효하지 않은 시간을 선택하셨습니다.");
        }
    }

    public GSRecordGetResponse getGSRecord(final Long gsId) {

        List<Guest> guestList = tmpGuestRepository.findByGuestScheduleId(gsId);
        Map<Long, Nickname> nickNameMap = guestList.stream()
                .collect(Collectors.toMap(Guest::getId, Guest::getNickname));

        List<GuestScheduleRecord> gsRecords = findGSRecordByGSId(gsId);

        NicknameList allNickname = GSRecordNicknameList.convertNicknameProvidersList(guestList);

        NicknameList participants = GSRecordNicknameList.convertMapToNickNameList(gsRecords, nickNameMap);
        List<SelectionDetail> selectedInfoList = GSRecordSelectionDetail.convertMapToSelectionDetail(gsRecords,
                nickNameMap);

        return GSRecordGetResponse.of(gsId, allNickname, participants, selectedInfoList);
    }


    private List<GuestScheduleRecord> findGSRecordByGSId(final Long gsId) {
        List<GuestScheduleRecord> gsRecords = gsRecordRepository.findByGuestScheduleId(gsId);
        if (gsRecords == null) {
            return Collections.emptyList();
        }
        return gsRecords;
    }

    private GuestSchedule findGSByGSId(final Long gsId) {
        return tmpGSRepository.findById(gsId).orElseThrow(() -> new IllegalArgumentException("일치하는 일정을 찾을 수 없습니다."));
    }

    private Guest findGuestByGuestId(final Long guestId) {
        return tmpGuestRepository.findById(guestId)
                .orElseThrow(() -> new IllegalArgumentException("일치하는 비회원 id를 찾을 수 없습니다."));
    }
}
