package com.data.btss15trenlop.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegister {
    private String username;
    private String email;
    private String phone;
    private String password;
    private String fullName;
    private String address;
    private List<String> roles;
}
