package sf.jz;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ41GetMedianNumberInStream {

    public static void main(String[] args) {
        offer(2);
        offer(2);
        offer(3);
        offer(3);
        offer(3);
        offer(4);
        log.info("{}", get());
    }

    /**
     * 数据流中的中位数
     */
    private static int get() {
        int minus = maxHeap.size() - minHeap.size();
        if (minus == 0) {
            return (maxHeap.peek() + minHeap.peek()) >> 1;
        } else if (minus == 1) {
            return maxHeap.peek();
        } else if (minus == -1) {
            return minHeap.peek();
        }

        return 0;
    }

    //大根堆存放小数部分
    private static Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    //小根堆存放大数部分
    private static Queue<Integer> minHeap = new PriorityQueue<>();

    /**
     * 插入元素，大根堆和小根堆元素相差不大于1
     */
    private static void offer(int num) {
        if (maxHeap.size() >= minHeap.size()) {
            if (maxHeap.size() == 0 || num > maxHeap.peek()) {
                minHeap.offer(num);
            } else {
                maxHeap.offer(num);
                int tmp = maxHeap.poll();
                minHeap.offer(tmp);
            }
        } else {
            if (maxHeap.size() == 0 || num < maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
                int tmp = minHeap.poll();
                maxHeap.offer(tmp);
            }
        }
    }
}
