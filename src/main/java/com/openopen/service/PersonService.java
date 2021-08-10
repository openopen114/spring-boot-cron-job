package com.openopen.service;

import com.openopen.dao.PersonMapper;
import com.openopen.model.Person;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sql.Druid;

import java.sql.Connection;
import java.sql.SQLException;


@Service
public class PersonService {


    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);


    private static SqlSessionFactory sqlSessionFactory = null;


    public Person getPersonByid() {

        Person person = new Person();
        SqlSession sqlSession = null;
        Connection conn = null;
        try {
            //取得連接
            conn = Druid.getConn();
            if (conn != null) {
                System.out.println("conn is good to go");
            }
            sqlSession = sqlSessionFactory.openSession(
                    conn
            );
            PersonMapper personMapper = sqlSession.getMapper(PersonMapper.class);


            person = personMapper.selectByPrimaryKey("uuuuu3");

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            e.printStackTrace();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
                sqlSession = null;
            }

            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return person;

    }


}
