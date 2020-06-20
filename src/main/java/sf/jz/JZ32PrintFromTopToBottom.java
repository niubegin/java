package sf.jz;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ32PrintFromTopToBottom {

    public static void main(String[] args) {
        char[] charsA = {'8', '8', '7', '9', '2', '\0', '\0', '\0', '\0', '5', '4'};
        TreeNode rootA = TreeNode.build(charsA);
        log.info("{}", rootA);
        JZ32PrintFromTopToBottom jz32PrintFromTopToBottom = new JZ32PrintFromTopToBottom();
        jz32PrintFromTopToBottom.print(rootA);
        jz32PrintFromTopToBottom.printInDifferentLines(rootA);
        jz32PrintFromTopToBottom.printInDifferentLines2(rootA);
    }

    private Queue<TreeNode> queue = new ArrayDeque<>();

    /**
     * 从上到下打印二叉树：使用队列先进先出打印
     */
    private void print(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        queue.add(node);
        while (queue.size() > 0) {
            //取到当前节点，并将左右孩子加到队列
            node = queue.poll();
            log.info("{}", node.getValue());
            if (Objects.nonNull(node.getLeft())) {
                queue.add(node.getLeft());
            }
            if (Objects.nonNull(node.getRight())) {
                queue.add(node.getRight());
            }
        }
    }

    /**
     * 创建树的时候就指定层，打印时，发现层变化就打印换行
     */
    private void printInDifferentLines(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        queue.add(node);
        int level = node.getLevel();
        while (queue.size() > 0) {
            //取到当前节点，并将左右孩子加到队列
            node = queue.poll();
            if (level != node.getLevel()) {
                level = node.getLevel();
                log.info("---");
            }
            log.info("{}", node.getValue());
            if (Objects.nonNull(node.getLeft())) {
                queue.add(node.getLeft());
            }
            if (Objects.nonNull(node.getRight())) {
                queue.add(node.getRight());
            }
        }
    }

    /**
     * 两个计数器，一个记录本层要打印的个数，一个记录下层要打印的个数；类似于快慢指针的思路；本次打印数为0时，进行状态改变
     */
    private void printInDifferentLines2(TreeNode node) {
        if (Objects.isNull(node)) {
            return;
        }
        queue.add(node);
        int nextLevelNums = 0;
        int toBePrinted = 1;
        while (queue.size() > 0) {
            //取到当前节点，并将左右孩子加到队列
            node = queue.poll();
            log.info("{}", node.getValue());
            if (Objects.nonNull(node.getLeft())) {
                queue.add(node.getLeft());
                nextLevelNums++;
            }
            if (Objects.nonNull(node.getRight())) {
                queue.add(node.getRight());
                nextLevelNums++;
            }
            toBePrinted--;
            if (toBePrinted == 0) {
                toBePrinted = nextLevelNums;
                nextLevelNums = 0;
                log.info("---");
            }
        }
    }

    /**
     * 之字形打印二叉树
     */
    private void printInZigZag(TreeNode node) {

    }
}
