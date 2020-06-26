package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ36ConvertBSTToDoubleLinkedList {

    static TreeNode head = null;

    public static void main(String[] args) {
        Integer[] arr = {17, 10, 30, 6, 14, 26, 34, 4, 8, 12, 16, 24, 28, 32, 36};
        TreeNode node = TreeNode.build(arr);
        covert(node, true, true);
        TreeNode cur = head;
        while (Objects.nonNull(cur)) {
            log.info("{}", cur.getIntValue());
            cur = cur.getRight();
        }
    }

    /**
     * 递归实现
     */
    private static TreeNode covert(TreeNode node, boolean allLeft, boolean left) {
        if (Objects.isNull(node)) {
            return null;
        }
        TreeNode tmp;
        //没有子节点返回自己
        if (Objects.isNull(node.getLeft())) {
            if (allLeft) {
                head = node;
            }
            return node;
        } else {
            //得到pre节点
            tmp = covert(node.getLeft(), allLeft && true, true);
            tmp.setRight(node);
            node.setLeft(tmp);
            //得到next节点
            tmp = covert(node.getRight(), allLeft && false, false);
            node.setRight(tmp);
            tmp.setLeft(node);
        }
        if (left) {
            return node.getRight();
        } else {
            return node.getLeft();
        }
    }
}
