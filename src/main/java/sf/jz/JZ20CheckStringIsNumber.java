package sf.jz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JZ20CheckStringIsNumber {

    public static void main(String[] args) {
        log.info("{}", check("3.14"));
        log.info("{}", check("+100"));
        log.info("{}", check("5e2"));
        log.info("{}", check("-1E-16"));
        log.info("{}", check(""));
        log.info("{}", check("5e-"));
        log.info("{}", check("12e"));
        log.info("{}", check("1a3.14"));
        log.info("{}", check("1.2.3"));
        log.info("{}", check("+-5"));
        log.info("{}", check("12e+5.4"));
    }

    /**
     * https://zhuanlan.zhihu.com/p/41347029
     * 检查字符串是否是数字 合法：+100；5e2；-123；3.14；-1E-16 非法：12e；1a3.14；1.2.3；+-5；12e+5.4
     * 解题思路
     * 设置三个标志符分别记录“+/-”、“e/E”和“.”是否出现过。
     *
     * 对于“+/-”： 正常来看它们第一次出现的话应该出现在字符串的第一个位置，如果它第一次出现在不是字符串首位，而且它的前面也不是“e/E”，那就不符合规则；
     *            如果是第二次出现，那么它就应该出现在“e/E”的后面，如果“+/-”的前面不是“e/E”，那也不符合规则；
     *            如果后面没有字符也不符合规则。
     * 对于“e/E”： 如果它的后面不接任何数字，就不符合规则；如果出现多个“e/E”也不符合规则。
     * 对于“.”： 出现多个“.”是不符合规则的。还有“e/E”的字符串出现“.”也是不符合规则的。 同时，要保证其他字符均为 0-9 之间的数字。
     */
    private static boolean check(String value) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        char curChar = '\u0000';
        char preChar = '\u0000';
        boolean hasFlag = false;
        boolean hasE = false;
        boolean hasDot = false;
        int length = value.length();
        for (int index = 0; index < length; index++) {
            curChar = value.charAt(index);
            if ((curChar == '+' || curChar == '-')) {
                //首次出现
                if (index == 0) {
                } else {
                    //前一个字符是e/E
                    preChar = value.charAt(index - 1);
                    if (preChar == 'e' || preChar == 'E') {

                    } else {
                        return false;
                    }
                    //后面没有字符，非法
                    if (index == length - 1) {
                        return false;
                    }
                }
                hasFlag = true;
            } else if (curChar == 'e' || curChar == 'E') {
                //是e/E
                //出现过
                if (hasE) {
                    return false;
                }
                //后面没有字符
                if (index == length - 1) {
                    return false;
                }
                hasE = true;
            } else if (curChar == '.') {
                //.
                //出现过.或e/E，那么错误
                if (hasDot || hasE) {
                    return false;
                }
                hasDot = true;
            } else if (curChar < '0' || curChar > '9') {
                //非数字
                return false;
            }
        }
        return true;
    }
}
