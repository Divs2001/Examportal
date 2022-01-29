package com.exam.examportalServer.entity;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private String username;
    private String password;
}
