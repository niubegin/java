package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ27GetMirrorOfTree {

    public static void main(String[] args) {
        char[] charsA = {'8', '8', '7', '9', '2', '\0', '\0', '\0', '\0', '5', '4'};
        TreeNode rootA = TreeNode.build(charsA);
        log.info("{}", rootA);
        log.info("{}", getMirror(rootA));
    }

    /**
     * 获取树的镜像
     * @param root
     * @return
     */
    private static TreeNode getMirror(TreeNode root) {
        if (Objects.isNull(root)) {
            return null;
        }

        //生成新的节点拷贝
        TreeNode mirrorRoot = TreeNode.builder().value(root.getValue()).build();
        mirrorRoot.setLeft(getMirror(root.getRight()));
        mirrorRoot.setRight(getMirror(root.getLeft()));
        return mirrorRoot;
    }
}
