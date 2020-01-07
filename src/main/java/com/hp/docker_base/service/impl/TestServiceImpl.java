package com.hp.docker_base.service.impl;

import com.hp.docker_base.bean.User;
import com.hp.docker_base.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private JdbcTemplate jdbcTemplate;



    @Override
    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user");
        return list;
    }


}
