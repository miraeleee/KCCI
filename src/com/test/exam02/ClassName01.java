package com.test.exam02;

import com.test.member.Member;

public class ClassName01 {
    //하나의 자바파일내에 여러개의 클래스가 있는경우에 파일명과 같은 클래스가  반드시 있어야 하고
    //그 클래스에만 pubic을 붙일 수 있음.  
    public static void main(String[] args) {
        /*Sum sum1 = new Sum(); //클래스 참조형 변수 선언 및 정의->인스턴스화
        System.out.println(sum1.sumA);
        System.out.println(sum1.sumB);
        sum1.sumA =3;
        sum1.sumB =4;
        System.out.println(sum1.sumA);
        System.out.println(sum1.sumB);

        Sum sum2 = new Sum();
        System.out.println(sum2);
        System.out.println(sum1 ==sum2);

        sum2.sumA = 13;
        sum2.sumB = 15;
        */
        Sum sum = new Sum();
        sum.setSumA(3);
        sum.setSumB(4);
        System.out.println("Sum클래스의 멤버변수 sumA="+sum.getSumA()); 
        System.out.println("Sum클래스의 멤버변수 sumB="+sum.getSumB()); 
        System.out.println(4+"와"+5+"의 합은"+"입니다.");

        Member member = new Member("김철수","남성",34);
        // member.setName("김철수");
        // member.setGender("남자");
        // member.setAge(34);

        System.out.println("이름 :"+member.getName()+"/"+
                            "성별 :"+member.getGender()+"/"+
                            "나이 :"+member.getAge());

        
        new Member();//익명객체 생성(객체명 x)
        new Member("김민수");

        
    }
    
}//클래스-객체의 속성과 액션을 코드화한 템플리트(틀)
class Sum{
     int sumA; //멤버변수
     int sumB; //멤버변수
    
    // private 접근제한자는 메소드를 통해서만 접근이가능
    public int getSumA(){ //이러한 네이밍 규칙으로 Private 멤버변수의 값을 가져오는 메소드를 getter메소드라고 함.
        return this.sumA; //this는 자신이 속한 클래스를 의미   
    } 
    public void setSumA(int sumA){ //멤버변수와 다른 지역변수임.(다름)
        this.sumA = sumA; //setter 메소드
    }

    public int getSumB(){
        return this.sumB;
    }
    public void setSumB(int sumB){ //멤버변수와 다른 지역변수임.(다름)
        this.sumB = sumB; //setter 메소드
    }

    public int sum(int a,int b){

        return a+b;
    }

}
