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
}
