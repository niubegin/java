package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ19ReguarMatch {

    public static void main(String[] args) {
        log.info("{}", check("aaa", 0, "ab*ac*a", 0));
        log.info("{}", check("aaa", 0, "ab*ac*ab", 0));
        log.info("{}", check("aaa", 0, "aa*ac*a", 0));
    }

    /**
     * 正则匹配 如：aaa匹配ab*ac*a 使用递归方法
     */
    private static boolean check(String source, int sourceIndex, String reguarEx, int reguarIndex) {
        //越界返回false
        if (sourceIndex > source.length() || reguarIndex > reguarEx.length()) {
            return false;
        }
        //均比较完了最后一个字符，返回true
        if (sourceIndex == source.length()) {
            if (reguarIndex == reguarEx.length()) {
                return true;
            } else {
                return false;
            }
        }
        char sc = source.charAt(sourceIndex);
        char rc = reguarEx.charAt(reguarIndex);
        boolean hasNext = false;
        char next = '\u0000';
        if (reguarIndex + 1 < reguarEx.length()) {
            hasNext = true;
            next = reguarEx.charAt(reguarIndex + 1);
        }
        //当前字符相同，或者是.，则继续
        if (sc == rc || rc == '.') {
            //有下一个字符，且是*
            if (hasNext && next == '*') {
                //source下一个字符仍和当前正则比较，比如aaa和a*
                //注意不要用++sourceIndex，会导致下一个check参数错误
                return check(source, sourceIndex + 1, reguarEx, reguarIndex) ||
                    //source和正则下一组比较，比如aaa和a*a*a*a*
                    check(source, sourceIndex, reguarEx, reguarIndex + 2);
            } else {
                //正则后面不是*，分别比较后面字符
                return check(source, sourceIndex + 1, reguarEx, reguarIndex + 1);
            }
        } else {
            //有下一个字符，且是*
            if (hasNext && next == '*') {
                //soure当前字符和正则2个后比较
                return check(source, sourceIndex, reguarEx, reguarIndex + 2);
            } else {
                return false;
            }
        }
    }
}
