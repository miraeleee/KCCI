package com.test.exam01;

import java.util.Arrays;

public class ArrayExam {
    public static void main(String[] args) {
        int[] a = new int[]{1,2,3,4,5,6};
        //for문을 이용하여 배열의 요소값을 추출 #1
        for (int i = 0; i < a.length; i++) System.out.print(a[i]+" ");//전통 방식
        
        System.out.println("\n");

        //for문을 이용하여 배열의 요소값을 추출 #2
        for(int i:a) System.out.print(i+" "); //배열 전용 for문, a는 배열명
       
        System.out.println("\n");

        //for문을 이용하여 배열의 요소값을 추출 #3
        //arrays라는 클래스 import하고, tostring 이라는 매소드사용함
        System.out.println(Arrays.toString(a)); //문자열로 리턴
        
        //2차원배열을 게임이나, 영상쪽에서 많이 활용함
        int[][] b = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("b[0][2]="+b[0][2]);
        System.out.println("b[1][2]="+b[1][2]);
        System.out.println("b[2][0]="+b[2][0]);

        for (int j = 0; j < b.length; j++) {
            System.out.println(Arrays.toString(b[j]));
        }

        // 2차원 동적배열 예제
        for (int i = 0; i < b.length; i++) 
            System.out.println(Arrays.toString(b[i]));

            int intArray[][] = new int[4][]; //실행할때 메모리크키 조절가능
            intArray[0] = new int[3]; 
            intArray[1] = new int[2];
            intArray[2] = new int[3];
            intArray[3] = new int[2];
    
            for (int i = 0; i < intArray.length; i++) 
                for (int j = 0; j < intArray[i].length; j++) 
                    intArray[i][j] = (i+1)*10+j;
                
            for (int i = 0; i < intArray.length; i++) {
                for (int j = 0; j < intArray[i].length; j++) 
                System.out.print(intArray[i][j]+" "); 
                System.out.println();   
            }
            
        

    }//Main함수 끝....
}
