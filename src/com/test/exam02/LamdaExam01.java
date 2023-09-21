package com.test.exam02;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

// 사용자가 생성하는 람다 표현식 2가지
@FunctionalInterface //여기 아래에 나오는 인터페이스는 람다표현식으로 만들때 사용할 인터페이스임. 컴파일러에게 공지
interface MyLamdaFunction{
    int sum(int a, int b); //인터페이스내에 메소드 선언
}
@FunctionalInterface
interface MyPrint{
    String printLamda();
}
public class LamdaExam01 {
        public static void main(String[] args) {
        // 인터페이스는 인스턴스화 못하고, 변수 선언만하는것임. 변수선언후 람다표현식
        // 람다식을 이용한 익명함수: 2개의 인자를 받아서 메소드로 계산 후 그 값을 반환하는 형태
        MyLamdaFunction lamdaFunction = (int a, int b)-> a+b; 
        System.out.println(lamdaFunction.sum(3,4));

        // 람다식을 이용한 익명함수: 인자없이 문자열만 반환(화살표 리턴의 의미임)
        MyPrint myPrint = () -> "Hello World"; //람다표현식으로 구현체를 만듦
        System.out.println(myPrint.printLamda());

        // 기존 만들어진것을 람다 표현식을 위한 인터페이스를 활용하는 예
        // 인터페이스 종류: Supplier, Consumer, Function, Predicate
    
        // supplier: 매개변수 없이 반환값만 갖는 함수형 인터페이스
        Supplier<String> supplier = () -> "매게변수 없이 반화값만 갖는 함수형 인터페이스";
         System.out.println(supplier.get()); //위에서 정의한 람다실행식으로 정의한 메소드를 실행

         // Consumer: 객체 T를 매게변수로 받아서 사용하며, 반환값은 없는 함수형 인터페이스
        Consumer<String> consumer = (str)->System.out.println(str.split(",")[1]);
        Consumer<String> consumer1 = (s)->System.out.println("오늘은"+s+"요일");
        consumer.accept("수");

        // Function: 객체 T를 매게변수로 받아서 처리한 후 R로 반환하는 함수형 인터페이스
        Function<String, Integer> function = (str) -> str.length();
        System.out.println(function.apply("안녕하세요? 좋은하루"));

        //Predicate: 객체 T를 매게변수로 받아 처리한 후 Boolean으로 반환
        Predicate<String> predicate = (str)->str.equals("Hello");
        System.out.println(predicate.test("Hello1"));
        
        // 메소드 참조(method reference)
        // 클래스 이름::메소드이름 또는 참조변수이름::메소드이름
        // String s; -->s가 참조변수 이름
        Function<String, Integer> function2 = String::length;
        System.out.println(function2.apply("좋은 아침"));

        //생성자 참조(constructor reference) //배열은 참조형으로 표시 //잘 사용은x 참고만!
        //Function<Interger, double[]> function3 = a -> double[]::new;
    }

}
