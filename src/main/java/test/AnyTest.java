package test;

import java.math.BigDecimal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AnyTest {

    public static void main(String[] args) {
        log.info("{}", 2 / 2);
        log.info("{}", "" + null);
        Double sumVolume = 2997d;
        Long shelfVolume = 2500000000L;
        compute(sumVolume, shelfVolume);
        compute(100800d, shelfVolume);
        compute(100800d, shelfVolume);
        compute(100800d, shelfVolume);
        compute(100800d, shelfVolume);
    }

    private static void compute(Double sumVolume, Long shelfVolume) {
        Double vacancy = 1 - sumVolume / shelfVolume;
        log.info("{}", vacancy);
        if (vacancy > 1) {
            vacancy = 1.00;
        } else if (vacancy < 0) {
            vacancy = 0.00;
        }
        BigDecimal bigDecimal = new BigDecimal(vacancy);
        double vacancyReal = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info("{}", vacancyReal);
    }
}
