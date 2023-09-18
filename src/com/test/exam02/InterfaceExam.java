package com.test.exam02;

interface InterfaceTest {
    public void print1();
    public void print2(); //메소드 선언만 가능

}

class InterfaceChild implements InterfaceTest{

    @Override
    public void print1() {
     System.out.println("#1 출력");
    }

    @Override
    public void print2() {
     System.out.println("#2 출력");
    }
}

public class InterfaceExam{
    public static void main(String[] args){
        InterfaceChild interfaceChild = new InterfaceChild();
        interfaceChild.print1();
        interfaceChild.print2();
    }
}
