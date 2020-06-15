package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ25MergeList {

    public static void main(String[] args) {
        LinkedListNode headA = null;
        LinkedListNode headB = null;
        LinkedListNode tail = null;
        LinkedListNode tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(1));
        headA = tmp;
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(2));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(3));
        tail.setNext(tmp);
        tail = tmp;

        tmp = new LinkedListNode();
        headB = tmp;
        tail = tmp;
        tmp.setValue(Integer.valueOf(4));
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(5));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(6));
        tail.setNext(tmp);
        tail = tmp;
        LinkedListNode.travelPrint(merge(headA, headB));
        LinkedListNode.travelPrint(merge(null, headB));
        LinkedListNode.travelPrint(merge(null, null));
    }

    /**
     * 合并有序链表
     */
    private static LinkedListNode merge(LinkedListNode a, LinkedListNode b) {
        if (Objects.isNull(a)) {
            return b;
        }

        if (Objects.isNull(b)) {
            return a;
        }

        LinkedListNode head = null;
        LinkedListNode cur = null;
        LinkedListNode pre = null;
        while (Objects.nonNull(a) && Objects.nonNull(b)) {
            if (a.getValue() <= b.getValue()) {
                cur = a;
                a = a.getNext();
            } else {
                cur = b;
                b = b.getNext();
            }

            if (Objects.isNull(head)) {
                head = cur;
            } else {
                pre.setNext(cur);
            }

            pre = cur;
        }

        if (Objects.nonNull(a)) {
            pre.setNext(a);
        }

        if (Objects.nonNull(b)) {
            pre.setNext(b);
        }

        return head;
    }
}
