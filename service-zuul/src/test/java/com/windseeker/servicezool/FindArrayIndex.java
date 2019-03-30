package com.windseeker.servicezool;

import java.util.Arrays;
import java.util.Random;

public class FindArrayIndex {

    private static int times = 0;

    public static void main(String[] args) {
        int a = 0;
        int row = 1000;
        int size = 1000;
        int[][] array = new int[row][size];
        for (int i = 0; i < row; i++) {
            int[] item = new int[size];
            array[i] = item;
            for (int j = 0; j < size; j++) {
                item[j] = ++a;
            }
            System.out.println(Arrays.toString(item));
        }
        int r = new Random().nextInt(row * size);
        r = 38468295;
        int[] pos = findIndex(array, r, 0, row * size);
        if (pos == null) {
            System.out.printf("随机值: %d，共查询%d次", r, times);
        } else {
            System.out.printf("随机值: %d，所在位置：%d,%d 共查询%d次", r, pos[0], pos[1], times);
        }
    }

    public static int[] findIndex(int[][] array, int random, int start, int end) {
        System.out.println(++times);


        int middle = (start + end) / 2;

        int[] result = locate(array, middle);
        int value = result[2];
        System.out.println("中间值：" + value);
        if (value == random) {
            return new int[]{result[0], result[1]};
        }

        if (middle == start) {
            return null;
        }

        if (value < random) {
            return findIndex(array, random, middle, end);
        } else {
            return findIndex(array, random, start, middle);
        }
    }

    public static int[] locate(int[][] array, int position) {
        int pos = position % array[0].length;
        int row = position / array[0].length;
        return new int[]{row, pos, array[row][pos]};
    }


}
