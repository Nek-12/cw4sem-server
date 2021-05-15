package com.fantastictrio.cw4sem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @Pattern(regexp = "[\\w-.]{3,31}")
    private String username;
    @Pattern(regexp = "[\\x21-\\x7E]{8,64}")
    private String password;
}
