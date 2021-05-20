package com.fantastictrio.cw4sem.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class StrategicDecisionPayload {
    @Size(max = 32)
    private String name;
    @Size(max = 500)
    private String data;
}
