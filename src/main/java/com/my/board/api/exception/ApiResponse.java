package com.my.board.api.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    // 사용자가 잘못 요청했을 경우 되돌려줄 메시지 DTO
    private String message;
}
