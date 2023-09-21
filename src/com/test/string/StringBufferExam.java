package com.test.string;

public class StringBufferExam {
    public static void main(String[] args) {
        // StringBuffer string문자를 보조,,다양하게 사용하게 하기위함 
        // StringBuffer은 대용량 데이터 처리와 멀티쓰레드 환경에서 우수한 성능을 발휘
        // StringBuild는 StringBuffer보다는 멀티 쓰레드 환경에서의 안정성은 다소 낮지만, StringBuffer보다 성능이 우수
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Hello");
        stringBuffer.append(" ");
        stringBuffer.append("Ny World");
        String result1 = stringBuffer.toString();
        System.out.println(result1);

        stringBuffer.insert(0, "Everyone, ");
        String result2 = stringBuffer.toString();
        System.out.println(result2);

        // 문자열 0부터 시작해서 8번째 자리가지의 문자열을 리턴
        String result3 = stringBuffer.substring(0, 8);
        System.out.println(result3);

    }   
}
