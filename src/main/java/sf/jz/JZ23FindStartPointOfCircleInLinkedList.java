package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ23FindStartPointOfCircleInLinkedList {

    public static void main(String[] args) {
        LinkedListNode head = null;
        LinkedListNode tail = null;
        LinkedListNode tmp = new LinkedListNode();
        LinkedListNode circleStart = null;
        tmp.setValue(Integer.valueOf(1));
        head = tmp;
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(2));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(3));
        circleStart = tmp;
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
        tmp.setNext(circleStart);
        tail.setNext(tmp);
        tail = tmp;
        LinkedListNode meetPoint = getFirstMeetPoint(head);
        int circlePointNum = getCirclePointNum(meetPoint);
        log.info("meetPoint:{},circlePointNum:{},circleStartPoint:{}", meetPoint, getCirclePointNum(meetPoint),
            findCircleStartPoint(head, circlePointNum));
    }

    /**
     * 非环长度m；环长度n；第一次相遇点z；2m+2z=m+nk+z=>m+z=nk=>m=nk-z 另外一个慢指针再从头开始走，慢指针在相遇点继续走，两个慢指针相遇的地方就是入口点
     */
    private static LinkedListNode getFirstMeetPoint(LinkedListNode head) {
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        //第一次相遇
        while (Objects.nonNull(fast)) {
            slow = slow.getNext();
            fast = fast.getNext();
            if (Objects.nonNull(fast)) {
                fast = fast.getNext();
            } else {
                //到头了，说明没有环
                return null;
            }
            if (slow == fast) {
                break;
            }
        }
        //到头了，没有环
        if (Objects.isNull(fast)) {
            return null;
        }

        return fast;
    }

    /**
     * 求环的长度
     */
    private static int getCirclePointNum(LinkedListNode meetPoint) {
        if (Objects.isNull(meetPoint)) {
            return 0;
        }
        LinkedListNode point = meetPoint;
        int num = 0;
        while (Objects.nonNull(point)) {
            point = point.getNext();
            num++;
            if (point == meetPoint) {
                break;
            }
        }
        return num;
    }

    /**
     * 查找入口点，找到长度后，问题等价于题目22求倒数第n个节点
     */
    private static LinkedListNode findCircleStartPoint(LinkedListNode head, int count) {
        if (count == 0) {
            return null;
        }

        LinkedListNode p1 = head;
        LinkedListNode p2 = head;
        //两个慢指针继续走，再次相遇就是入口节点
        while (Objects.nonNull(p1)) {
            p1 = p1.getNext();
            if (count <= 0) {
                p2 = p2.getNext();
            } else {
                count--;
            }
            if (p1 == p2) {
                return p1;
            }
        }
        return null;
    }
}
