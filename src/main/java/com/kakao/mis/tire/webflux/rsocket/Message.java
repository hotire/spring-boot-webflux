package com.kakao.mis.tire.webflux.rsocket;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private String origin;
    private String interaction;
    private long index;
    private final long created = Instant.now().getEpochSecond();
}
