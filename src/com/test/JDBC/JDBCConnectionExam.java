package com.test.jdbc;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.*; //으로도 모든 메소드 import가능
import java.util.ArrayList;
import java.util.List;
// import java.beans.Statement;
//import java.sql.SQLException;
import com.test.member.Member;

public class JDBCConnectionExam {

    /**
     * @param args
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //오라클 전용 프로토콜로 데이터 베이스 처리
        String uri = "jdbc:oracle:thin:@localhost:1521:xe";
        String userid = "springdev";
        String userpw = "12345";
        String query = "select id,name,gender,age from tbl_test order by id desc";

        Connection con;
        Statement stmt;
        ResultSet rs;

            Class.forName("oracle.jdbc.driver.OracleDriver");//JDBC 드라이버를 로딩
            con = DriverManager.getConnection(uri, userid, userpw); //uri, userid, userpw값으로 미리 연결시도
            stmt = con.createStatement();//sql이 실행할 수 있는 환경 생령
            rs = stmt.executeQuery(query);//sql문을 실행해서 그 결과를 ResultSet이란 collection 객체에 담는다. 
            
            List<Member> list = new ArrayList<>();
        // rs.netx() -> db에서 읽어와서 ResutlSet에 저장된 값들을 한줄씩 읽고 더 이상 읽을 줄이 없이 없으면 false을 리턴
        while(rs.next()){
            list.add(new Member(rs.getInt("id")
                     , rs.getString("name")
                     , rs.getString("gender")
                     ,rs.getInt("age")));
            }
            // 배열전용for문으로 출력(할것!)
            for(Member member:list){
                System.out.println("번호:"+ member.getId()+"\t"
                                    +"이름 :" + member.getName()+"\t"
                                    +"성별 :"+ member.getGender()+"\t"
                                    +"나이 :"+ member.getAge());
            }           

    }
}