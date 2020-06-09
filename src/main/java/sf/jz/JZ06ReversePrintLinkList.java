package sf.jz;

import java.util.Objects;
import java.util.Stack;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ06ReversePrintLinkList {

    public static void main(String[] args) {
        LinkList linkList = new LinkList();
        linkList.insert(Node.builder().value(1).next(null).build());
        linkList.insert(Node.builder().value(2).next(null).build());
        linkList.insert(Node.builder().value(3).next(null).build());
        linkList.reversePrintUsingRecursion(linkList.getHead());
        linkList.reversePrintUsingStack(linkList.getHead());
        linkList.reversePrintUsingArray(linkList.getHead());
        linkList.reversePrintReverseAndRecover(linkList.getHead());
    }

    @Data
    @Builder
    private static class Node {

        private int value;
        private Node next;
    }

    @Data
    private static class LinkList {

        private Node head = null;
        private Node tail = null;

        public void insert(Node node) {
            if (Objects.isNull(head)) {
                head = node;
                tail = node;
            }

            tail.setNext(node);
            tail = node;
        }

        /**
         * 反转返回新的头节点，其实就是原来的尾节点
         */
        public Node reverse(Node head) {
            Node currentNode = head;
            Node preNode = null;
            while (Objects.nonNull(currentNode)) {
                Node nextNode = currentNode.getNext();
                currentNode.setNext(preNode);
                preNode = currentNode;
                currentNode = nextNode;
            }

            //返回前一个节点，而不是currentNode，当前节点为空
            return preNode;
        }

        /**
         * 递归调用，原理是：通过JVM函数调用栈实现，时间复杂度O(n)，空间复杂度O(0)
         */
        public void reversePrintUsingRecursion(Node node) {
            if (Objects.isNull(node.getNext())) {
            } else {
                //打印后续节点
                reversePrintUsingRecursion(node.getNext());
            }

            //打印完后续节点后再打印自己
            log.info("{}", node.getValue());
        }

        /**
         * 使用栈打印，时间复杂度O(n)，空间复杂度O(n)
         */
        public void reversePrintUsingStack(Node node) {
            Stack<Integer> stack = new Stack<>();
            while (Objects.nonNull(node)) {
                stack.push(node.getValue());
                node = node.getNext();
            }

            while (!stack.empty()) {
                log.info("{}", stack.pop());
            }
        }

        /**
         * 反转后打印，然后恢复 1->2->3 1<-2<-3
         */
        public void reversePrintReverseAndRecover(Node head) {
            Node newHead = reverse(head);
            while (Objects.nonNull(newHead)) {
                log.info("{}", newHead.getValue());
                newHead = newHead.getNext();
            }
            reverse(newHead);
        }

        /**
         * 正序遍历放到数组内，反序遍历数组输出，时间复杂度O(n)，空间复杂度O(0)
         */
        public void reversePrintUsingArray(Node head) {
            int num = 0;
            //设置头节点
            Node node = head;
            //计算节点总数，或者创建时就计数
            while (Objects.nonNull(node)) {
                node = node.getNext();
                num++;
            }

            int[] array = new int[num];
            int index = 0;
            //重置头节点
            node = head;
            while (Objects.nonNull(node)) {
                array[index++] = node.getValue();
                node = node.getNext();
            }

            for (int i = num - 1; i >= 0; i--) {
                log.info("{}", array[i]);
            }
        }
    }
}
