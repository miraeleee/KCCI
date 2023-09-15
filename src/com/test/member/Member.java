package com.test.member;

public class Member { //DTO(Data Transfer Object)

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

        public Member(String name, String gender, int age){ 
        this.name = name;
        this.gender = gender;
        this.age = age;

        System.out.println("멤버 변수가 초기화 되었습니다.");
         }//인자가 3개인 생성자 정의

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
}
