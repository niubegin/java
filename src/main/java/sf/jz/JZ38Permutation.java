package sf.jz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JZ38Permutation {

    public static void main(String[] args) {
        char[] chars = {'a', 'b', 'c'};
        permutate(chars, "");
        permutation("abc");
        combination(chars);
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

    /**
     * 根据交换进行排列，降低了空间复杂度
     */
    private static void permutation(char[] chars, int pos) {
        if (pos == chars.length - 1) {
            log.info("{}", chars);
            return;
        }
        for (int i = pos; i < chars.length; i++) {
            //首部字符和它后面的字符（包括自己）进行交换
            char temp;
            if (i > pos) {
                temp = chars[i];
                chars[i] = chars[pos];
                chars[pos] = temp;
            }
            //递归求后面的字符的排列
            permutation(chars, pos + 1);
            //由于前面交换了一下，所以chs的内容改变了，我们要还原回来
            if (i > pos) {
                temp = chars[i];
                chars[i] = chars[pos];
                chars[pos] = temp;
            }
        }
    }

    /**
     * 求字符串内字符的所有组合
     */
    private static void combination(char[] chars) {
        List<Character> com = new ArrayList<>();
        for (int i = 1; i <= chars.length; i++) {
            combination(chars, 0, i, com);
        }
    }

    private static void combination(char[] chars, int pos, int len, List<Character> com) {
        if (len == 0) {
            log.info("组合：{}", com);
            return;
        }

        //不能放到上一个if内去判断返回，因为只有len=0时才是组合成功才输出
        if (pos >= chars.length) {
            return;
        }

        //选取当前字符
        com.add(chars[pos]);
        //在后面选取len-1个字符
        combination(chars, pos + 1, len - 1, com);
        //还原选择
        com.remove(com.size() - 1);
        //不选择当前字符，在余下选择len个
        combination(chars, pos + 1, len, com);
    }
}
