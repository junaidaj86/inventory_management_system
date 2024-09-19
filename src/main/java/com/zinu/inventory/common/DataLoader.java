package com.zinu.inventory.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

//@Component
public class DataLoader {

    private final JdbcTemplate jdbcTemplate;

    @Value("classpath:data.sql")
    private Resource dataScript;

    public DataLoader(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void loadData() throws IOException {
        String sql = new String(Files.readAllBytes(Paths.get(dataScript.getURI())));
        jdbcTemplate.execute(sql);
    }
}

