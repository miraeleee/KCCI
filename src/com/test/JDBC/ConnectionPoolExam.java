package com.test.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.test.member.Member;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolExam {
    public static void main(String[] args) {
        HikariConfig hikariConfig = new HikariConfig();
        // 기본설정
        hikariConfig.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        hikariConfig.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
        hikariConfig.setUsername("springdev");
        hikariConfig.setPassword("12345");
        hikariConfig.setConnectionTestQuery("select sysdate from dual"); //dual 가상테이블임
        hikariConfig.setMaximumPoolSize(10); // 동시접속자수 제한
        hikariConfig.setLeakDetectionThreshold(30000); // 접속로딩시간
        hikariConfig.setPoolName("Oracle-HirariCP"); // 시스템띄울떄 consol창에 띄우는 것(큰의미x)

        String query = "select id,name,gender,age from tbl_test order by id desc";
        Connection con;
        Statement stmt;
        ResultSet rs;
        HikariDataSource ds = new HikariDataSource(hikariConfig);

        List<Member> list  = new ArrayList<>(); 

        try {
            con = ds.getConnection(); //
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);


            while(rs.next()){ //next는 커서를 이동해주는 역할, 더이상 값이 없으면 false를 출력
                list.add(new Member.Builder()
                .id(rs.getInt("id"))
                .name(rs.getNString("name"))
                .gender(rs.getString("gender"))
                .age(rs.getInt("age"))
                .build()
                );
            }
            // 사용후 닫아줘야한다. 
            if(rs != null)rs.close();
            if(stmt != null)rs.close();
            if(con != null)rs.close();  

        } catch (SQLException e) {
            e.printStackTrace();
        }

        list.stream().forEach(l->System.out.println("번호:"+ l.getId()+"\t"
                                +"이름 :" + l.getName()+"\t"
                                +"성별 :"+ l.getGender()+"\t"
                                +"나이 :"+ l.getAge()
                                )                          
                                );


    }   
}
