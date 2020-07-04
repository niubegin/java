package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JZ37SerializeAndDeserializeTree {

    static Integer[] tree = new Integer[1];
    static int index = 0;

    /**
     * 要点：递归
     */
    public static void main(String[] args) {
        Integer[] arr = {17, 10, 30, 6, 14, 26, 34, 4, 8, 12, 16, 24, 28, 32, 36};
        TreeNode node = TreeNode.build(arr);
        serialize(node, 0);
        for (Integer it : tree) {
            log.info("{}", it);
        }
        TreeNode newNode = deserialize(tree, 0);
        log.info("{}", newNode);
        StringBuilder sb = new StringBuilder();
        serialize2(node, sb);
        log.info("{}", sb);
        TreeNode treeNode = deserialize2(sb.toString().split(","));
        log.info("{}", treeNode);
    }

    /**
     * 序列化二叉树，给每个节点固定的数组索引的方式，比较浪费空间
     */
    private static void serialize(TreeNode node, int index) {
        if (Objects.isNull(node)) {
            add(index, null);
        } else {
            add(index, node.getIntValue());
            serialize(node.getLeft(), 2 * index + 1);
            serialize(node.getRight(), 2 * index + 2);
        }
    }

    /**
     * 序列化二叉树，先序遍历，给空节点赋值为$，比上面算法省空间
     */
    private static void serialize2(TreeNode node, StringBuilder sb) {
        if (Objects.isNull(node)) {
            sb.append("$,");
            return;
        }

        sb.append(node.getIntValue()).append(",");
        serialize2(node.getLeft(), sb);
        serialize2(node.getRight(), sb);
    }

    private static void add(int index, Integer target) {
        if (index >= tree.length) {
            Integer[] newTree = new Integer[index + 1];
            for (int i = 0; i < tree.length; i++) {
                newTree[i] = tree[i];
            }
            tree = newTree;
        }

        tree[index] = target;
    }

    /**
     * 反序列号，使用固定数组索引
     */
    private static TreeNode deserialize(Integer[] tree, int index) {
        if (Objects.isNull(tree) || tree.length == 0 || index >= tree.length || Objects.isNull(tree[index])) {
            return null;
        }
        TreeNode treeNode = TreeNode.builder().intValue(tree[index]).build();
        treeNode.setLeft(deserialize(tree, 2 * index + 1));
        treeNode.setRight(deserialize(tree, 2 * index + 2));
        return treeNode;
    }

    /**
     * 二叉树反序列化，顺序读取重建
     */
    private static TreeNode deserialize2(String[] tree) {
        Integer it = get(tree);
        if (Objects.nonNull(it)) {
            TreeNode treeNode = TreeNode.builder().intValue(it).build();
            treeNode.setLeft(deserialize2(tree));
            treeNode.setRight(deserialize2(tree));
            return treeNode;
        }
        return null;
    }

    private static Integer get(String[] tree) {
        if (Objects.isNull(tree) || tree.length == 0 || index >= tree.length) {
            return null;
        }

        if (StringUtils.equals(tree[index], "$")) {
            index++;
            return null;
        }
        Integer it = Integer.valueOf(tree[index]);
        index++;
        return it;
    }
}
