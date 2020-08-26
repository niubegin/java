package sf.jz;

import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ55IsBalancedTree {

    /**
     * 要点：平衡二叉树；递归遍历；优化减少递归次数；
     */
    public static void main(String[] args) {
        Integer[] nodesArr = {1, 2, 3, 4, 5, null, 6, null, null, 7, null, null, null, null, null};
        TreeNode root = TreeNode.build(nodesArr);
        log.info("{}", isBalanced(root));
        Integer[] nodesArr2 = {1, 2, 3, null, 5, null, 6, null, null, 7, null, null, null, null, null};
        TreeNode root2 = TreeNode.build(nodesArr2);
        log.info("{}", isBalanced(root2));
        log.info("{}", isBalanced(root2, new Depth()));
    }

    /**
     * 遍历，存在重复遍历的问题，性能不高
     */
    private static boolean isBalanced(TreeNode node) {
        if (Objects.isNull(node)) {
            return true;
        }

        int leftDepth = get2(node.getLeft());
        int righttDepth = get2(node.getRight());
        // 当前是平衡的，且子节点也平衡
        return Math.abs(leftDepth - righttDepth) <= 1 && isBalanced(node.getLeft()) && isBalanced(node.getRight());
    }

    /**
     * 获取节点深度
     */
    private static int get2(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }

        int leftDepth = get2(node.getLeft());
        int righttDepth = get2(node.getRight());
        return Math.max(leftDepth, righttDepth) + 1;
    }

    @Data
    private static class Depth {

        private int depth;
    }

    /**
     * Java是值传递，需要通过定义Depth对象类来实现调用函数知道函数内的修改
     */
    private static boolean isBalanced(TreeNode node, Depth depth) {
        if (Objects.isNull(node)) {
            depth.setDepth(0);
            return true;
        }

        Depth leftDepth = new Depth(), rightDepth = new Depth();
        if (isBalanced(node.getLeft(), leftDepth) &&
            isBalanced(node.getRight(), rightDepth)) {
            depth.setDepth(Math.max(leftDepth.getDepth(), rightDepth.getDepth()) + 1);
            if (Math.abs(leftDepth.getDepth() - rightDepth.getDepth()) <= 1) {
                return true;
            }
        }

        return false;
    }
}
