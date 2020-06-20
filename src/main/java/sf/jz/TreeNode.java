package sf.jz;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TreeNode {

    private int level;
    private char value;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    /**
     * 不实现的话，log输出时因为有parent循环引用会导致栈溢出
     */
    @Override
    public String toString() {
        return "TreeNode{" +
            "level=" + level +
            ", value=" + value +
            ", left=" + left +
            ", right=" + right +
            '}';
    }

    public static TreeNode build(char[] arr) {
        TreeNode root = createTree(arr, 0, 0);
        return root;
    }

    /**
     * 根据数组创建树；规定：\0表示空节点
     */
    private static TreeNode createTree(char[] arr, int index, int level) {
        if (index >= arr.length) {
            return null;
        }

        if (arr[index] == '\0') {
            return null;
        }

        TreeNode node = TreeNode.builder().value(arr[index]).level(level).build();
        node.setLeft(createTree(arr, 2 * index + 1, level + 1));
        node.setRight(createTree(arr, 2 * index + 2, level + 1));
        return node;
    }
}
