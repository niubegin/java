package sf.jz;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ16Power {

    public static void main(String[] args) {
        log.info("{}", power(4, 2));
        log.info("{}", power(0, -2));
    }

    /**
     * 数值的整数次方，需要考虑异常情况
     */
    private static double power(double base, int exponent) {
        if (equals(base, 0.0d) && exponent < 0) {
            throw new IllegalArgumentException("错误的参数");
        }
        int e = Math.abs(exponent);
        double p = powerInner(base, e);
        if (exponent < 0) {
            p = 1 / p;
        }
        return p;
    }

    private static double powerInner(double base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        if (exponent == 1) {
            return base;
        }

        double p = powerInner(base, exponent >> 1);
        p = p * p;
        if ((exponent & 1) == 1) {
            p = base * p;
        }
        return p;
    }

    private static boolean equals(double a, double b) {
        return (a - b > -0.00000001) && (a - b < 0.00000001);
    }
}
