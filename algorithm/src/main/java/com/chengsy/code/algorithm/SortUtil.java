package com.chengsy.code.algorithm;

import java.util.Arrays;

public class SortUtil {
    /**
     * 冒泡排序
     * <p>
     * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
     * <p>
     * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
     * <p>
     * 3.针对所有的元素重复以上的步骤，除了最后一个。
     * <p>
     * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较
     *
     * @param nums
     * @return
     */
    public static int[] bubbleSort(int[] nums) {
        int count = 0;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 1; j < nums.length; j++) {
                count++;
                if (nums[j] < nums[j - 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j - 1];
                    nums[j - 1] = temp;
                    flag = true;
                }
            }
        }
        System.out.println(String.format("排序循环次数:%d", count));
        return nums;
    }

    /**
     * 快速排序
     *
     * @param nums 原始数组
     * @param low  最低位索引
     * @param higt 最高位索引
     * @return 排序好的数组
     */
    public static int[] quickSort(int[] nums, int low, int higt) {
        if (higt >= low) {
            int mid = partition(nums, low, higt);
            quickSort(nums, low, mid - 1);
            quickSort(nums, mid + 1, higt);
        }
        return nums;
    }

    /**
     * 1.从数列中挑出一个元素，称为 “基准”
     * 2.重新排序数列，所有元素比基准值小的摆放在基准前面，
     * 所有元素比基准值大的摆在基准的后面（相同的数可以到任一边）。
     * 在这个分区退出之后，该基准就处于数列的中间位置。
     * 这个称为分区（partition）操作
     * 3.递归地（recursive）把小于基准值元素的子数列和大于基准值元素的子数列排序
     * @param nums
     * @param low
     * @param higt
     * @return
     */
    private static int partition(int[] nums, int low, int higt) {
        int pivot = nums[higt];
        int i = low - 1;
        int temp;
        for (int j = low; j < higt; j++) {
            if (nums[j] < pivot) {
                i++;
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        // 将基准值放到中间
        temp = nums[i + 1];
        nums[i + 1] = pivot;
        nums[higt] = temp;
        System.out.println(Arrays.toString(nums));
        return i + 1;
    }
}
