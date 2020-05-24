import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeanUtils;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Slf4j
public class LambdaTest {

    @Data
    static class Trader {

        private String name;
        private String city;

        public Trader(String name, String city) {
            this.name = name;
            this.city = city;
        }
        //任务执行计数器
        private AtomicInteger excutingCounter = new AtomicInteger(0);
    }

    @Data
    static class Transaction {

        private Trader trader;
        private int year;
        private int money;

        public Transaction(Trader trader, int year, int money) {
            this.trader = trader;
            this.year = year;
            this.money = money;
        }
    }

    public static void main(String[] args) {
        //test0();
//        test();
        testFilter();
    }

    private static void testFilter() {
        List<Trader> traders = new ArrayList<>();
        traders.add(new Trader("1", "a"));
        traders.add(new Trader("2", "b"));
        List<Trader> traders1 = traders.stream().filter(t -> t.getName().equals("1")).collect(toList());
        traders1.forEach(t -> {
            Trader newT = new Trader("", "");
            BeanUtils.copyProperties(t, newT);
            t.setCity("c");
            t.getExcutingCounter().getAndIncrement();
            //并不会将计数器给恢复掉，需要手动恢复
            BeanUtils.copyProperties(newT, t);
            t.getExcutingCounter().getAndDecrement();
        });
        log.info("{},{}", traders, traders1);
    }

    private static void test0() {
        List<String> list = Arrays.asList("1");
        Arrays.asList(" a ", null, "b ");
        List<String> newList = list.stream()
            .filter(item -> !StringUtils.isEmpty(item) && !StringUtils.isEmpty(item.trim())).
                map(item -> item.trim()).collect(toList());
        System.out.println("list:" + list);
        System.out.println("new list:" + newList);
    }

    /**
     * 找出2011年发生的所有交易，并按交易额排序 交易员在哪些不同的城市工作过 查找所有来自剑桥的交易员，并按姓名排序 返回所有交易员的姓名字符串，并按字母顺序排序 有没有交易员在米兰工作的？ 打印生活在剑桥的交易员的所有交易额
     * 所有交易中，最高的交易额是多少 找到交易额最小的交易 ———————————————— 版权声明：本文为CSDN博主「蒙牛真好」的原创文章，遵循 CC 4.0 BY-SA 版权协议，转载请附上原文出处链接及本声明。
     * 原文链接：https://blog.csdn.net/nullbull/article/details/81234250
     */
    private static void test() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
        );

        // Query 1: Find all transactions from year 2011 and sort them by value (small to high).
        // 找出2011年发生的所有交易，并按交易额排序
        List<Transaction> tr2011 = transactions.stream()
            .filter(transaction -> transaction.getYear() == 2011)
            .sorted(comparing(Transaction::getMoney))
            .collect(toList());
        System.out.println(tr2011);

        // Query 2: What are all the unique cities where the traders work?
        // 交易员在哪些不同的城市工作过
        List<String> cities =
            transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(toList());
        System.out.println(cities);

        // Query 3: Find all traders from Cambridge and sort them by name.
        // 查找所有来自剑桥的交易员，并按姓名排序
        List<Trader> traders =
            transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());
        System.out.println(traders);

        // Query 4: Return a string of all traders’ names sorted alphabetically.
        // 返回所有交易员的姓名字符串，并按字母顺序排序
        String traderStr =
            transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2 + ',');
        System.out.println(traderStr);

        // Query 5: Are there any trader based in Milan?
        // 有没有交易员在米兰工作的？
        boolean milanBased =
            transactions.stream()
                .anyMatch(transaction -> transaction.getTrader()
                    .getCity()
                    .equals("Milan")
                );
        System.out.println(milanBased);

        // Query 6: Update all transactions so that the traders from Milan are set to Cambridge.
        // 打印生活在剑桥的交易员的所有交易额
        transactions.stream()
            .map(Transaction::getTrader)
            .filter(trader -> trader.getCity().equals("Milan"))
            .forEach(trader -> trader.setCity("Cambridge"));
        System.out.println(transactions);

        // Query 7: What's the highest value in all the transactions?
        // 所有交易中，最高的交易额是多少
        int highestValue =
            transactions.stream()
                .map(Transaction::getMoney)
                .reduce(0, Integer::max);
        System.out.println(highestValue);
    }

}
