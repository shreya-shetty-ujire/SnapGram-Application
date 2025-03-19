package com.snapgram.backend.serviceImpl;



import com.snapgram.backend.config.JwtUtil;
import com.snapgram.backend.exception.AuthenticationException;
import com.snapgram.backend.exception.InvalidCredentialException;
import com.snapgram.backend.model.User;
import com.snapgram.backend.service.AuthService;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean passwordMatches(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);

    }

    @Override
    public String authenticateUser(String username, String password)  throws AuthenticationException {
        User user= userService.findUserByUsername(username);

        if(passwordMatches(password, user.getPassword())){
            return jwtUtil.generateToken(user.getUsername());
        }else{
            throw new InvalidCredentialException("Invalid username or password. Please check your credentials!");
        }
    }
}