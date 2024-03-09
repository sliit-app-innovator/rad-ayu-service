package com.sliit.ayu.ayuservice.service.impl;

import com.sliit.ayu.ayuservice.execption.AyuException;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.sliit.ayu.ayuservice.service.impl.UserServiceImpl.AU_001;
import static com.sliit.ayu.ayuservice.service.impl.UserServiceImpl.USER_CANNOT_BE_FOUND;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private Map<String, String> refreshTokenStore = new ConcurrentHashMap<>();

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username)  {
        try {
            UserEntity user = userRepository.findByUsername(username);
            return new CustomUserDetails(user.getUsername(), user.getPassword(), new ArrayList<>(),user.getId(),user.getUserRole());
        } catch (Exception e) {
            throw new RuntimeException("Unauthorized");
        }
    }

    public void storeRefreshToken(String username, String refreshToken) {
        refreshTokenStore.put(username, refreshToken);
    }

    public String getRefreshToken(String username) {
        return refreshTokenStore.get(username);
    }

    public boolean validateRefreshToken(String username, String refreshToken) {
        String storedRefreshToken = getRefreshToken(username);
        return refreshToken.equals(storedRefreshToken);
    }
}
