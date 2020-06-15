package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ24ReverseList {

    public static void main(String[] args) {
        LinkedListNode head = null;
        LinkedListNode tail = null;
        LinkedListNode tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(1));
        head = tmp;
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
        tmp.setValue(Integer.valueOf(4));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(5));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(6));
        tail.setNext(tmp);
        tail = tmp;
        LinkedListNode.travelPrint(reverse(head));
    }

    /**
     * 反转链表
     */
    private static LinkedListNode reverse(LinkedListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }

        LinkedListNode pre = null;
        LinkedListNode cur = head;
        LinkedListNode next = null;
        while (Objects.nonNull(cur)) {
            next = cur.getNext();
            cur.setNext(pre);
            pre = cur;
            cur = next;
        }

        return pre;
    }
}
