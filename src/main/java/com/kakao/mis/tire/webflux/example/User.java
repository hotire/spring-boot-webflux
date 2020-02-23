package com.kakao.mis.tire.webflux.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String username;
    private String firstname;
    private String lastname;

    public static final User SAUL = new User();
    public static final User SKYLER = new User();
    public static final User JESSE = new User();
}
