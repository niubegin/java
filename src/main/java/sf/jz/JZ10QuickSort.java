package sf.jz;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

        LinkedListNode head = null;
        LinkedListNode tail = null;
        LinkedListNode tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(3));
        head = tmp;
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(1));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(4));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(5));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(2));
        tail.setNext(tmp);
        tail = tmp;
        quickSort(head, tail);
        log.info("{}", head);
    }

    /**
     * 顺序遍历法 https://segmentfault.com/a/1190000004410119、 用两个指针，small始终指向小于pivot的最后一个节点，初始时指向pivot节点；
     * 另外一个指针依次遍历数组（同样可以遍历链表）； 如果当前节点小于pivot，则将small指针+1，然后交互当前节点和small指向的节点（肯定是大于pivot的节点）；
     */
    private static void quickSort(int[] arr, int low, int high) {
        //结束递归的条件
        if (low >= high) {
            return;
        }

        int small = partition(arr, low, high);
        //递归排序pivot前节点
        quickSort(arr, low, small - 1);
        //递归排序pivot后节点
        quickSort(arr, small + 1, high);
    }

    /**
     * 交互并拆分：还可以用于实现第k大的数
     * @return pivot的最新位置
     */
    private static int partition(int[] arr, int low, int high) {
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
        swap(arr, low, small);
        return small;
    }

    /**
     * 交换
     */
    private static void swap(int[] arr, int a, int b) {
        if (a == b) {
            return;
        }

        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    private static void quickSort(LinkedListNode low, LinkedListNode high) {
        //只有一个节点结束递归
        if (low == high || Objects.isNull(low)) {
            return;
        }

        //小于pivot的指针
        LinkedListNode small = low;
        //当前指针，指向pivot的下一个
        LinkedListNode cur = low.getNext();
        while (Objects.nonNull(cur)) {
            //如果当前节点小于pivot，则small下移（指向的肯定大于pivot），并交互cur和small的元素
            if (cur.getValue().compareTo(low.getValue()) < 0) {
                //small指向下一个节点（该节点大于pivot）
                small = small.getNext();
                //交换small和cur
                swap(small, cur);
            }
            //cur指向下一个节点
            cur = cur.getNext();
        }
        //交互pivot和small
        swap(low, small);
        //递归调用小于pivot部分，small是pivot的位置，多递归了一个位置
        quickSort(low, small);
        //递归调用大于pivot部分
        quickSort(small.getNext(), high);
    }

    /**
     * 交互链表元素
     */
    private static void swap(LinkedListNode a, LinkedListNode b) {
        if (a == b) {
            return;
        }
        Integer tmp = a.getValue();
        a.setValue(b.getValue());
        b.setValue(tmp);
    }
}
