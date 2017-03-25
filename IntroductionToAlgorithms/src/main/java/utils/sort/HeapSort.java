package utils.sort;

/**
 * Created by hero on 17-3-25.
 * 堆排序（非递归）
 * 算法关键
 * 1.首先要构造一个最大堆
 * 2.在1基础上交换根节点和尾节点，然后维护最大堆
 *
 * 因为非递归，所以在处理大量数据时，可以使用堆排序
 */
public class HeapSort implements Sort {

    public void sort(int[] data) {
        /** 构造最大堆 */
        for (int i = (data.length - 1) / 2; i >= 0; i--)
            sift(data, i, data.length - 1);

        for (int i = data.length - 1, v; i > 0; i--) {
            v = data[0];
            data[0] = data[i];
            data[i] = v;
            sift(data, 0, i - 1);
        }
    }

    /**
     * 从i向其左右孩子节点更新维护最大堆
     * 因为本身就是最大堆，所以只需维护改变的分支
     */
    private void sift(int[] data, int i, int m) {
        int v = data[i];    // v为“根”元素，将作为某分支的父节点
        for (int j = 2 * i + 1; j <= m; j = 2 * j + 1) {    // 从根元素向子元素更新
            if (j + 1 <= m && data[j + 1] > data[j])        //  若有右孩子并且右孩子大于左孩子
                j++;
            if (data[j] > v) {    // 子节点大于父节点
                data[i] = data[j];
                i = j;    // i为等待填值的父节点
            } else
                break;
        }
        data[i] = v;
    }
}
