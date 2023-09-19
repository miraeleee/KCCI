package com.test.exam02;
/*
 Option 객체란? null처리를 가급적 깔끔하게 하기 위한 클래스임
 - Null Point Exception 에러를 방지하기 위한  Wrapper 클래스로 
 
 */

import java.util.Optional;

public class OptionalExam01 {
    public static void main(String[] args) {
        String str1 = null;
        String str2 = "010-123-1234";

        String phoneNumber = str1 == null? "NO" : str1.replace("-","");

       /*
        String phonNumber = Optional.ofNullable(str2).orElse("NO");
        if(phonNumber.equals("NO")){
            phonNumber="잘못된 번호";
        System.out.println("고객에게 전화번호 제대로 입력하라고 얘기하세요.");}
        */
        //String str2 = null; 

        //String phonNumber = str2.replace("-", "");
        System.out.println("전화번호 = "+phoneNumber);

        /*
        try{
            if(str2.equals(str2)) System.out.println("str1과 str2가 같네요");
        }catch(NullPointerException e){
            System.out.println("str2가 null이네요");
        }
        */

    }
}
