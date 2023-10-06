package com.test.thread;

public class ThreadExam03 {
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() { //runnable익명함수로 인자로 넣어줌

            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    try {
                        Thread.sleep(1000); //sleep 예외처리해줘야함,static메서도
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 작동");
                }
            }
            
        }).start(); //Thread().start()
        for(int i=0; i<10; i++){
            Thread.sleep(1000);
            System.out.println("철수 안녕!!");
        }
        for(int i=0; i<10; i++){
            Thread.sleep(1000);
            System.out.println("영희 안녕!!");
        }
    }
}
