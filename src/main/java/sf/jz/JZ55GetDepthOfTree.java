package sf.jz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ55GetDepthOfTree {

    /**
     * 要点：层序遍历；递归遍历；
     */
    public static void main(String[] args) {
        Integer[] nodesArr = {1, 2, 3, 4, 5, null, 6, null, null, 7, null, null, null, null, null};
        TreeNode root = TreeNode.build(nodesArr);
        log.info("{}", root);
        log.info("{}", get(root));
        log.info("{}", get2(root));
    }

    /**
     * 层序遍历计算
     */
    private static int get(TreeNode node) {
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        //初始化
        list1.add(node);
        List<TreeNode> curList = list1;
        List<TreeNode> cacheList = list2;
        List<TreeNode> tmpList;
        int depth = 0;
        while (curList.size() > 0) {
            // 层数加1
            depth++;
            // 遍历当前层，子元素添加到下一层
            for (TreeNode tmpNode : curList) {
                if (Objects.nonNull(tmpNode.getLeft())) {
                    cacheList.add(tmpNode.getLeft());
                }

                if (Objects.nonNull(tmpNode.getRight())) {
                    cacheList.add(tmpNode.getRight());
                }
            }

            //清空并交换
            curList.clear();
            tmpList = curList;
            curList = cacheList;
            cacheList = tmpList;
        }

        return depth;
    }

    /**
     * 递归计算，代码更简洁
     */
    private static int get2(TreeNode node) {
        if (Objects.isNull(node)) {
            return 0;
        }

        int leftDepth = get2(node.getLeft());
        int righttDepth = get2(node.getRight());
        return Math.max(leftDepth, righttDepth) + 1;
    }
}
