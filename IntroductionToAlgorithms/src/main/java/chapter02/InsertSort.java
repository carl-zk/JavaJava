package chapter02;

import utils.sort.Sort;

/**
 * Created by hero on 17-3-24.
 * 插入排序（非递归）
 * 算法缺点
 * 有可能每次都要移动数组，效率很低
 * O(n^2)
 */
public class InsertSort implements Sort {

    public void sort(int[] data) {
        for (int i, j = 1, v; j < data.length; j++) {
            v = data[j];
            for (i = j; i - 1 >= 0 && data[i - 1] > v; i--) {
                data[i] = data[i - 1];
            }
            data[i] = v;
        }
    }
}
