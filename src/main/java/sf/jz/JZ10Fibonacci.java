package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ10Fibonacci {

    /**
     * 要点：去重优化
     */
    public static void main(String[] args) {
        log.info("{}", fn(10));
        log.info("{}", fn(3000));
        //int会溢出
        log.info("{}", fn(5000));
    }

    /**
     * f(0)=0,f(1)=1,f(n)=f(n-1)+f(n-2)
     */
    private static int fn(int n) {
        if (n == 0) {
            return 0;
        }

        if (n == 1) {
            return 1;
        }

        int a = 0;
        int b = 1;
        int c = 0;
        for (int i = 0; i < n - 1; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return c;
    }
}
