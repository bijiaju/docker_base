package com.hp.docker_base.controller;

import com.hp.docker_base.config.ExecSQL;
import com.hp.docker_base.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cs")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private ExecSQL execSQL;

    @GetMapping("/list")
    public List<Map<String, Object>> queryUsers() throws Exception {
        //List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from tb_user");
        execSQL.operate();
        return testService.getAllUsers();
    }

}
