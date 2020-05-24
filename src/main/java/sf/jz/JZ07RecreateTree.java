package sf.jz;

import lombok.Data;

@Data
public class JZ07RecreateTree {
    public static void main(String[] args) {
        int[] first = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] middle = {4, 7, 2, 1, 5, 3, 8, 6};
        JZ07RecreateTree jz07RecreateTree = new JZ07RecreateTree();
        Node head = jz07RecreateTree.recreateTree(first, middle, 0, first.length - 1, 0, middle.length - 1);
    }

    @Data
    private class Node {
        private int value;
        private Node left;
        private Node right;
    }

    /**
     * 根据前序遍历【1，2，4，7，3，5，6，8】，中序遍历【4，7，2，1，5，3，8，6】重建二叉树
     */
    private Node recreateTree(int[] first, int[] middle, int firstStart, int firstEnd, int middleStart, int middleEnd) {
        Node head = new Node();
        head.value = first[firstStart];
        //找到头节点在中序遍历中的位置
        int findMiddle = middleStart;
        for (int i = middleStart; i <= middleEnd; i++) {
            if (middle[i] == head.value) {
                findMiddle = i;
                break;
            }
        }
        //没有左子树
        if (findMiddle == middleStart) {
            head.left = null;
        } else {
            head.left = recreateTree(first, middle, firstStart + 1, firstStart + findMiddle - middleStart, middleStart, findMiddle - 1);
        }
        //没有右子树
        if (findMiddle == middleEnd) {
            head.right = null;
        } else {
            head.right = recreateTree(first, middle, firstStart + findMiddle - middleStart + 1, firstEnd, findMiddle + 1, middleEnd);
        }
        return head;
    }
}
