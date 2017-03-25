package chapter02;

import utils.sort.Sort;

/**
 * Created by hero on 17-3-24.
 * 归并排序（递归）
 * 算法缺点
 * merge时需要额外的数组，空间占用大
 */
public class MergeSort implements Sort {

    public void sort(int[] data) {
        divided(data, 0, data.length - 1);
    }

    private void divided(int[] data, int head, int tail) {
        if (head < tail) {
            int mid = (head + tail) >> 1;
            divided(data, head, mid);
            divided(data, mid + 1, tail);
            merge(data, head, mid, tail);
        }
    }

    private void merge(int[] data, int head, int mid, int tail) {
        int i = head, j = mid + 1, k = 0;
        int[] temp = new int[tail - head + 1];
        while (i <= mid && j <= tail) {
            temp[k++] = data[i] > data[j] ? data[j++] : data[i++];
        }
        if (i <= mid) {
            System.arraycopy(data, i, temp, k, mid - i + 1);
        } else {
            System.arraycopy(data, j, temp, k, tail - j + 1);
        }
        System.arraycopy(temp, 0, data, head, tail - head + 1);
    }
}
