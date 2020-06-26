package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ35CloneComplexList {

    public static void main(String[] args) {
        ComplexListNode complexListNode1 = new ComplexListNode();
        complexListNode1.setValue(1);
        ComplexListNode complexListNode2 = new ComplexListNode();
        complexListNode2.setValue(2);
        ComplexListNode complexListNode3 = new ComplexListNode();
        complexListNode3.setValue(3);
        complexListNode1.setNext(complexListNode2);
        complexListNode1.setSibling(complexListNode3);
        complexListNode2.setNext(complexListNode3);
        complexListNode2.setSibling(complexListNode1);
        complexListNode3.setNext(null);
        complexListNode3.setSibling(complexListNode2);
        log.info("{}", clone(complexListNode1));
    }

    /**
     * 克隆复杂链表
     */
    private static ComplexListNode clone(ComplexListNode node) {
        if (Objects.isNull(node)) {
            return null;
        }
        ComplexListNode head = node;
        ComplexListNode next;
        //拷贝每个node
        while (Objects.nonNull(node)) {
            ComplexListNode newNode = new ComplexListNode();
            newNode.setValue(node.getValue());
            next = node.getNext();
            newNode.setNext(next);
            node.setNext(newNode);
            node = next;
        }
        node = head;
        //遍历设置随机指针
        while (Objects.nonNull(node) && Objects.nonNull(node.getNext())) {
            next = node.getNext();
            if (Objects.nonNull(node.getSibling())) {
                next.setSibling(node.getSibling().getNext());
            }
            node = next.getNext();
        }
        node = head;
        ComplexListNode newHead = head.getNext();
        //拆分
        while (Objects.nonNull(node) && Objects.nonNull(node.getNext())) {
            next = node.getNext();
            node.setNext(next.getNext());
            node = next;
        }
        return newHead;
    }
}
