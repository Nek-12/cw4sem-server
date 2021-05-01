package com.fantastictrio.cw4sem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPayload {
    private String username;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private int id;
}
