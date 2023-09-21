package com.test.exam02;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Practice {
    public static void main(String[] args) {
        int[] nums = {1,7,2,4,3,5,0,1,9,6};
        System.out.println(Arrays.toString(nums));

        for(int i=nums.length-1; i>0; i--){
            for(int j=0; j<i; j++){
                if(nums[j]>nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
    }
}



