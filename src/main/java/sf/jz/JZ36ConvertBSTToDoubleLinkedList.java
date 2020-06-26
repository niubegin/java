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
        //如果左子树只有一个节点，则进行单独设置，并根据是父节点的左子树还是右子树决定返回节点
        if (Objects.nonNull(node.getLeft()) && Objects.isNull(node.getLeft().getLeft())) {
            tmp = node.getLeft();
            tmp.setRight(node);
            //都是从左侧进入，则为头节点
            if (allLeft) {
                head = tmp;
            }
            tmp = node.getRight();
            if (Objects.nonNull(tmp)) {
                tmp.setLeft(node);
            }
            if (left) {
                //右节点为空，返回node
                return Objects.isNull(node.getRight()) ? node : node.getRight();
            } else {
                return node.getLeft();
            }
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
