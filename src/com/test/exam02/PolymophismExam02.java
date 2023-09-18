package com.test.exam02;

public class PolymophismExam02 {
    public static void main(String[] args) {
        new AnimalMove(new Human1()); //이떄에 메모리 실행됨.
        new AnimalMove(new Tiger1());
        new AnimalMove(new Eagel1());
    }
}

class AnimalMove{ //스프링배울떄 의존성 주입의 기본 작동 원리
    Animal1 Animal1; //클래스의 멤버변수
    public AnimalMove(Animal1 Animal1){
        this.Animal1 = Animal1;
        Animal1.move();
    }
}

interface Animal1{
    public void move();
}
class Human1 implements Animal1{
    @Override
    public void move(){
        System.out.println("사람이 두발로 걷습니다");
    };
}
class Tiger1 implements Animal1{
  @Override
    public void move(){
        System.out.println("호랑이가 네발로 걷습니다");
    };
}
class Eagel1 implements Animal1{
  @Override
    public void move(){
        System.out.println("독수리가 하늘을 납니다.");
    };
}