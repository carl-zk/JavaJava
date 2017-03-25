package utils.sort;

import utils.common.Queue;

/**
 * Created by hero on 17-3-25.
 * 2路归并排序（非递归）
 * 第一次步长为1，相当于每一路只有一个元素，然后两两合并
 * 第2次两两合并，每一路有2个元素
 * 第3次两两合并，每一路有4个元素
 * ......
 * 最终步长会超过数组总长度，分路合并结束
 *
 * 分步长是logn，合并是n，总时间复杂度是O(nlogn)
 * 算法缺点
 * 需要一个等长的数组，并且来回的复制，数组操作太多
 */
public class TwoWayMergeSort implements Sort {

    public void sort(int[] data) {
        int[] copy = new int[data.length];   //需要一个等长的数组
        for (int step = 1, i, j, alen, blen, pa, pb; step < data.length; step <<= 1) {    //各路的长度：1,2,4,8...
            for (i = 0, j = 0; i < data.length; ) {    //分路，a路加到copy[0,alen]，b路加到copy[data.length-blen, data.length]
                if (i + step < data.length) {
                    System.arraycopy(data, i, copy, 0, step);
                    alen = step;
                    i += step;
                } else {
                    System.arraycopy(data, i, copy, 0, data.length - i);
                    alen = data.length - i;
                    i = data.length;
                }
                if (i + step - 1 < data.length) {
                    System.arraycopy(data, i, copy, data.length - step, step);
                    blen = step;
                    i += step;
                } else if (i < data.length) {
                    System.arraycopy(data, i, copy, i, data.length - i);
                    blen = data.length - i;
                    i = data.length;
                } else
                    blen = 0;

                for (pa = 0, pb = data.length - blen; pa < alen && pb < data.length; ) {    //合路
                    data[j++] = copy[pa] > copy[pb] ? copy[pb++] : copy[pa++];
                }
                if (pa == alen) {
                    System.arraycopy(copy, pb, data, j, data.length - pb);
                    j += (data.length - pb);
                } else {
                    System.arraycopy(copy, pa, data, j, alen - pa);
                    j += (alen - pa);
                }
            }
        }
    }
}
