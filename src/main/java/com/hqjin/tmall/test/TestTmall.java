package com.hqjin.tmall.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestTmall {
    public static void main(String[] args){
        try{
            Class.forName("com.mysql.jdbc.Driver");

        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try(
            Connection c=
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_springboot?" +
                            "useUnicode=true&characterEncoding=utf-8","root","admin");
            Statement s=c.createStatement();
        ){
            for(int i=10;i<20;i++){
                String sqlForma="insert into category values (null,'测试分数类%d')";
                String sql=String.format(sqlForma,i);
                s.execute(sql);
            }
            System.out.println("已经成功创建10条分类");
        }catch (SQLException e ){
            e.printStackTrace();;
        }
    }
}
