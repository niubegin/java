package sf.jz;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JZ38Permutation {

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        permutate(chars, "");
        permutation("abc");
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

    /**
     * 给输入的str字符串中的字符进行全排列
     */
    private static void permutation(String str) {
        //如果字符串为空，直接返回
        if (StringUtils.isEmpty(str)) {
            return;
        }
        //否则将字符串转换为字符数字，并从字符0位置开始进行全排列
        permutation(str.toCharArray(), 0);
    }

    private static void permutation(char[] chars, int pos) {
        if (pos == chars.length - 1) {
            log.info("{}", chars);
        }
        for (int i = pos; i < chars.length; i++) {
            //首部字符和它后面的字符（包括自己）进行交换
            char temp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = temp;
            //递归求后面的字符的排列
            permutation(chars, pos + 1);
            //由于前面交换了一下，所以chs的内容改变了，我们要还原回来
            temp = chars[i];
            chars[i] = chars[pos];
            chars[pos] = temp;
        }
    }
}
