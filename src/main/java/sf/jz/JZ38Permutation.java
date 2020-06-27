package sf.jz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ38Permutation {

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        permutate(chars, "");
    }

    private static void permutate(char[] chars, String pre) {
        int len = chars.length;
        char[] newChars = new char[len - 1];
        for (int i = 0; i < len; i++) {
            //拼接当前字符
            pre += chars[i];
            if (len == 1) {
                //打印
                log.info("{}", pre);
                return;
            }

            int num = 0;
            for (int j = 0; j < len; j++) {
                if (j == i) {
                    continue;
                }

                newChars[num++] = chars[j];
            }

            permutate(newChars, pre);
            //本次字符使用完后移除
            pre = pre.substring(0, pre.length() - 1);
        }
    }
}
