package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ20CheckStringIsNumber {

    public static void main(String[] args) {

    }

    /**
     * 检查字符串是否是数字 合法：+100；5e2；-123；3.14；-1E-16 非法：12e；1a3.14；1.2.3；+-5；12e+5.4
     */
    private static boolean check(String value) {
        char cur = '\u0000';
        for (int index = 0; index < value.length(); index++) {
            cur = value.charAt(index);
            if (cur == '+' || cur == '-') {

            }
        }
        return false;
    }
}
