package com.sliit.ayu.ayuservice.service;

import com.sliit.ayu.ayuservice.model.RefreshTokenEntity;
import com.sliit.ayu.ayuservice.model.UserEntity;
import com.sliit.ayu.ayuservice.repository.RefreshTokenRepository;
import com.sliit.ayu.ayuservice.repository.UserRepository;
import com.sliit.ayu.ayuservice.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(MockitoJUnitRunner.class)
class CustomUserDetailsServiceTest {

    @InjectMocks
    private CustomUserDetailsService userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RefreshTokenRepository refreshTokenRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadUserByUsername() {
        Mockito.when(userRepository.findByUsername("testUser1")).thenReturn(getUserEntity(1));
        UserDetails userDetails = userDetailsService.loadUserByUsername("testUser1");
        assertNotNull(userDetails);
    }

    @Test
    void loadUserByUsernameError() throws RuntimeException {
        Mockito.when(userRepository.findByUsername("testUser1")).thenReturn(null);
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername("testUser1");
        } catch (RuntimeException e) {
            assertNotNull(e);
        }

    }


    @Test
    void storeRefreshToken() {
        userDetailsService.storeRefreshToken("user1", "token1");
    }

    @Test
    void getRefreshToken() {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setRefreshToken("token111");
        refreshTokenEntity.setUsername("userId");
        Mockito.when(refreshTokenRepository.findById("userId")).thenReturn(Optional.of(refreshTokenEntity));
        assertNotNull(userDetailsService.getRefreshToken("userId"));
    }

    @Test
    void validateRefreshToken() {
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();
        refreshTokenEntity.setRefreshToken("token111");
        refreshTokenEntity.setUsername("userId");
        Mockito.when(refreshTokenRepository.findById("userId")).thenReturn(Optional.of(refreshTokenEntity));
        assertTrue(userDetailsService.validateRefreshToken("userId", "token111"));
    }

    private UserEntity getUserEntity(int id){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserRole("admin");
        userEntity.setTitle("title");
        userEntity.setUsername("uname1");
        userEntity.setPassword("pwd1");
        userEntity.setEmployeeNumber("no001");
        return userEntity;
    }
}