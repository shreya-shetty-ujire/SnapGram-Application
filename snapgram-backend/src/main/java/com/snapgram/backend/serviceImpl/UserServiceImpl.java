package com.snapgram.backend.serviceImpl;

import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.exception.UserAlreadyExistsException;
import com.snapgram.backend.model.User;
import com.snapgram.backend.repository.UserRepository;
import com.snapgram.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Override
    public User saveUser(UserRequestDto userRequestDto) {
        Optional<User> existingUser=userRepository.findByUserName(userRequestDto.getUsername());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Username already taken");
        }

        existingUser=userRepository.findByEmail(userRequestDto.getEmail());
        if(existingUser.isPresent()){
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user=new User();
        user.setUserName(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());

//      Hash the password before saving it to the database
        String hashed=passwordEncode.encode(userRequestDto.getPassword());
        user.setPassword(hashed);

        user.setCreatedAt(LocalDateTime.now());

        return userRepository.save(user);
    }

    @Override
    public List <User> getAllUsers() {
        return List.of();
    }

    @Override
    public Optional <User> getUserById(long userId) {
        return Optional.empty();
    }

    @Override
    public void updateUser(UserRequestDto userRequestDto) {

//        Optional<User> existingUser=userRepository.findByUserName(userRequestDto.getUsername());
//        if(!existingUser.isPresent()){
//            throw Exception("Username already taken");
//        }

    }

    @Override
    public Optional <User> deleteUser(User user) {
        return Optional.empty();
    }
}
