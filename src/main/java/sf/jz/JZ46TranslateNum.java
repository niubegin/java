package sf.jz;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class JZ46TranslateNum {

    private static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    /**
     * 要点：递归
     */
    public static void main(String[] args) {
        translate("12258", 0, new StringBuilder());
    }

    private static void translate(String number, int index, StringBuilder sb) {
        if (Integer.parseInt(number) < 0) {
            return;
        }

        if (index >= number.length()) {
            log.info("{}", sb.toString());
            return;
        }

        //取1位转换
        String char1 = getString(number, index, 1);
        if (StringUtils.isNotEmpty(char1)) {
            sb.append(char1);
            translate(number, index + 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        //取2位转换
        String char2 = getString(number, index, 2);
        if (StringUtils.isNotEmpty(char2)) {
            sb.append(char2);
            translate(number, index + 2, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    private static String getString(String number, int index, int size) {
        if (index > number.length() - 1 || (index + size) > number.length()) {
            return null;
        }

        byte cr = Byte.parseByte(number.substring(index, index + size));
        return getChar(cr);
    }

    private static String getChar(byte index) {
        if (index >= chars.length) {
            return null;
        }

        return String.valueOf(chars[index]);
    }
}
