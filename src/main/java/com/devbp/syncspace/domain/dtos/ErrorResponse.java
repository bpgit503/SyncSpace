package com.devbp.syncspace.domain.dtos;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int status;
    private LocalDateTime time;
    private String message;
    private String details;

}
