package com.test.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberInfoView {
    public static void main(String[] args) {
        List<Member> list = new ArrayList<>(); //동적배열을 지원하는 Collector Framework
        list.add(new Member("김철수","남성",23));
        list.add(new Member("이미래","여성",30));
        list.add(new Member("송윤주","여성",29));
        list.add(new Member("김수지","여성",28));
        list.add(new Member("채종현","남성",27));

        for(Member member:list){ //배열전용 for문
            System.out.println(member.getName()+"\t"+member.getGender()+"\t"+member.getAge());  
        }
        for(int i=0; i< list.size(); i++){
            System.out.println(list.get(i).getName()
            +"\t"+list.get(i).getGender()+"\t"
            +list.get(i).getAge());     
        }

        List<Map<Integer,Integer>> listFruits = new ArrayList<>();
        // List<Map<Integer,Integer>> listFruits = new ArrayList<Map<Integer,Integer>>(); >생략가능
        
        int aa = 0;
        for(int i=0; i<3; i++){
            Map<Integer, Integer> map = new HashMap<>();

            map.put(i, aa*10);
            aa ++;
            listFruits.add(map);
             System.out.println(listFruits.get(i).toString());
        }
    }
}
