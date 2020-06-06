package sf.jz;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ12FindPathInMatrix {

    public static void main(String[] args) {
        JZ12FindPathInMatrix jz12FindPathInMatrix = new JZ12FindPathInMatrix();
        jz12FindPathInMatrix.findTarget();
    }

    private void findTarget() {
        char[][] matrix = {{'a', 'b', 't', 'g'}, {'c', 'f', 'c', 's'}, {'j', 'd', 'e', 'h'}};
        char[] target = {'b', 'f', 'c', 'e'};
        List<Node> nodes = new ArrayList<>();
        Path path = new Path();
        path.setNodes(nodes);
        path.setSize(0);
        log.info("{},Path:{}", find(matrix, target, path), path);
    }

    @Data
    private class Node {

        private int x;
        private int y;
    }

    @Data
    private class Path {

        private List<Node> nodes;
        private int size;

        private Node getLast() {
            if (size > 0) {
                return nodes.get(size - 1);
            }
            return null;
        }
    }

    private boolean find(char[][] martix, char[] target, Path path) {
        int x = martix.length;
        int y = martix[0].length;
        int[] direct = {};
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                if (!findInPosition(martix, target, path, i, j)) {
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 在矩阵中递归查找target的一个节点
     *
     * @param martix 矩阵
     * @param target 要查找的路径
     * @param path 现有查找到的节点路径
     * @param i 坐标x
     * @param j 坐标y
     */
    private boolean findInPosition(char[][] martix, char[] target, Path path, int i, int j) {
        Node preNode = path.getLast();
        //越界返回
        if (i < 0 || j < 0 || i >= martix.length || j >= martix[0].length) {
            return false;
        }
        //是已经存在的节点则，不继续
        if (Objects.nonNull(preNode) && i == preNode.getX() && j == preNode.getY()) {
            return false;
        }
        //都找到了，返回true
        if (path.getSize() == target.length) {
            return true;
        }
        //当前值正确
        if (martix[i][j] == target[path.getSize()]) {
            Node node = new Node();
            node.setX(i);
            node.setY(j);
            path.getNodes().add(node);
            path.setSize(path.getSize() + 1);
            //在上面查找
            boolean find = findInPosition(martix, target, path, i - 1, j) ||
                //在右边查找
                findInPosition(martix, target, path, i, j + 1) ||
                //在下边查找
                findInPosition(martix, target, path, i + 1, j) ||
                //在左边查找
                findInPosition(martix, target, path, i, j - 1);
            if (!find) {
                //都没找到，则删除本节点
                path.setSize(path.getSize() - 1);
                path.getNodes().remove(path.getSize());
                return false;
            }
        }
        return false;
    }
}
