package sf.jz;

import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JZ22FindMiddleInLinkedList {
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
        log.info("{}", find(head));
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(2));
        tail.setNext(tmp);
        tail = tmp;
        log.info("{}", find(head));
    }

    private static LinkedListNode find(LinkedListNode head) {
        if (Objects.isNull(head)) {
            return null;
        }
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        while (true) {
            if (Objects.nonNull(fast)) {
                fast = fast.getNext();
            } else {
                return slow;
            }

            if (Objects.nonNull(fast)) {
                fast = fast.getNext();
                //fast走完两步后，slow再走一步，不能放在上一个if判断里面
                slow = slow.getNext();
            } else {
                return slow;
            }
        }
    }
}
