package com.test.http;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.net.httpserver.*;

public class HttpServerManager {
    private final int BACKLOG = 0; //상수화 선언
    private HttpServer server = null;
    

    //생성자 생성되면서 createServer메소드를 호출해서 실행
    public HttpServerManager(String host, int port)throws IOException{
        createServer(host,port);
    }
    //서버 생성 : 서버 작동에 필요한 기본 정보 즉,context를 생성
    private void createServer(String host, int port) throws IOException{
        //HTTP SERVER생성
        //백로그: 네트워크 포트에서 패킷을 쌓아두는 커널(공간)의 큐 크기, 0이면 시스템 기본값 사용
        this.server = HttpServer.create(new InetSocketAddress(host, port),BACKLOG);
        //HTTP SERVER Context 설정
        server.createContext("/", new RootHandler());
    }
    //서버 실행
    public void start(){
        server.start();
    }
    //서버 중지
    public void stop(int delay){
        server.stop(delay);
    }
    public static void main(String[] args) {
        HttpServerManager httpServerManager = null;
        try {
            System.out.println(
                String.format("시작 시간[%s]", new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date())) //SimpleDateFormat자바에서 자주사용되는 것으로 사람이 이해하기 편한 양식으로 표현해줌
            );
            //서버 생성
            httpServerManager = new HttpServerManager("localhost",3000);//host, port두 매개변수를 받는다.
            httpServerManager.start(); 

            //안전한 프로그램 종료를 위한 설정-> 다른 프로그램 명령문 진행과 상관없이 비동적으로 진행
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {

                @Override
                public void run() {
                    //종료 로그
                    System.out.println( 
                    String.format("종료 시간 [%s]", 
                    new SimpleDateFormat("yyyy-mm-dd HH:mm:ss").format(new Date()))
                    ); 
                   
                }
            }));

            //Enter입력시 종료
            System.out.println("종료하려면 Enter 키를 눌러주세요");
            System.in.read(); 

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            httpServerManager.stop(0);
        }
    }
}
class RootHandler implements HttpHandler{

   @Override
   public void handle(HttpExchange exchange) throws IOException { // OutputStream자바에서 입출력시 사용되는 매소드임
       OutputStream responseBody  = exchange.getResponseBody();

       try {//예외처리 발생 방지목적
           StringBuilder sb = new StringBuilder();
           sb.append("<!DOCTYPE html>");        
           sb.append("<html>");
           sb.append("   <head>");
           sb.append("<meta charset=\"UTF-8\">");
           sb.append("   </head>"); 
           sb.append("   <body>");
           sb.append("    <h1>내가 만든 웹서버!!</h1>");
           sb.append("    <span>Method: "+ exchange.getRequestMethod() + "</span><br>");
           sb.append("    <span>URI: "+ exchange.getRequestURI() + "</span><br>"); 
           sb.append("    <span>PATH: "+ exchange.getRequestURI().getPath() + "</span><br>"); 
           sb.append("    <span>QueryString: "+ exchange.getRequestURI().getQuery() + "</span><br>"); 
           sb.append("   </body>");
           sb.append("/<html>");  

           ByteBuffer bb = Charset.forName("UTF-8").encode(sb.toString()); //ByteBuffer도 stream의..여기에 값을 담을수있음
           int contentLength = bb.limit(); // limit() -> 버퍼 내 쌓인 데이터 크기
           byte[] content = new byte[contentLength]; //배열사이즈 contentLength만큼
           bb.get(content,0, contentLength); // 빈공간 정리..

           //클라이언트에게 보낼 Response header정보 설정
           Headers headers = exchange.getResponseHeaders();
           headers.add("Content-Type", "text/html;charset=UTF-8");
           headers.add("Content-Length", String.valueOf(contentLength));

           //클라이언트에게 보낼 상태 코드와 Response Body 사이즈 설정 
           exchange.sendResponseHeaders(200, contentLength);

           //네트워크 스트림으로 바이트 단위로 저장된 정보를 클라이언트 전달 
           responseBody.write(content);

           responseBody.close();
        } catch (Exception e) {
           e.printStackTrace();//에러난것을 출력시켜줌

           if(responseBody != null){
               responseBody.close();
           }
           }finally{
               exchange.close();
           }
   }

}
