package com.data.ss13.service;

import com.data.ss13.entity.bt.ERole;
import com.data.ss13.entity.bt.Role;
import com.data.ss13.entity.bt.User;
import com.data.ss13.entity.dto.request.UserLogin;
import com.data.ss13.entity.dto.request.UserRegister;
import com.data.ss13.entity.dto.response.JWTResponse;
import com.data.ss13.repository.RoleRepo;
import com.data.ss13.repository.UserRepo;
import com.data.ss13.security.jwt.JWTProvider;
import com.data.ss13.security.principal.CustomUserDetails;
import lombok.extern.slf4j.Slf4j;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.Set;


@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{
    private final UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private JWTProvider jwtProvider;
    private AuthenticationManager authenticationManager;

    @Override
    public User editUser(User user){
        if(userRepo.existsById(user.getId())){
            return userRepo.save(user);
        }
        throw new EntityNotFoundException("Khong ton tai id");
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new SecurityException("Người dùng chưa đăng nhập");
        }

        Object principal = authentication.getPrincipal();

        String username;
        if (principal instanceof CustomUserDetails) {
            username = ((CustomUserDetails) principal).getUsername();
        } else if (principal instanceof String) {
            username = (String) principal;
        } else {
            throw new SecurityException("Không xác định được thông tin người dùng");
        }

        return userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy người dùng với username: " + username));
    }


    @Override
    public User registerUser(UserRegister userRegister) {
        if (userRepo.existsByUsername(userRegister.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        if (userRepo.existsByEmail(userRegister.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role userRole = roleRepo.findByName(String.valueOf(ERole.ROLE_USER))
                .orElseThrow(() -> new RuntimeException("Error: Role not found"));

        User user = User.builder()
                .username(userRegister.getUsername())
                .email(userRegister.getEmail())
                .password(passwordEncoder.encode(userRegister.getPassword()))
                .roles(Set.of(userRole))
                .build();

        return userRepo.save(user);
    }


    @Override
    public JWTResponse login(UserLogin userLogin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userLogin.getUsername(),
                            userLogin.getPassword()
                    )
            );

            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtProvider.generateToken(userDetails.getUsername());

            return JWTResponse.builder()
                    .username(userDetails.getUsername())
                    .email(userDetails.getEmail())
                    .status(userDetails.isEnabled())
                    .authorities(userDetails.getAuthorities())
                    .token(token)
                    .build();
        } catch (AuthenticationException e) {
            log.error("Sai username hoặc password: {}", e.getMessage());
            throw new BadCredentialsException("Tài khoản hoặc mật khẩu không chính xác.");
        }
    }

}