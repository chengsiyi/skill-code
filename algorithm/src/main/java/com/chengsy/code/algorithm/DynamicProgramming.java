package com.chengsy.code.algorithm;

import javafx.util.Pair;

/**
 * 动态规划
 * @author chengsiyi
 * @date 2020/4/7 20:28
 */
public class DynamicProgramming {
    private static final int[][] array = {
            {1, 3, 1},
            {1, 5, 1},
            {4, 2, 4},
            {2, 5, 2}
    };

    public static Pair<Integer, Integer> min(int firstIndex, int secondIndex) {

        if (firstIndex < 0 || secondIndex < 0) {
            return new Pair<>(0, 0);
        }
        if (firstIndex == 0 && secondIndex > 0) {
            return new Pair<>(firstIndex, secondIndex - 1);
        } else if (secondIndex == 0 && firstIndex > 0) {
            return new Pair<>(firstIndex - 1, secondIndex);

        } else if (firstIndex == 0 && secondIndex == 0) {
            return new Pair<>(firstIndex, secondIndex);
        }

        int right = array[firstIndex][secondIndex - 1];
        int up = array[firstIndex - 1][secondIndex];

        if (right > up) {
            return new Pair<>(firstIndex - 1, secondIndex);
        } else {
            return new Pair<>(firstIndex, secondIndex - 1);
        }
    }

    public static void main(String[] args) {
        int i = array.length - 1;
        int j = array[0].length - 1;
        Integer sum = array[i][j];
        Pair<Integer, Integer> result = min(i, j);
        i = result.getKey();
        j = result.getValue();
        while (i != 0 || j != 0) {
            sum += array[i][j];
            result = min(i, j);
            i = result.getKey();
            j = result.getValue();
        }
        sum += array[0][0];

        System.out.println(sum);
    }
}
