package sf.jz;

import java.util.Objects;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ26HasSubTree {

    public static void main(String[] args) {
        char[] charsA = {'8', '8', '7', '9', '2', '\0', '\0', '\0', '\0', '5', '4'};
        char[] charsB = {'8', '9', '2'};
        TreeNode rootA = TreeNode.build(charsA);
        TreeNode rootB = TreeNode.build(charsB);
        log.info("{}", hasSubTree(rootA, rootB));
        log.info("{}", hasSubTree(rootA, null));
        log.info("{}", hasSubTree(null, rootB));
    }

    private static boolean hasSubTree(TreeNode a, TreeNode b) {
        //必须加上b的判空
        if (Objects.isNull(a) && Objects.nonNull(b)) {
            return false;
        }

        if (Objects.isNull(b)) {
            return true;
        }

        boolean result = false;
        if (Objects.equals(a.getValue(), b.getValue())) {
            result = isSame(a, b);
        }

        if (result) {
            return true;
        }

        return hasSubTree(a.getLeft(), b) || hasSubTree(a.getRight(), b);
    }

    private static boolean isSame(TreeNode a, TreeNode b) {
        //必须加上b的判空
        if (Objects.isNull(a) && Objects.nonNull(b)) {
            return false;
        }

        if (Objects.isNull(b)) {
            return true;
        }

        return Objects.equals(a.getValue(), b.getValue()) &&
            isSame(a.getLeft(), b.getLeft()) &&
            isSame(a.getRight(), b.getRight());
    }
}
