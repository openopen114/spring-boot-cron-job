package com.openopen.service;


import com.openopen.dao.PersonMapper;
import com.openopen.dao.RolesMapper;
import com.openopen.model.Person;
import com.openopen.model.Roles;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import sql.Druid;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

@Service
public class RolesService {
    private static final Logger logger = LoggerFactory.getLogger(RolesService.class);


    private static SqlSessionFactory sqlSessionFactory = null;

    static {
        String resource = "mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }



    public Roles getRolesById(){
        Roles model = new Roles();
        SqlSession sqlSession = null;
        Connection conn = null;
        try {
            //取得連接
            conn = Druid.getConn();
            if (conn != null) {
                System.out.println("conn is good to go");

            }

            sqlSession = sqlSessionFactory.openSession(conn);

            if(sqlSession != null){
                System.out.println("========> sqlSession good to go");
            }


            RolesMapper rolesMapper = sqlSession.getMapper(RolesMapper.class);


            model = rolesMapper.selectByPrimaryKey(1);

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


        return model;
    }



}
