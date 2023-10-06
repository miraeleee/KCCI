package com.test.http;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpClientTest {
    public static void main(String[] args)throws Exception{

      //URL 주소 설정
      String address_get = "http://127.0.0.1/jsp/jdbctest_oracle.jsp";
      String address_post = "http://127.0.0.1/servlet/jdbc_registry";

      //URL Query --> 파라미터
      Map<String,String> params = new HashMap<>();
      params.put("rname","최지훈");
      params.put("gender","남자");
      params.put("age","47");

      //Request 헤더정보
      String[] headers = {"content-type","application/x-www-form-urlencoded"}; //타입,HTML Form 형태
      
      //POST 방식의 경우 request body 값에 데이터를 넣어서 보내는 것이 일반적
      //POST방식으로 서버에 값을 전달하고 Request처리를 하고, Response로 페이지 Body를 함께 가져옴
      HttpClientRun.post(address_post, params, headers);

      //GET으로 body를 담아 보낼 수는 있지만, 데이터 검색역할의 의미가 강하므로, 표현을 지양한다.
      //so,thenApply메소드로 body를 가져옴
      //GET방식으로 서버에 페이지 요청을 해서 Response로 페이지 Body를 가져 옴
      HttpClientRun.get(address_get);
        
    }
}
    
  class HttpClientRun{
    //get처리
    public static void get(String address_get) throws Exception{
      String[] headers = {"content-type","text/html"}; //html데이터
      HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build(); //빌드패턴,http버전으로 
      //방법1.
      String result = client.sendAsync(
          HttpRequest.newBuilder(new URI(address_get))//빌드패턴으로 설정값 받음
          .GET().headers(headers).build(), //GET방식으로 요청 request
          HttpResponse.BodyHandlers.ofString() // response를 문자열로 받겠다. 
          ).thenApply(HttpResponse::body) //thenApply 메소드로 body를 받음 //람다표기법 클래스::메소드
          .get().toString(); //get메소드로 body에 내용을 받아서 문자열타입으로 변환
      System.out.println("HTTP 본문:"+ result);

    }
    //post 처리
    public static void post(String address_post, Map<String,String> params,String[] headers) throws Exception{
      BodyPublisher body = BodyPublishers.ofString(getFormDataAsString(params)); //body로 받기위해 BodyPublishers클래스 사용
      HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();

      //방법2.(여기서는 보내는것은되고 redirect까지는 안됨)
      HttpResponse<String> response = client.send(
           HttpRequest.newBuilder(new URI(address_post))//빌드 패턴으로 설정값 받음
          .POST(body).headers(headers).build(),//post방식 요청
           HttpResponse.BodyHandlers.ofString()//response
           );
           System.out.println(response);
    }
    
    public static String getFormDataAsString(Map<String,String> formData){
      StringBuilder formBodyBuilder = new StringBuilder();
      // formData.entrySet() ---> {{"name","오상훈"},{"gender","남자"},{"age","58"}...}
      for(Map.Entry<String,String> singleEntry : formData.entrySet()){ //map데이터를 배열 형식으로 바꾼다. 

      if(formBodyBuilder.length() > 0){
        formBodyBuilder.append("&"); //name=오상훈&gender=남성&age=58
      }
      formBodyBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
      formBodyBuilder.append("=");
      formBodyBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
      }      
      return formBodyBuilder.toString();
    }
  }
//요즘방식