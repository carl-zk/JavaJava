package utils.sort;

/**
 * Created by hero on 17-3-24.
 * 希尔排序
 * 算是变步长的插入排序
 * 希尔排序和折半插入排序都是对直接插入排序的优化
 */
public class ShellSort implements Sort {

    public void sort(int[] data) {
        for (int step = data.length >> 1, i, j, v; step > 0; step >>= 1) {
            for (i = step; i < data.length; i++) {
                v = data[i];
                for (j = i - step; j >= 0 && data[j] > v; j -= step) {
                    data[j + step] = data[j];
                }
                data[j + step] = v;
            }
        }
    }
}
