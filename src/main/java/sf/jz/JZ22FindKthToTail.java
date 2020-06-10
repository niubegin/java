package sf.jz;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JZ22FindKthToTail {
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
        log.info("{}", find(head, 3));
    }

    /**
     * 快慢两个指针，快指针先走k-1步，然后快慢指针一起走，快指针走到终点时，慢指针即为结果
     *
     * @param head
     * @param k
     * @return
     */
    private static LinkedListNode find(LinkedListNode head, int k) {
        if (Objects.isNull(head) || k < 0) {
            return null;
        }
        LinkedListNode fast = head;
        for (int i = 0; i < k; i++) {
            if (Objects.isNull(fast)) {
                return null;
            }
            fast = fast.getNext();
        }
        LinkedListNode slow = head;
        while (Objects.nonNull(fast)) {
            fast = fast.getNext();
            slow = slow.getNext();
        }
        return slow;
    }
}
