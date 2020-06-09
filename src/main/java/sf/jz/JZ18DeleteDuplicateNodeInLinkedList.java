package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ18DeleteDuplicateNodeInLinkedList {

    public static void main(String[] args) {
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
        tmp.setValue(Integer.valueOf(3));
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
        tmp.setValue(Integer.valueOf(4));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(2));
        tail.setNext(tmp);
        tail = tmp;
        log.info("{}", head);
        deleteDup(head, tail);
        log.info("{}", head);
    }

    /**
     * 删除重复的元素 1.快速排序 2.遍历删除重复
     */
    private static void deleteDup(LinkedListNode head, LinkedListNode tail) {
        if (Objects.isNull(head) || Objects.isNull(tail)) {
            return;
        }
        quickSort(head, tail);
        LinkedListNode cur = head;
        //遍历
        while (Objects.nonNull(cur)) {
            //判重
            if (Objects.nonNull(cur.getNext()) && Objects.equals(cur.getValue(), cur.getNext().getValue())) {
                //删除下一个节点
                cur.setNext(cur.getNext().getNext());
            }
            cur = cur.getNext();
        }
    }

    /**
     * 快速排序
     */
    private static void quickSort(LinkedListNode head, LinkedListNode tail) {
        if (head == tail) {
            return;
        }
        //pivot和small指向头节点；cur指向头节点的下一个
        LinkedListNode pivot = head;
        LinkedListNode small = head;
        LinkedListNode cur = head.getNext();
        int tmp = 0;
        //遍历
        while (Objects.nonNull(cur)) {
            //如果小于pivot，和small的下一个元素交换
            if (cur.getValue().intValue() < pivot.getValue().intValue()) {
                small = small.getNext();
                tmp = cur.getValue();
                cur.setValue(small.getValue());
                small.setValue(tmp);
            }
            cur = cur.getNext();
        }
        //交换pivot和small
        tmp = small.getValue();
        small.setValue(pivot.getValue());
        pivot.setValue(tmp);
        //排序前半部分，多排了一个pivot节点
        quickSort(head, small);
        //排序后半部分
        quickSort(small.getNext(), tail);
    }
}
