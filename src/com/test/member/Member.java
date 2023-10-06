package com.test.member;

import oracle.net.aso.l;

public class Member { //**DTO(Data Transfer Object)
        private int id;
        private String name;
        private String gender;
        private int age;

        //원래 클래스가 메모리내에 생성(인스턴스화) 되기 위해서는 반드시 생성자가 정의되어 있어야 함.
        //아래와 같이 인자가 없는 생성자의 경우는 따로 정의를 하지않아도 컴파일러가 소스를 컴파일할때 
        //소스에 자동으로 삽입을 해 줌.
        //생성자 정의(매게변수가 있을경우 순서, 개수 맞춰야함.)
        public Member(){
             System.out.println("인자가 없는 생성자를 통해 객체가 생성되었습니다.");
        }
        
        public Member(String name){
             System.out.println("인자가 하나인 생성자를 통해 객체가 생성되었습니다.");
        }

        public Member(int id, String name, String gender, int age){ //인자가 3개인 생성자 정의
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;

        System.out.println("멤버 변수가 초기화 되었습니다.");
        }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender = gender;
    }
    public int getAge(){
        return this.age;
    }
    public void setAge(int age){
        this.age = age;
    }
    //build 패턴
    //내부클래스 생성
    public static class Builder{
        private int id;
        private String name;
        private String gender;
        private int age;

        public Builder id(int id){       this.id = id;
            return this; // 메소드 종료후 내부 클래스인 BUild클래스가 종료되는것을 방지한다. 
        }
        public Builder name(String name){
            this.name = name;
            return this;// 멤버변수 주소 리턴
        }
        public Builder gender(String gender){
            this.gender = gender;
            return this;
        }
        public Builder age(int age){
            this.age = age;
            return this;
        }
        public Member build(){
            if(id== 0 || name ==null || gender ==null || age ==0)
            throw new IllegalStateException("멤버클래스에 값이 없네요");
            return new Member(id, name, gender, age); // 값을 받고, 인자 4개인아웃클래스에 멤버변수에 값을 넣어줌.
        }

    }
}
