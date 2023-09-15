package com.test.exam01;

public class Sample {
    int x,y; //인스턴스 변수
    static int result; //클래스 변수

    void add(int a, int b){
        x=a;
        y=b;
        int sum = x+y;
        System.out.println("Sum="+sum);
    }
    public static void main(String[] args) {
        Sample obj = new Sample();
        obj.add(10,20);
    }
    
}
