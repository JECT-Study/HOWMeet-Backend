package org.chzzk.howmeet.domain.temporary.schedule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GSErrorCode {
    SCHEDULE_NOT_FOUND("스케줄을 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    SCHEDULE_CREATION_FAILED("스케줄 생성에 실패하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    GSErrorCode(final String message, final HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
