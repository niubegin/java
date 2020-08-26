package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ54GetKthNode {

    /**
     * 要点：中序遍历
     */
    public static void main(String[] args) {
        Integer[] nodes = {5, 3, 7, 2, 4, 6, 8};
        TreeNode tmp = TreeNode.build(nodes);
        log.info("{}", tmp);
        log.info("{}", get(tmp, 3));
    }

    private static int count = 0;

    private static TreeNode get(TreeNode node, int kth) {
        if (Objects.isNull(node)) {
            return null;
        }

        TreeNode target = null;
        if (Objects.nonNull(node.getLeft())) {
            target = get(node.getLeft(), kth);
            if (Objects.nonNull(target)) {
                return target;
            }
        }

        // count 计数多了一个变量，可以用kth--替代实现
        /*
        count++;
        if (count == kth) {
            return node.getIntValue();
        }
         */
        kth--;
        if (kth == 1) {
            return node;
        }

        // log.debug("{}", node.getIntValue());

        if (Objects.nonNull(node.getRight())) {
            target = get(node.getRight(), kth);
            if (Objects.nonNull(target)) {
                return target;
            }
        }

        return null;
    }

}
