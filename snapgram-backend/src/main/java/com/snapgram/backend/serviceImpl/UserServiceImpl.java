package com.snapgram.backend.serviceImpl;

import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.exception.UserAlreadyExistsException;
import com.snapgram.backend.exception.UserNotFoundException;
import com.snapgram.backend.model.User;
import com.snapgram.backend.repository.UserRepository;
import com.snapgram.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User saveUser(UserRequestDto userRequestDto) {
        logger.info("Attempting to save user with username: {}", userRequestDto.getUsername());
        Optional<User> existingUser=userRepository.findByUserName(userRequestDto.getUsername());
        if(existingUser.isPresent()){
            logger.warn("Username already taken: {}", userRequestDto.getUsername());
            throw new UserAlreadyExistsException("Username already taken");
        }

        existingUser=userRepository.findByEmail(userRequestDto.getEmail());
        if(existingUser.isPresent()){
            logger.warn("Email already registered: {}", userRequestDto.getEmail());
            throw new UserAlreadyExistsException("Email already registered");
        }

        User user=new User();
        user.setUserName(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setFirstName(userRequestDto.getFirstName());
        user.setLastName(userRequestDto.getLastName());

//      Hash the password before saving it to the database
        String hashed=passwordEncoder().encode(userRequestDto.getPassword());
        user.setPassword(hashed);

        user.setCreatedAt(LocalDateTime.now());
        logger.info("Saving new user: {}", userRequestDto.getUsername());
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
    public User updateUser(Long userId, UserUpdateDto userUpdateDto) {
        logger.info("Attempting to update user: {}", userId);
        return userRepository.findById(userId).map(existingUser->
        {
            Optional.ofNullable(userUpdateDto.getUsername()).ifPresent(existingUser::setUserName);
            Optional.ofNullable(userUpdateDto.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(userUpdateDto.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(userUpdateDto.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(userUpdateDto.getPassword()).ifPresent(existingUser::setPassword);
            User updatedUser=  userRepository.save(existingUser);
            logger.info("User updated successfully with ID: {}", updatedUser.getUserName());
            return updatedUser;
        }).orElseThrow(()-> {
            logger.error("User not found");
            return new UserNotFoundException("User not Found!" + userId);
        });

    }

    @Override
    public Optional <User> deleteUser(User user) {
        return Optional.empty();
    }
}
