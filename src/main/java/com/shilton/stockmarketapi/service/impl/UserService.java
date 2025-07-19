package com.shilton.stockmarketapi.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.shilton.stockmarketapi.domain.user.User;
import com.shilton.stockmarketapi.repository.UserRepository;
import com.shilton.stockmarketapi.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Transactional
    public void updateUser(User u) throws Exception {

        LOG.info("Saving Transaction...: {}", objectMapper.writeValueAsString(u));
        userRepository.save(u);
        LOG.info("Saved...{}", objectMapper.writeValueAsString(u));
    }
}
