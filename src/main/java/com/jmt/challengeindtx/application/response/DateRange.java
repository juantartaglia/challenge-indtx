package com.jmt.challengeindtx.application.response;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record DateRange(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
}
