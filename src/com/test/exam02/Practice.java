package com.test.exam02;


public class Practice {
    public static void main(String[] args) {
        GStack<String> stringGStack= new GStack<String>();
        stringGStack.push("aa");

        for(int i=0; i<3; i++){
            System.out.println();
        }
    }

}
class GStack<T>{
    int tos;
    Object[] stck;

    public GStack(){//생성자
        tos = 0;
        stck = new Object[10];
    }
    
    public void push(T item){
        if(tos ==10) return;
        stck[tos] = item;
        tos++;
    }
    public T pop(){
        if(tos ==0) return null;
        tos --;
        return (T)stck[tos];
    }
}



