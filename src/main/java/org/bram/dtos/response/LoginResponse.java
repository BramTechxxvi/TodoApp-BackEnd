package org.bram.dtos.response;

import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String userId;
    private String message;
    private boolean success;
}
