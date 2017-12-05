package com.chengsy.code.algorithm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode {

    public int[] twoSum(int[] nums, int target) {
        int sum = 0;
        int[] result = new int[2];
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                count ++;
                sum = nums[i] + nums[j];
                if (target == sum) {
                    result[0] = i;
                    result[1] = j;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        System.out.println(count);
        return result;
    }

    public List<List<Integer>> threeSum(int[] nums) {
        for (Integer i: nums) {
        }
    }
}
