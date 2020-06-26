package sf.jz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ34FindPathInTree {

    public static void main(String[] args) {
        Integer[] arr = {10, 5, 12, 4, 7, null, null};
        TreeNode root = TreeNode.build(arr);
        find(root, 22, new ArrayList<Integer>());
    }

    /**
     * 查找路径是否是目标值，如果是，则输出
     */
    private static void find(TreeNode node, int target, List<Integer> path) {
        if (Objects.isNull(node)) {
            return;
        }
        //找到则输出
        if (Objects.isNull(node.getLeft()) && Objects.isNull(node.getRight())) {
            if (node.getIntValue().intValue() == target) {
                path.add(node.getIntValue());
                log.info("{}", path);
            }
        }
        //在左子树查找
        if (Objects.nonNull(node.getLeft())) {
            if (target - node.getIntValue() > 0) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(node.getIntValue());
                find(node.getLeft(), target - node.getIntValue(), newPath);
            }
        }
        //在右子树查找
        if (Objects.nonNull(node.getRight())) {
            if (target - node.getIntValue() > 0) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(node.getIntValue());
                find(node.getRight(), target - node.getIntValue(), newPath);
            }
        }
    }
}
