package sf.jz;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JZ18DeleteNodeInLinkedList {
    public static void main(String[] args) {
        LinkedListNode head = null;
        LinkedListNode tail = null;
        LinkedListNode deleted = null;
        LinkedListNode tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(3));
        head = tmp;
        tail = tmp;
        tmp = new LinkedListNode();
        tmp.setValue(Integer.valueOf(1));
        tail.setNext(tmp);
        tail = tmp;
        tmp = new LinkedListNode();
        deleted = tmp;
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
        //删除中间节点
        head = deleteNode(head, deleted);
        log.info("{}", head);
        //删除尾节点
        head = deleteNode(head, tail);
        log.info("{}", head);
        //删除头节点
        head = deleteNode(head, head);
        log.info("{}", head);
    }

    /**
     * 通过拷贝下一个节点的值实现删除；
     * 特殊情况是头和尾节点的删除；
     *
     * @param head
     * @param node
     */
    private static LinkedListNode deleteNode(LinkedListNode head, LinkedListNode node) {
        if (Objects.isNull(node)) {
            return head;
        }
        //头节点不是特殊情况
        if (head == node) {
            // 错误的写法，java是传值，调用处的头指针并不会变化
            head = node.getNext();
            // 需要返回新的头节点
            return head;
        } else {
            //要删除的不是尾节点
            if (Objects.nonNull(node.getNext())) {
                node.setValue(node.getNext().getValue());
                node.setNext(node.getNext().getNext());
            } else {
                //删除尾节点，且不是头节点
                LinkedListNode cur = head;
                while (Objects.nonNull(cur)) {
                    //下一个节点是要删除的
                    if (cur.getNext() == node) {
                        cur.setNext(node.getNext());
                    }
                    cur = cur.getNext();
                }
            }
        }
        return head;
    }
}
