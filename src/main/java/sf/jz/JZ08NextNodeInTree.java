package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ08NextNodeInTree {

    public static void main(String[] args) {
        JZ08NextNodeInTree jz08NextNodeInTree = new JZ08NextNodeInTree();
        TreeNode head = jz08NextNodeInTree.buildTree();
        log.info("{}", head);
        TreeNode node = jz08NextNodeInTree.findNode(head, 'c');
        log.info("{}", node);
    }

    /**
     * 中序二叉树：{d,b,h,e,i,a,f,c,g}
     */
    private TreeNode buildTree() {
        TreeNode h = TreeNode.builder().value('h').build();
        TreeNode i = TreeNode.builder().value('i').build();
        TreeNode e = TreeNode.builder().value('e').left(h).right(i).build();
        TreeNode d = TreeNode.builder().value('d').build();
        TreeNode b = TreeNode.builder().value('b').left(d).right(e).build();
        TreeNode f = TreeNode.builder().value('f').build();
        TreeNode g = TreeNode.builder().value('g').build();
        TreeNode c = TreeNode.builder().value('c').left(f).right(g).build();
        TreeNode a = TreeNode.builder().value('a').left(b).right(c).build();
        b.setParent(a);
        c.setParent(a);
        d.setParent(b);
        e.setParent(b);
        f.setParent(c);
        g.setParent(c);
        h.setParent(e);
        i.setParent(e);
        return a;
    }

    /**
     * 根据值查找节点
     */
    private TreeNode findNode(TreeNode treeNode, char value) {
        if (Objects.isNull(treeNode)) {
            return null;
        }

        TreeNode resultTreeNode;
        if (treeNode.getValue() == value) {
            return treeNode;
        } else {
            resultTreeNode = findNode(treeNode.getLeft(), value);
            if (Objects.nonNull(resultTreeNode)) {
                return resultTreeNode;
            } else {
                resultTreeNode = findNode(treeNode.getRight(), value);
                if (Objects.nonNull(resultTreeNode)) {
                    return resultTreeNode;
                }
            }
        }

        return null;
    }

    /**
     * 查找中序遍历的下一个节点
     */
    private TreeNode findNext(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return null;
        }
        //没有父节点
        if (Objects.isNull(treeNode.getParent())) {
            //右子树为空
            if (Objects.isNull(treeNode.getLeft())) {
                return null;
            } else {
                //找到右子树的第一个节点
                return null;
            }
        } else {
            //有父节点
            //是父节点的左子树，
            if (treeNode.getParent().getLeft() == treeNode) {
                //父节点就是下一个节点
                return treeNode.getParent();
            } else {
                //是父节点的右子树，找到父节点的父节点
                return null;
            }
        }
    }

}
