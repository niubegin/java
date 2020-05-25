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
        char[] nodeValues = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
        for (char nodeValue : nodeValues) {
            log.info("{}->{}", nodeValue, jz08NextNodeInTree.findNext(head, nodeValue));
        }
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

    private TreeNode findNext(TreeNode head, char treeNodeValue) {
        return findNext(findNode(head, treeNodeValue));
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
            //与有无左子树无关
            //右子树为空
            if (Objects.isNull(treeNode.getRight())) {
                return null;
            } else {
                //找到右子树的第一个节点
                TreeNode node = treeNode.getRight();
                while (Objects.nonNull(node.getLeft())) {
                    node = node.getLeft();
                }
                return node;
            }
        } else {
            //有父节点
            //是父节点的左子树
            if (treeNode.getParent().getLeft() == treeNode) {
                if (Objects.nonNull(treeNode.getRight())) {
                    //有右子树，找第一个节点，同上
                    TreeNode node = treeNode.getRight();
                    while (Objects.nonNull(node.getLeft())) {
                        node = node.getLeft();
                    }
                    return node;
                } else {
                    //没右子树，返回父节点
                    return treeNode.getParent();
                }
            } else {
                //是父节点的右子树
                //没有右孩子
                if (Objects.isNull(treeNode.getRight())) {
                    //在上代节点中找到某个节点是父节点的左孩子，则父节点是下一个
                    TreeNode node = treeNode;
                    //存在父节点
                    while (Objects.nonNull(node.getParent())
                            //不是父节点的左孩子，则继续向上代找
                            && node != node.getParent().getLeft()) {
                        node = node.getParent();
                    }
                    return node.getParent();
                } else {
                    //有右孩子，返回右孩子
                    return treeNode.getRight();
                }
            }
        }
    }
}
