package com.shilton.stockmarketapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.shilton.stockmarketapi.domain.User;
import com.shilton.stockmarketapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Transactional
    public void updateUser(User u) throws Exception {

        LOG.info("Saving Transaction...: " + objectMapper.writeValueAsString(u));
        userRepository.save(u);
        LOG.info("Saved..." +  objectMapper.writeValueAsString(u));
    }
}
