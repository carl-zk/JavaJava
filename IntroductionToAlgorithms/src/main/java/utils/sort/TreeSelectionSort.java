package utils.sort;

import utils.common.Queue;

/**
 * Created by hero on 17-3-25.
 * 树形选择排序
 * 算法描述
 * data[n]共有n个数，两两比较，小者成为父节点的值
 * 依次结合，直到根节点，此时，根节点就是n个数的最小值
 * 然后去掉最小值所在的节点，更新此节点的父节点最小值，直到根节点
 * n次更新之后，排序完成
 * （类似比赛中的对决，先两两对决，胜者再两两对决，直到选出冠军）
 * 树形选择排序是对简单选择排序的优化，堆排序是对树形选择排序的优化
 * O(nlogn)，时间复杂度很稳定，但是需要额外的2n-1个临时节点
 */
public class TreeSelectionSort implements Sort {
    public void sort(int[] data) {
        Queue<Node> queue = new Queue<>();
        for (int d : data) {
            queue.addLast(new Node(d));
        }

        /** 构建二叉树 */
        while (queue.length > 1) {
            Node lch = queue.removeFirst();
            Node rch = queue.removeFirst();
            Node parent = new Node(lch, rch);
            queue.addLast(parent);
        }

        Node root = queue.removeFirst();

        /**
         * 第i次循环，可以确定第i个最小值
         * 然后将该值对应的叶节点去掉，更新整个二叉树
         */
        for (int i = 0; i < data.length; i++) {
            data[i] = root.min.value;
            Node parentOfMin = root.min.parent;
            if (parentOfMin == null)
                return;
            if (parentOfMin.lch == root.min)
                parentOfMin.lch = null;
            else
                parentOfMin.rch = null;
            update(parentOfMin);
        }
    }

    private void update(Node node) {
        while (node != null) {
            if (node.lch == null && node.rch == null) {
                Node parent = node.parent;
                if (parent == null)
                    return;
                if (parent.lch == node)
                    parent.lch = null;
                else
                    parent.rch = null;
            } else
                node.selectMin(node.lch, node.rch);
            node = node.parent;
        }
    }

    private class Node {
        private int value;
        private Node min;    //指向value对应的叶节点
        private Node lch, rch, parent;

        //叶节点初始化
        Node(int value) {
            this.value = value;
            this.min = this;
            this.lch = this.rch = this.parent = null;
        }

        //非叶节点初始化
        Node(Node lch, Node rch) {
            this.lch = lch;
            this.rch = rch;
            this.parent = null;
            selectMin(lch, rch);
            lch.parent = this;
            rch.parent = this;
        }

        private void selectMin(Node lch, Node rch) {
            if (lch == null && rch == null)
                throw new IllegalArgumentException();
            if (lch == null && rch != null)
                min = rch.min;
            else if (lch != null && rch == null)
                min = lch.min;
            else if (lch.min.value > rch.min.value)
                min = rch.min;
            else
                min = lch.min;
        }
    }
}
