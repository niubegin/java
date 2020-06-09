package sf.jz;

import java.util.LinkedList;
import java.util.Queue;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ09ImplementStackUsingQueue {

    Queue<Integer> inQueue = new LinkedList<>();
    Queue<Integer> outQueue = new LinkedList<>();

    public static void main(String[] args) {
        JZ09ImplementStackUsingQueue jz09ImplementStackUsingQueue = new JZ09ImplementStackUsingQueue();
        jz09ImplementStackUsingQueue.push(Integer.valueOf(1));
        jz09ImplementStackUsingQueue.push(Integer.valueOf(2));
        jz09ImplementStackUsingQueue.push(Integer.valueOf(3));
        log.info("{}", jz09ImplementStackUsingQueue.pop());
        log.info("{}", jz09ImplementStackUsingQueue.pop());
        jz09ImplementStackUsingQueue.push(Integer.valueOf(2));
        log.info("{}", jz09ImplementStackUsingQueue.pop());
    }

    private void push(Integer value) {
        inQueue.offer(value);
    }

    private Integer pop() {
        if (inQueue.isEmpty()) {
            return null;
        } else {
            //inQueue留最后一个元素，其他转outQueue
            while (inQueue.size() > 1) {
                outQueue.offer(inQueue.poll());
            }
        }

        Integer value = inQueue.poll();
        //交换队列
        Queue<Integer> tmp = inQueue;
        inQueue = outQueue;
        outQueue = tmp;
        return value;
    }
}
