package utils.sort;

/**
 * Created by hero on 17-3-24.
 * 冒泡排序
 * 算法缺点
 * O(n^2)
 */
public class BubbleSort implements Sort {

    public void sort(int[] data) {
        for (int i = 0, j; i < data.length; i++) {
            for (j = i + 1; j < data.length; j++) {
                if (data[i] > data[j]) {
                    swap(data, i, j);
                }
            }
        }
    }

    private void swap(int[] data, int a, int b) {
        int v = data[a];
        data[a] = data[b];
        data[b] = v;
    }
}
