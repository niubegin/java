package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ28IsTreeSymmetrical {

    public static void main(String[] args) {
        char[] charsA = {'8', '8', '7', '9', '2', '\0', '\0', '\0', '\0', '5', '4'};
        char[] charsB = {'8', '9', '9'};
        TreeNode rootA = TreeNode.build(charsA);
        TreeNode rootB = TreeNode.build(charsB);
        log.info("{}", isSymmetrical(rootA, rootA));
        log.info("{}", isSymmetrical(rootB, rootB));
    }

    /**
     * 是否对称
     */
    private static boolean isSymmetrical(TreeNode nodeA, TreeNode nodeB) {
        if (Objects.isNull(nodeA) && Objects.isNull(nodeB)) {
            return true;
        }

        if (Objects.isNull(nodeA) || Objects.isNull(nodeB)) {
            return false;
        }

        return Objects.equals(nodeA.getValue(), nodeB.getValue())
            && isSymmetrical(nodeA.getLeft(), nodeB.getRight())
            && isSymmetrical(nodeA.getRight(), nodeB.getLeft());
    }
}
