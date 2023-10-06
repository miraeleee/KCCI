package com.test.thread;

class ExamClass04{
    public void examA(){ //synchronized를 이용한 비동기화 1)
        synchronized(this){
            System.out.println("ExamA 시작");
                for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                    System.out.println("철수 안녕!!");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("ExamA 끝");
        }
    }
    public synchronized void examB(){ //synchronized를 이용한 비동기화 2)
        System.out.println("ExamB 시작");
                    for(int i=0; i<10; i++){
            try {
                Thread.sleep(1000);
                System.out.println("영희 안녕!!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("ExamB 끝");
    }
}

public class ThreadExam04 {
    public static void main(String[] args) {
           ExamClass04 examClass04 = new ExamClass04();

           Thread thread1 = new Thread(new Runnable(){

            @Override
            public void run() {
                examClass04.examA();
            }
           });

            Thread thread2 = new Thread(new Runnable(){

            @Override
            public void run() {
                examClass04.examB();
            }
           });
           thread1.start();
           thread2.start();
    }
}
