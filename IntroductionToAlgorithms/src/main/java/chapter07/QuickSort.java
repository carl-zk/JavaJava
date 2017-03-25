package chapter07;

import utils.sort.Sort;

/**
 * 快速排序算法（递归）
 * 每次取第一个值作为比较值key，用一个临时变量key可以避免作交换
 * 1.从最后向前找比key小的值，赋给data[i]，这里就不是交换
 * 2.从前面向后找比key大的值，赋给data[j]，相当于数组中每次都空一个等待赋值的位置
 * 3.递归结束
 * <p>
 * 算法缺点
 * 每次取第一个作为比较值，情况不够随机，很容易遇到最坏情况
 */
public class QuickSort implements Sort {

    public void sort(int[] data) {
        sort(data, 0, data.length - 1);
    }

    private void sort(int[] data, int first, int last) {
        if (first >= last) return;
        int key = data[first];
        int i = first, j = last;
        while (i < j) {
            while (i < j && data[j] >= key) j--;
            data[i] = data[j];
            while (i < j && data[i] <= key) i++;
            data[j] = data[i];
        }
        data[i] = key;
        sort(data, first, i - 1);
        sort(data, i + 1, last);
    }
}
