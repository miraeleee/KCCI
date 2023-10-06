package com.test.thread;

class ExamClass implements Runnable{
    @Override
    public void run(){
        for(int i=0; i<10; i++){
            try {
                Thread.sleep(1000); //sleep 예외처리해줘야함,static메서도
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 작동");
        }
    }
}

public class ThreadExam01 {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ExamClass()); //runable인터페이스를 인자에 넣어줌
        thread.start();

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
