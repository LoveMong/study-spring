package com.fastcampus.ch3;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:../ch3/src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DBConnectionTest2Test {
    @Autowired
    DataSource ds;

    @Test
    public void insertUserTest() throws Exception {
        User user = new User("mmong", "1234", "Leemong", "mong@mong", new Date(), "facebook", new Date());
        deleteAll();
        int rowCnt = insertUser(user);

        assertTrue(rowCnt == 1);
    }

    @Test
    public void selectUserTest() throws Exception {
        deleteAll();
        User user = new User("mmong", "1234", "Leemong", "mong@mong", new Date(), "facebook", new Date());
        int rowCnt = insertUser(user);
        User user2 = selectUser("mmong");

        assertTrue(user2.getId().equals("mmong"));
    }

    @Test
    public void deleteUserTest() throws Exception {
        deleteAll();
        int rowCnt = deleteUser("mmong");

        assertTrue(rowCnt == 0);

        User user = new User("m2ong", "1234", "Leemong", "mong@mong", new Date(), "facebook", new Date());
        rowCnt = insertUser(user);
        assertTrue(rowCnt == 1);

        rowCnt = deleteUser(user.getId());
        assertTrue(rowCnt == 1);

        assertTrue(selectUser(user.getId()) == null);



    }

    @Test
    public void updateUserTest() throws Exception {
        deleteAll();
        User user = new User("m2ong", "1234", "Leemong", "mong@mong", new Date(), "facebook", new Date());
        insertUser(user);

        User user2 = new User("m2ong", "1234", "update", "mong@mong", new Date(), "facebook", new Date());
        int rowCnt = updateUser(user2);

        assertTrue(rowCnt==1);




    }

    public int updateUser(User user) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "update user_info set name = ? where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, user.getName());
        pstmt.setString(2, user.getId());

        int rowCnt = pstmt.executeUpdate();

        return rowCnt;


    }

    public int deleteUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info where id = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, id);
        return pstmt.executeUpdate();

    }

    public User selectUser(String id) throws Exception {
        Connection conn = ds.getConnection();

        String sql = "select * from user_info where id = ?";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.setString(1, id);
        ResultSet rs = pstmt.executeQuery(); // select

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getString(1));
            user.setPwd(rs.getString(2));
            user.setName(rs.getString(3));
            user.setEmail(rs.getString(4));
            user.setBirth(new Date(rs.getDate(5).getTime()));
            user.setSns(rs.getString(6));
            user.setReg_date(new Date(rs.getTimestamp(7).getTime()));

            return user;

        }

        return null;
    }

    private void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user_info";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상

        int rowCnt = pstmt.executeUpdate(); // executeUpdate() :  insert, delete, update 에만 사용
    }

    public int insertUser(User user) throws Exception {
        Connection conn = ds.getConnection();

//        String sql = "insert into user_info values('mmong', '1234', 'Leemong', 'mong@mong', '2019-06-11', 'facebook', now())";
        String sql = "insert into user_info values(?, ?, ?, ?, ?, ?, now())";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection 공격, 성능향상
        pstmt.setString(1, user.getId());
        pstmt.setString(2, user.getPwd());
        pstmt.setString(3, user.getName());
        pstmt.setString(4, user.getEmail());
        pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
        pstmt.setString(6, user.getSns());

        int rowCnt = pstmt.executeUpdate(); // executeUpdate() :  insert, delete, update 에만 사용


        return rowCnt;
    }

    @Test
    public void main() throws Exception {
//        ApplicationContext ac = new GenericXmlApplicationContext("file:../ch3/src/main/webapp/WEB-INF/spring/**/root-context.xml");
//        DataSource ds = ac.getBean(DataSource.class);

        Connection conn = ds.getConnection(); // 데이터베이스의 연결을 얻는다.

        System.out.println("conn = " + conn);
        assertTrue(conn != null); // (Test에는 assert문 필요) 괄호 안에 조건식이 true면, 테스트 성공, 아니면 실패
    }

}