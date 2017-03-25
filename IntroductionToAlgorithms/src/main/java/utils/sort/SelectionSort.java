package utils.sort;

/**
 * Created by hero on 17-3-25.
 * 简单选择排序
 */
public class SelectionSort implements Sort {
    /**
     * 每次都选择一个最小的值，排到前面
     * k是最小值的下标
     */
    public void sort(int[] data) {
        for (int i = 0, j, k; i < data.length; i++) {
            for (k = i, j = i + 1; j < data.length; j++)
                if (data[k] > data[j])
                    k = j;
            j = data[i];    //为了节省空间，可读性差了，工作中不推荐这么利用临时变量
            data[i] = data[k];
            data[k] = j;
        }
    }
}
