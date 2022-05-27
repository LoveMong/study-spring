package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:../ch3/src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {

    @Autowired
    UserDao userDao;

    @Test
    public void deleteUser() throws Exception {
        userDao.deleteAll();
        User user = new User("mmong", "1234", "Leemong", "mong@mong", new Date(), "facebook", new Date());
        userDao.insertUser(user);

        int rowCnt = userDao.deleteUser(user.getId());

        assertTrue(rowCnt == 1);


    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2022, 2, 22);

        userDao.deleteAll();
        User user = new User("mmong", "1234", "Leemong", "mong@mong", new Date(cal.getTimeInMillis()), "facebook", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt==1);

        user.setPwd("1111");
        user.setEmail("update@ccc.com");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt == 1);

        User user2 = userDao.selectUser(user.getId());
        System.out.println("user2 = " + user2);
        System.out.println("user = " + user);
        assertTrue(user.equals(user2));


    }
}