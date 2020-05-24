package sf.jz;

import lombok.Builder;
import lombok.Data;

    @Data
    @Builder
    public class TreeNode {
        private char value;
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;

        /**
         * 不实现的话，log输出时因为有parent循环引用会导致栈溢出
         * @return
         */
        @Override
        public String toString() {
            return "TreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
        }
    }
