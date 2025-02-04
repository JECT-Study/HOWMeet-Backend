package org.chzzk.howmeet.domain.temporary.record.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.chzzk.howmeet.domain.common.model.Nickname;
import org.chzzk.howmeet.domain.common.model.ParticipantDetails;
import org.chzzk.howmeet.domain.common.model.SelectionDetail;
import org.chzzk.howmeet.domain.temporary.record.entity.GuestScheduleRecord;

public class GSRecordSelectionDetail extends SelectionDetail {

    private GSRecordSelectionDetail(final LocalDateTime selectTime,
            final ParticipantDetails participantDetails) {
        super(selectTime, participantDetails);
    }

    public static List<SelectionDetail> convertMapToSelectionDetail(final List<GuestScheduleRecord> gsRecords,
            final Map<Long, Nickname> nickNameMap) {
        HashMap<LocalDateTime, GSRecordNicknames> selectTimeMap = new HashMap<>();

        Nickname nickname;
        LocalDateTime selectTime;
        for (GuestScheduleRecord gsRecord : gsRecords) {
            nickname = nickNameMap.get(gsRecord.getGuestId());
            selectTime = gsRecord.getSelectTime();

            selectTimeMap.computeIfAbsent(selectTime, k -> GSRecordNicknames.create()).add(nickname);
        }
        return convertMapToSelectionDetailsList(selectTimeMap);
    }

}
