package com.abumaster.example.crud.mockdata.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class AreaCodeDAOTest {


    @Autowired
    private AreaCodeDAO areaCodeDAO;

    @Test
    public void testOne() {
        areaCodeDAO.processData();
    }

}