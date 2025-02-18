package com.snapgram.backend.serviceImpl;


import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.config.JwtUtil;
import com.snapgram.backend.exception.AuthenticationException;
import com.snapgram.backend.exception.InvalidCredentialException;
import com.snapgram.backend.model.User;
import com.snapgram.backend.service.AuthService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public boolean passwordMatches(String password, String encodedPassword) {
        return passwordEncoder().matches(password, encodedPassword);

    }

    @Override
    public String authenticateUser(UserRequestDto userRequestDto)  throws AuthenticationException {
        User user= userService.findUserByUsername(userRequestDto.getUsername());

        if(passwordMatches(userRequestDto.getPassword(), user.getPassword())){
            return jwtUtil.generateToken(user.getUsername());
        }else{
            throw new InvalidCredentialException("Invalid username or password. Please check your credentials!");
        }
    }
}