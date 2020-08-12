package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ52FindFirstCommonNode {

    public static void main(String[] args) {
        LinkedListNode tmp3 = new LinkedListNode();
        tmp3.setValue(3);
        tmp3.setNext(null);
        LinkedListNode tmp2 = new LinkedListNode();
        tmp2.setValue(2);
        tmp2.setNext(tmp3);
        LinkedListNode head2 = tmp2;
        LinkedListNode tmp1 = new LinkedListNode();
        tmp1.setValue(1);
        tmp1.setNext(tmp2);
        LinkedListNode tmp0 = new LinkedListNode();
        tmp0.setValue(0);
        tmp0.setNext(null);
        LinkedListNode head1 = tmp1;
        log.info("{}", find(head1, head2));
        log.info("{}", find(head1, head1));
        log.info("{}", find(head1, tmp0));
    }

    /**
     * 查找单向链表的第一个交点
     */
    private static LinkedListNode find(LinkedListNode head1, LinkedListNode head2) {
        int length1 = getLength(head1);
        int length2 = getLength(head2);
        LinkedListNode cur1 = length1 > length2 ? head1 : head2;
        LinkedListNode cur2 = length1 > length2 ? head2 : head1;
        int minus = Math.abs(length1 - length2);
        while (minus > 0) {
            minus--;
            cur1 = cur1.getNext();
        }

        while (Objects.nonNull(cur1)) {
            if (cur1.getValue().equals(cur2.getValue())) {
                return cur1;
            }

            cur1 = cur1.getNext();
            cur2 = cur2.getNext();
        }

        return null;
    }

    private static int getLength(LinkedListNode head1) {
        LinkedListNode cur = head1;
        int length1 = 0;
        while (Objects.nonNull(cur)) {
            length1++;
            cur = cur.getNext();
        }
        return length1;
    }
}
