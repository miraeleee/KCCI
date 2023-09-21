package com.test.jdbc;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.ResultSet;
import java.sql.*; //으로도 모든 메소드 import가능
import java.util.ArrayList;
import java.util.List;
import com.test.member.Member;

public class JDBCConnectionExam {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //오라클 전용 프로토콜로 데이터 베이스 처리
        String uri = "jdbc:mariadb://localhost:3306/springdev";
        String userid = "springdev";
        String userpw = "tlsfk656!";
        String query = "select id,name,gender,age from tbl_test order by id desc";
        //String query1 = "insert into tbl_test (id,name,gender,age) values (tbl_test_seq.nextval,\'김길동\',\'남성\',23)";//(tbl_test_seq.nextval >> 자동생성 요청명령문
        String query1 = "insert into tbl_test (name,gender,age) values (\'김길동\',\'남성\',23)";

        Connection con;
        Statement stmt;
        ResultSet rs;

            //Class.forName("oracle.jdbc.driver.OracleDriver");//JDBC 드라이버를 로딩
            Class.forName("org.mariadb.jdbc.Driver");//Mariadb 드라이버를 로딩
            con = DriverManager.getConnection(uri, userid, userpw); //uri, userid, userpw값으로 미리 연결시도
            stmt = con.createStatement();//sql이 실행할 수 있는 환경 생성
            stmt.executeQuery(query1);//isert,update,dele값은 DML명령문을 실행
            rs = stmt.executeQuery(query);//sql문을 실행해서 그 결과를 ResultSet이란 collection 객체에 담는다. 
    
            
        List<Member> list = new ArrayList<>();
        // rs.net() -> db에서 읽어와서 ResutlSet에 저장된 값들을 한줄씩 읽고 더 이상 읽을 줄이 없이 없으면 false을 리턴
        while(rs.next()){
            // 생성자 값을 넣어서(set) 하는방법(순서상관0)
            // list.add(new Member(rs.getInt("id")
            //          , rs.getString("name")
            //          , rs.getString("gender")
            //          , rs.getInt("age")));
            // }
            // 빌드패턴을 이용해서 member클래스에 값을 불러오는 방법(가독성이 좋음)(순서상관0)-여러 장점이 있어 자주 사용됨.
            list.add(new Member.Builder()
                        .id(rs.getInt("id"))
                        .name(rs.getString("gender"))
                        .gender(rs.getString("gender"))
                        .age(rs.getInt("age"))
                        .build());
        }
            /*
            // 배열전용for문으로 출력(할것!)
            for(Member member:list){
                System.out.println("번호:"+ member.getId()+"\t"
                                    +"이름 :" + member.getName()+"\t"
                                    +"성별 :"+ member.getGender()+"\t"
                                    +"나이 :"+ member.getAge());
            } 
             */
            //함수형 프로그램으로 출력(코드 단축)
            list.stream().filter(member->member != null).forEach(l->System.out.println("번호:"+ l.getId()+"\t"
                                    +"이름 :" + l.getName()+"\t"
                                    +"성별 :"+ l.getGender()+"\t"
                                    +"나이 :"+ l.getAge()));
            //성능에 영향이 있으므로 파이프라인 사용했으면, 닫아줘야함.          
            if(rs != null)rs.close();
            if(stmt != null)rs.close();
            if(con != null)rs.close();    
}
}