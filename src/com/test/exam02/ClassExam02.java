package com.test.exam02;

public class ClassExam02 {
    public static void main(String[] args) {
        Parent parent = new Parent();
        //추상클래스는 인스턴스화를 시키지 못함.
        // AbstractClass abstractClass = new AbstractClass(); 
        Child1 child1 = new Child1();
        Child2 child2 = new Child2();
        child1.printHello();
        child2.print1(); 
        child2.print2();

    }
}
class Parent{
    int a = 10;
    int b = 20;
    public void printHello(){
        System.out.println("Hello");
    }
}
//JAVA는 다중상속 지원X
class Child1 extends Parent{
    @Override//Annotation -->컴파일러에게 통지..상속받은 메소드의 내용을 수정하겠다.
    public void printHello(){
        //super.printHello();
        System.out.println("안녕");
    }
}
//추상클래스
abstract class AbstractClass{
    int c = 100;
    int d = 200;

    //추상클래스는 실행부가 있는 일반 메소드도 가질 수 있음.
    //일반 메소드
    public void print1(){
        System.out.println("#1 출력");
    }
    //abstract 매소드는 {}중괄호가 없음.
    //추상매소드
    abstract public void print2();

}
//추상클래스는 상속을 해야지만, 멤버변수 및 매소드 사용가능함
//추상클래스 상속시 @override
//추상클래스는 업무지시서의 역할.., Framework
class Child2 extends AbstractClass{

    @Override
    public void print2() {
        System.out.println("추상클래스...");
    }

}
