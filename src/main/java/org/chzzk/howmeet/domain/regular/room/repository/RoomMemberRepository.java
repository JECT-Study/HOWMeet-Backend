package org.chzzk.howmeet.domain.regular.room.repository;

import org.chzzk.howmeet.domain.regular.room.entity.RoomMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoomMemberRepository extends JpaRepository<RoomMember, Long> {
    List<RoomMember> findByRoomId(final Long roomId);
    List<RoomMember> findByMemberId(final Long memberId);
    Optional<RoomMember> findByRoomIdAndMemberId(@Param("roomId") final Long roomId, @Param("memberId") final Long memberId);
}