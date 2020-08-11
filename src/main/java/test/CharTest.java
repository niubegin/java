package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CharTest {

    public static void main(String[] args) {

        log.info("{}", Character.MAX_VALUE);
        log.info("{}", Character.MIN_VALUE);
        log.info("{}", Character.SIZE);
        char c = '在';
        log.info("{}", charToByte(c));
        c = '\0';//表示结束符，它的ascll码是0，这句话的意思和 char c=0 是一个意思。
        log.info("{}", c);
    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }
}
