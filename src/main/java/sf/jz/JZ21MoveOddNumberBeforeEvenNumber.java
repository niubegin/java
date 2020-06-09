package sf.jz;

import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JZ21MoveOddNumberBeforeEvenNumber {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 5, 1, 6, 7, 8, 9};
        move(numbers);
        log.info("{}", numbers);
        int[] numbers2 = {2, 4, 6, 8, 5, 7, 11, 9};
        move(numbers2);
        log.info("{}", numbers2);
    }

    /**
     * 参考快速排序的遍历分组算法
     *
     * @param numbers
     */
    private static void move(int[] numbers) {
        if (Objects.isNull(numbers) || numbers.length == 0) {
            return;
        }
        //定义两个指针，分别指向数组里面当前遍历到的最后一个奇数和偶数
        int oddIndex = 0;
        int evenIndex = 0;
        int tmp;
        while (evenIndex < numbers.length) {
            //如果偶数指针当前是奇数，则交换
            if ((numbers[evenIndex] & 1) == 1) {//&& oddIndex != evenIndex) {
                //找到奇数指针指向的下一个偶数，进行交换
                while (oddIndex < numbers.length) {
                    //找到了偶数
                    if ((numbers[oddIndex] & 1) == 0) {
                        break;
                    }
                    oddIndex++;
                }
                tmp = numbers[evenIndex];
                numbers[evenIndex] = numbers[oddIndex];
                numbers[oddIndex] = tmp;
            }
            evenIndex++;
        }
    }
}
