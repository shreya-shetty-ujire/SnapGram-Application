package com.snapgram.backend.serviceImpl;


import com.snapgram.backend.DTO.UserDto;
import com.snapgram.backend.DTO.UserRequestDto;
import com.snapgram.backend.config.JwtUtil;
import com.snapgram.backend.exception.UserAlreadyExistsException;
import com.snapgram.backend.exception.UserException;
import com.snapgram.backend.exception.UserNotFoundException;
import com.snapgram.backend.model.User;
import com.snapgram.backend.repository.UserRepository;
import com.snapgram.backend.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    JwtUtil jwtUtil;


    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public User saveUser(UserRequestDto userRequestDto){
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
        User user = new User();
        user.setUsername(userRequestDto.getUsername());
        user.setEmail(userRequestDto.getEmail());
        user.setName(userRequestDto.getName());

//      Hash the password before saving it to the database
        String hashed = passwordEncoder.encode(userRequestDto.getPassword());
        user.setPassword(hashed);

        user.setCreatedAt(LocalDateTime.now());
        logger.info("Saving new user: {}", userRequestDto.getUsername());
        return userRepository.save(user);
    }


    @Override
    public User findUserById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserException("User does not " +
                "exist !"));
    }

    @Override
    public String followUser(Integer reqUserId, Integer followUserId) {

        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(followUserId);
        logger.info("Attempting to follow {}", followUser.getUsername());

        UserDto follower=new UserDto();
        follower.setUserId(reqUser.getUserId());
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getUserImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following =new UserDto();
        following.setUserId(followUser.getUserId());
        following.setEmail(followUser.getEmail());
        following.setName(followUser.getName());
        following.setUserImage(followUser.getUserImage());
        following.setUsername(followUser.getUsername());

        reqUser.getFollowing().add(following);
        followUser.getFollowers().add(follower);

        // Saved because the relationship followers and following are modified so to persist the changes we use save
        userRepository.save(followUser);
        userRepository.save(reqUser);

        logger.info("User follows {}", followUser.getUsername());

        return "You are following " + followUser.getUsername();
    }

    @Override
    public String unFollowUser(Integer reqUserId, Integer unfollowUserId) {
        User reqUser = findUserById(reqUserId);
        User followUser = findUserById(unfollowUserId);
        logger.info("Attempting to unfollow {}", followUser.getUsername());

        UserDto follower=new UserDto();
        follower.setUserId(reqUser.getUserId());
        follower.setEmail(reqUser.getEmail());
        follower.setName(reqUser.getName());
        follower.setUserImage(reqUser.getUserImage());
        follower.setUsername(reqUser.getUsername());

        UserDto following =new UserDto();
        following.setUserId(followUser.getUserId());
        following.setEmail(followUser.getEmail());
        following.setName(followUser.getName());
        following.setUserImage(followUser.getUserImage());
        following.setUsername(followUser.getUsername());

        reqUser.getFollowing().remove(following);
        followUser.getFollowers().remove(follower);

        // Saved because the relationship followers and following are modified so to persist the changes we use save
        userRepository.save(followUser);
        userRepository.save(reqUser);


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
        token=token.substring(7); //Bearer
        logger.info(token);
        String username = jwtUtil.extractUsername(token);
        logger.info("Sending requested user details: "+username);
        return userRepository.findByUsername(username).orElseThrow(()->new UserNotFoundException("User with " +
                "username "+ username+" not found"));
    }

    @Override
    public User findProfileUsingUsername(String token, String username) throws UserNotFoundException {
        token=token.substring(7); //Bearer
        logger.info(token);
        logger.info("Sending requested user details: "+username);

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username " + username + " not found"));
    }

    @Override
    public User findUserByUsername(String username) {
        logger.info("Fetching user with username: {}", username);
        return userRepository.findByUsername(username)
                .orElseThrow(()->{
                    logger.error("User not found with username: {}", username);
                    return new UserNotFoundException("User not found with username: " + username);
                });

    }

    @Override
    public void deleteUser(String token) {


        String usernameFromToken = jwtUtil.extractUsername(token);

        logger.info("Attempting to delete user with userId: {}", usernameFromToken);

        userRepository.findByUsername(usernameFromToken).map(user -> {
                    // If user exists, delete the user and log the deletion
                    String username = user.getUsername();
                    logger.info("Deleting user: {}", username);
                    userRepository.delete(user); // Delete the user from the database
                    logger.info("User with username '{}' has been deleted successfully.", username);
                    return username;
                })
                .orElseThrow(() -> {
                    // If user doesn't exist, log the error and throw an exception
                    logger.error("User with username '{}' not found.", usernameFromToken);
                    return new UserNotFoundException("User Not Found");
                });

    }

    @Override
    public boolean passwordMatches(String password, String encodedPassword) {
        return passwordEncoder.matches(password, encodedPassword);

    }

    public User authenticateUser(String username, String password) {

        return Optional.ofNullable(findUserByUsername(username))
                .filter(user->passwordMatches(password,user.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
    }

    @Override
    public Map <String, Object> updateUser(User updatedUser, User existingUser) {
        logger.info("Attempting to update user: {}", existingUser.getUserId());  // Log with existingUser's userId

        return userRepository.findById(existingUser.getUserId()).map(userToUpdate -> {
            String oldUsername = userToUpdate.getUsername();
            // Use the fields from updatedUser to set the new values in userToUpdate.
            Optional.ofNullable(updatedUser.getUsername()).ifPresent(userToUpdate::setUsername);
            Optional.ofNullable(updatedUser.getName()).ifPresent(userToUpdate::setName);
            Optional.ofNullable(updatedUser.getEmail()).ifPresent(userToUpdate::setEmail);
            Optional.ofNullable(updatedUser.getGender()).ifPresent(userToUpdate::setGender);
            Optional.ofNullable(updatedUser.getUserImage()).ifPresent(userToUpdate::setUserImage);
            Optional.ofNullable(updatedUser.getBio()).ifPresent(userToUpdate::setBio);
            Optional.ofNullable(updatedUser.getPhoneNumber()).ifPresent(userToUpdate::setPhoneNumber);

            // Save the updated user to the repository
            User savedUser = userRepository.save(userToUpdate);

            logger.info("User updated successfully with ID: {}", savedUser.getUserId()); // Log the updated userId
            String newToken = null;
            if (!oldUsername.equals(savedUser.getUsername())) {
                logger.info("Username changed. Generating new token...");
                newToken = jwtUtil.generateToken(savedUser.getUsername()); // Generate a new token
            }
            Map <String, Object> response = new HashMap <>();
            response.put("user", savedUser);
            response.put("token", newToken);
            logger.info("Updated token" +newToken+" for username "+savedUser.getUsername());
            return response;
        }).orElseThrow(() -> {
            logger.error("User not found. You cannot update this User");
            return new UserNotFoundException("User not found with ID: " + existingUser.getUserId());
        });
    }
    public List<User> getPopularUsers(Integer userId, int limit) {
        Pageable top = PageRequest.of(0, limit);
        return userRepository.findTopUsersByFollowers(userId, top);
    }


}