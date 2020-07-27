package sf.jz;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ48GetMaxNoDuplicateSubString {

    /**
     * 要点：动态规划
     * https://zhuanlan.zhihu.com/p/80538556
     * 动态规划的题目难点是背后的思想
     *
     * 我们通常按如下4个步骤来设计一个动态规划算法：
     *
     * 1.刻画一个最优解的结构特征
     * 2.递归地定义最优解的值
     * 3.计算最优解的值，通常采用自底向上的方法。
     * 4.利用计算出的信息构造一个最优解
     * 步骤1-3是动态规划算法求解问题的基础。如果我们仅仅需要一个最优解的值，而非解本身，可以忽略步骤四。
     * 如果确实要做步骤4，有时候就需要在执行步骤3的过程中维护一些额外信息，以便用来构造一个最优解。
     */
    public static void main(String[] args) {
        log.info("{}", get("arabcacfr"));
    }

    /**
     * 例：arabcacfr，结果：acfr
     */
    private static int get(String str) {
        int currentLength = 0;
        int maxLength = 0;
        char[] chars = str.toCharArray();
        Map<Character, Integer> charMap = new HashMap<>();
        for (int index = 0; index < chars.length; index++) {
            if (charMap.containsKey(chars[index])) {
                // 如果该字符出现过
                int preIndex = charMap.get(chars[index]);
                // 计算当前字符与上一次出现时的距离
                int d = index - preIndex;
                if (d > currentLength) {
                    // 如果上一次出现的位置不在当前最长不重复的子串中
                    currentLength++;
                } else {
                    // 如果在
                    currentLength = d;
                }
            } else {
                currentLength++;
            }
            if (currentLength > maxLength) {
                maxLength = currentLength;
            }
            //更新该字符的出现位置，已经存在的话也用最新的index覆盖
            charMap.put(chars[index], index);
        }

        return maxLength;
    }
}
