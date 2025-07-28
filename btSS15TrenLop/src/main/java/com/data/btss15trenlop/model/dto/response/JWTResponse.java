package com.data.btss15trenlop.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String username;
    private String phone;
    private String email;
    private boolean status;
    private Collection<? extends GrantedAuthority> authorities;
    private String token;
    private String refreshToken;

    public JWTResponse(String accessToken, String token) {
        this.token = accessToken;
        this.token = token;
    }
}
