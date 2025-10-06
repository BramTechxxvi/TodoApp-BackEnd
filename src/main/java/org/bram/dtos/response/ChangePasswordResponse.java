package org.bram.dtos.response;


import lombok.*;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class ChangePasswordResponse {

    private String message;
    private boolean success;
}
