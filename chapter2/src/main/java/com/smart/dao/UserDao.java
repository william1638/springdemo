package com.smart.dao;

import com.smart.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by William on 2017/10/12.
 */
@Repository   //1通过spring注解定义一个DAO
public class UserDao {

    //根据用户名查询用户的SQL语句
    private final static String MATCH_COUNT_SQL =  "Select count(*) FROM t_user WHERE user_name=? and password=?";

    private final static String UPDATE_LOGIN_INFO_SQL = "UPDATE t_user SET last_visit=?,last_ip=?,credits=? WHERE user_id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int getMatchCount(String userName,String password){

        return (int)jdbcTemplate.queryForObject(MATCH_COUNT_SQL,int.class,new Object[]{userName,password});
    }

    public User findUserByUserName(final String userName){
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserId(resultSet.getInt("user_id"));
                        user.setUserName(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                });
        return user;
    }

    public void updateLoginInfo(User user){
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL,new Object[]{user.getLastVisit(),user.getLastIp()
        ,user.getCredits(),user.getUserId()});
    }

}
