package com.test.exam02;

public class StaticExam {
    public static void main(String[] args) {
        Number number1 = new Number();
        Number number2 = new Number();

        number1.b =3;
        System.out.println(number2.b);

       number1.a = 5;
        System.out.println(number2.a);

        number1.print2();
        Number.print1(); //인스턴스화 하지않아도 클래스 사용가능.(static변수는 Method Area에 저장)
    
        number1.print2();  

        //StaticExam staticExam = new StaticExam(); //인스턴스 생성
        //staticExam.sayHello(); //static을 붙이면 위에 sayHello()가 실행된다.
        //main 메소드는 static메소드이므로 실행 즉시 인스턴스화 과정을 거치지 않고 
        //바로 메모리에 로딩되어 실행되지만, sayHello 메소드는 일반 메소드인 관계로
        //인스턴스화가 되지않으면 생성 자체가 되지 않아서 main메소드에서 사용 불가 
        //sayHello(); //static하지않으면, 인스턴스화 시키면됨.

    }
        public void sayHello(){ //static을 붙이면 위에 sayHello()가 실행된다. 
        System.out.println("안녕!");
         }
       
}


class Number{
    //멤버변수에 있는 static은 같은 클래스내에 고정되어 여러 인스턴스끼리 공유된다.  
    static int a = 0;
    int b = 0;

    public static void print1(){ // void라 리턴값x
        System.out.println("static method test!!");
    }
    public void print2(){
        System.out.println("instance method test!!!");
        hello();
    }
    public void hello(){
        System.out.println("Hello~");
    }

}



