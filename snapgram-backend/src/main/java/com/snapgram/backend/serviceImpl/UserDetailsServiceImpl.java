//package com.snapgram.backend.serviceImpl;
//
//
//
//import com.snapgram.backend.exception.InvalidCredentialException;
//
//import com.snapgram.backend.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<com.snapgram.backend.model.User> user = userRepository.findByEmail(username);
//        if(user.isPresent()){
//            com.snapgram.backend.model.User u=user.get();
//            List <GrantedAuthority> authorities=new ArrayList <>();
//            return new User(u.getUsername(), u.getPassword(),authorities);
//        }else{
//            throw new InvalidCredentialException("User not found!");
//        }
//    }
//}
