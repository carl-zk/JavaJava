package utils.sort;

import chapter02.MergeSort;

/**
 * Created by hero on 17-3-24.
 */
public interface Sort {
    void sort(int[] data);

    public static void main(String[] args) {
        int[] data = {3, 4, 2, 6, 1, 5, 11, 7, 4};
        Sort s = new TwoWayMergeSort();
        s.sort(data);
        for (int d : data) {
            System.out.println(d);
        }
    }
}
