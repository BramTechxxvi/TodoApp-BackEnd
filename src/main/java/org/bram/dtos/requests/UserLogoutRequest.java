package org.bram.dtos.requests;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class UserLogoutRequest {

    @Id
    private String userId;
}
