package form.test.http;

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
      String address = "http:127.0.0.1/jsp/jdbctest_oracle.jsp";

      // URL Query --> 파라미터
      Map<String,String> params = new HashMap<>();
      params.put("rname","최지훈");
      params.put("gender","남자");
      params.put("age","47");

      // Request 헤더정보
      String[] headers = {"content-type","application/x-www-form-urlencoded"};

      //POST방식으로 서버에 값을 전달하고 Request처리를 하고 Response로 페이지 Body를 가져옴
      HttpClientRun.post(address, params, headers);

      //GET방식으로 서버에 페이지 요청을 해서 Response로 페이지 Body를 가져 옴
      HttpClientRun.get(address);
        
    }
}
//get처리
class HttpClientRun{
    public static void get(String address) throws Exception{
      String[] headers = {"content-type","text/html"};
      HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build(); //빌드패턴,http버전으로 
      //방법1.
      String result = client.sendAsync(
        HttpRequest.newBuilder(new URI(address))//빌드패턴으로 설정값 받음
          .GET().headers(headers).build(), //GET방식으로 요청 request
          HttpResponse.BodyHandlers.ofString() // response를 문자열로 받겠다. 
      ).thenApply(HttpResponse::body) //thenApply 메소드로 body를 받음 //람다표기법 클래스::매소드
      .get().toString(); //get메소드로 body에 내용을 받아서 문자열타입으로 변환
      System.out.println("HTTP 본문:"+ result);

    }
    //post 처리
    public static void post(String address, Map<String,String> params,String[] headers) throws Exception{
      BodyPublisher body = BodyPublishers.ofString(getFormDataAsString(params));
      HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();

      //방법2.
      HttpResponse<String> response = client.send(
         HttpRequest.newBuilder(new URI(address))
            .POST(body).headers(headers).build(),//빌드 패턴으로 설정값 받음
           HttpResponse.BodyHandlers.ofString());//post방식 요청
           System.out.println(response);//response
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
  }
    return formBodyBuilder.toString();
}
//요즘방식