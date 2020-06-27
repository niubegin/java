package sf.jz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ37SerializeAndDeserializeTree {

    static Integer[] tree = new Integer[1];

    public static void main(String[] args) {
        Integer[] arr = {17, 10, 30, 6, 14, 26, 34, 4, 8, 12, 16, 24, 28, 32, 36};
        TreeNode node = TreeNode.build(arr);
        serialize(node, 0);
        for (Integer it : tree) {
            log.info("{}", it);
        }
        TreeNode newNode = deserialize(tree, 0);
        log.info("{}", newNode);
    }

    private static void serialize(TreeNode node, int index) {
        if (Objects.isNull(node)) {
            add(index, null);
        } else {
            add(index, node.getIntValue());
            serialize(node.getLeft(), 2 * index + 1);
            serialize(node.getRight(), 2 * index + 2);
        }
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

    private static TreeNode deserialize(Integer[] tree, int index) {
        if (Objects.isNull(tree) || tree.length == 0 || index >= tree.length || Objects.isNull(tree[index])) {
            return null;
        }
        TreeNode treeNode = TreeNode.builder().intValue(tree[index]).build();
        treeNode.setLeft(deserialize(tree, 2 * index + 1));
        treeNode.setRight(deserialize(tree, 2 * index + 2));
        return treeNode;
    }
}
