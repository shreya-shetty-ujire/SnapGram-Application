package com.snapgram.backend.serviceImpl;


import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.DTO.UserUpdateDto;
import com.snapgram.backend.exception.UserAlreadyExistsException;
import com.snapgram.backend.exception.UserException;
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
        Optional <User> existingUser = userRepository.findByUsername(userRequestDto.getUsername());
        if (existingUser.isPresent()) {
            logger.warn("Username already taken: {}", userRequestDto.getUsername());
            throw new UserAlreadyExistsException("Username already taken");
        }

        Optional <User> isEmailExist = userRepository.findByEmail(userRequestDto.getEmail());
        if (isEmailExist.isPresent()) {
            logger.warn("Email already registered: {}", userRequestDto.getEmail());
            throw new UserAlreadyExistsException("Email is already registered");
        }
        if (userRequestDto.getEmail() == null || userRequestDto.getUsername() == null || userRequestDto.getPassword() == null || userRequestDto.getName() == null) {
            throw new UserException("Fields should not be null");
        }
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());

//      Hash the password before saving it to the database
        String hashed = passwordEncoder().encode(userRequestDto.getPassword());
        user.setPassword(hashed);

        user.setCreatedAt(LocalDateTime.now());
        logger.info("Saving new user: {}", userRequestDto.getUsername());
        userRepository.save(user);
        return user;
    }


    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("User does not " +
                "exist !"));
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(reqUserId);
        logger.info("Attempting to follow {}", followUser.getUsername());

        reqUser.getFollowing().add(followUser);
        followUser.getFollowers().add(reqUser);

        // Saved because the relationship followers and following are modified so to persist the changes we use save
        userRepository.save(reqUser);
        userRepository.save(followUser);

        logger.info("User follows {}", followUser.getUsername());

        return "You are following " + followUser.getUsername();
    }

    @Override
    public String unFollowUser(Integer reqUserId, Integer followUserId) {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(reqUserId);

        logger.info("Attempting to unfollow {}", followUser.getUsername());

        followUser.getFollowers().remove(reqUser);
        reqUser.getFollowing().remove(followUser);

        userRepository.save(reqUser);
        userRepository.save(followUser);

        logger.info("User unfollowed {}", followUser.getUsername());
        return "You have successfully unfollowed " + followUser.getUsername();
    }

    @Override
    public List <User> findUsersByIds(List <Integer> userIds) {
        return userRepository.findAllUsersByUserIds(userIds);
    }

    @Override
    public List <User> searchUsers(String query) {
        List <User> usersList = userRepository.findByQuery(query);

        if (usersList.isEmpty()) {
            throw new UserException("User not found.");
        }
        return usersList;
    }

    @Override
    public User findUserProfile(String token) throws UserNotFoundException {
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        logger.info("Fetching user with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(()->{
                    logger.error("User not found with username: {}", username);
                    throw new UserNotFoundException("User not found with username: " + username);
                });
    }

    @Override
    public void deleteUser(Integer userId) {
        userRepository.findById(userId).map(user -> {
                    String username = user.getUsername();
                    userRepository.delete(user);
                    return username;
                })
                .orElseThrow(() -> new UserNotFoundException("User Not Found"));

    }

    @Override
    public User updateUser(Integer userId, UserUpdateDto userUpdateDto) {
        logger.info("Attempting to update user: {}", userId);
        return userRepository.findById(userId).map(existingUser ->
        {
            Optional.ofNullable(userUpdateDto.getUsername()).ifPresent(existingUser::setUsername);
            Optional.ofNullable(userUpdateDto.getName()).ifPresent(existingUser::setName);
            Optional.ofNullable(userUpdateDto.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(userUpdateDto.getGender()).ifPresent(existingUser::setGender);
            Optional.ofNullable(userUpdateDto.getUserImage()).ifPresent(existingUser::setUserImage);
            Optional.ofNullable(userUpdateDto.getBio()).ifPresent(existingUser::setBio);
            User updatedUser = userRepository.save(existingUser);
            logger.info("User updated successfully with ID: {}", updatedUser.getUsername());
            return updatedUser;
        }).orElseThrow(() -> {
            logger.error("User not found. You cannot update this User6");
            return new UserNotFoundException("User not Found!" + userId);
        });

    }
}