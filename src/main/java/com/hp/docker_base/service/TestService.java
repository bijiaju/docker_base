package com.hp.docker_base.service;

import com.hp.docker_base.bean.User;

import java.util.List;
import java.util.Map;

public interface TestService {
    List<Map<String, Object>> getAllUsers();
}
