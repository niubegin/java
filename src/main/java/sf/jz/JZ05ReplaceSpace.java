package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ05ReplaceSpace {
    public static void main(String[] args) {
        char[] source = {'W', 'e', ' ', 'a', 'r', 'e', ' ', 'h', 'a', 'p', 'p', 'y', '.'};
        char[] target = replaceSpace(source);
        log.info("{}", target);
    }

    /**
     * 替换字符串里面的空格为20%，比如: We are happy.
     * 自己实现的版本，时间复杂度O(2n)，空间复杂度：O(2n)
     * @param source
     * @return
     */
    private static char[] replaceSpace(char[] source) {
        int size = source.length;
        for (char c : source) {
            if (' ' == c) {
                size += 2;
            }
        }
        char[] target = new char[size];
        int index = 0;
        for (char c : source) {
            if (' ' == c) {
                target[index++] = '2';
                target[index++] = '0';
                target[index++] = '%';
            } else {
                target[index++] = c;
            }
        }

        return target;
    }
}
