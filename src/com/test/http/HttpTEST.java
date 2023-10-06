package com.test.http;

import java.io.BufferedReader;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTEST {
    public static void main(String[] args) {
        String targetURL = "http://127.0.0.1/servlet/jdbctest_resgistry";
        String parameters = "rname=최만우&gender=남성&age=38";
        TesthttpRequest.testhttpRequest(targetURL, parameters);
    }
}

class TesthttpRequest{
    public static void testhttpRequest(String targetURL, String parameters){
       
        HttpURLConnection connection = null;
        //connection생성
        try {         URL url = new URL(targetURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //컨텐트 타입 수동지정
            connection.setRequestProperty("Content-Language", "ko-KR");//locale --->거주지역환경:시간,화폐,도량형 등을 의미
            connection.setUseCaches(false);
            connection.setDoOutput(true); //OutputStream으로 POST데이터를 넘겨주겠다는 옵션

            //Request 처리
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(parameters.getBytes("utf-8"));
            wr.flush();
            wr.close();

            //Response 처리
            int responseCode = connection.getResponseCode(); //responseCode에 request한 값을 담는다.   
            BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

            //대량의 데이터 처리방법 stringbuffer, string builder
            StringBuilder response = new StringBuilder();
            String line; //한줄한줄 읽는다는 의미
            while((line = rd.readLine()) != null ){
                response.append(line).toString();
                response.append("\r\n");
            }
            rd.close();

            System.out.println("HTTP 응답 코드:"+ responseCode);
            System.out.println("HTTP 응답 헤더"+ connection.getHeaderFields());
            System.out.println("HTTP BODY :"+ response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(connection != null)
            connection.disconnect();
        }
        //약간 이전방식
    }
}
