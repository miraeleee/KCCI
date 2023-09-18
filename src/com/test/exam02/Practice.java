package com.test.exam02;

interface InterfaceTest{
    public void print1(); //인터페이스 메소드는 {}없음
    public void print2();
}
class InterfaceChild implements InterfaceTest{
    @Override
    public void print1(){
    System.out.println("aa");    
    }
    @Override
    public void print2(){
        System.out.println("bb");
    }
}

public class Practice {
    public static void main(String[] args) {
        InterfaceChild interfaceChild = new InterfaceChild();
        interfaceChild.print1();
        
    }
}



