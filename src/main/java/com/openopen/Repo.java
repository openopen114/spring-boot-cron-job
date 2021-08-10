package com.openopen;

import sql.Druid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repo {






    //測試用
    public String getTest() throws SQLException {

        System.out.println(" ===> getTest");
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //取得連接
            conn = Druid.getConn();

			/*
			 *
			 *
                select * from person
                where name = 'u2'
			 */

            //SQL
            String query =
                    " select * from person " +
                            "where name = 'u2'  " ;

            //預處理SQL
            preparedStatement = null;
            preparedStatement = conn.prepareStatement(query);

            //查詢請求
            resultSet = preparedStatement.executeQuery();

            //結果集
            while (resultSet.next()) {
                System.out.println(resultSet.getString("id"));
            }


            // Close
            resultSet.close();


        } catch (Throwable e) {
            if (conn != null) {
                try {
                    //Roll back
                    conn.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }

        } finally {
            if (conn != null) {
                try {
                    preparedStatement.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }


        return null;
    }





}
