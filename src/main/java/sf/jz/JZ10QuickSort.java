package sf.jz;

import java.util.Random;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ10QuickSort {

    public static void main(String[] args) {
        int max = 10000;
        int[] arr = new int[max];
        Random random = new Random();
        for (int index = 0; index < max; index++) {
            arr[index] = random.nextInt();
        }

        int[] arr2 = {3, 1, 4, 5, 2};
        quickSort(arr2, 0, arr2.length - 1);
        log.info("{}", arr2);
        log.info("{}", arr);
    }

    /**
     * 顺序遍历法 https://segmentfault.com/a/1190000004410119、
     * 用两个指针，small始终指向小于pivot的最后一个节点，初始时指向pivot节点；
     * 另外一个指针依次遍历数组（同样可以遍历链表）；
     * 如果当前节点小于pivot，则将small指针+1，然后交互当前节点和small指向的节点（肯定是大于pivot的节点）；
     */
    private static void quickSort(int[] arr, int low, int high) {
        //结束递归的条件
        if (low >= high) {
            return;
        }

        //small指向比pivot节点小的最后一个节点，最开始比pivot小的节点是0个
        int small = low;
        //从第二个遍历到最后一个元素
        for (int index = low + 1; index <= high; index++) {
            //如果当前节点小于pivot，则交互small和当前节点
            if (arr[index] < arr[low]) {
                small++;
                swap(arr, small, index);
            }
        }

        //把pivot换到合适的位置
        swap(arr, small, low);
        //递归排序pivot前节点
        quickSort(arr, low, small - 1);
        //递归排序pivot后节点
        quickSort(arr, small + 1, high);
    }

    /**
     * 交换
     * @param arr
     * @param a
     * @param b
     */
    private static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }

        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }
}
