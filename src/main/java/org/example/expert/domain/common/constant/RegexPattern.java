package org.example.expert.domain.common.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexPattern {

    public static final String PASSWORD = "^(?=.*[A-Z])(?=.*\\d).{8,}$";

}
