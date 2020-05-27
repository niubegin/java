package sf.jz;

public class JZ10Fibonacci {
    public static void main(String[] args) {
        System.out.println(fn(39));
    }

    /**
     * f(0)=0,f(1)=1,f(n)=f(n-1)+f(n-2)
     *
     * @param n
     * @return
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
