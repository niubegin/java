package sf.jz;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ30MinStack {

    private Stack<Integer> dataStack = new Stack<>();
    private Stack<Integer> minStack = new Stack<>();

    public static void main(String[] args) {
        JZ30MinStack jz30MinStack = new JZ30MinStack();
        jz30MinStack.push(5);
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.push(3);
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.push(2);
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.push(1);
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.push(4);
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.pop();
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.pop();
        log.info("{}", jz30MinStack.mini());
        jz30MinStack.pop();
        log.info("{}", jz30MinStack.mini());
    }

    private Integer push(Integer data) {
        dataStack.push(data);
        if (minStack.size() == 0 || minStack.peek().intValue() >= data.intValue()) {
            minStack.push(data);
        }
        return data;
    }

    private Integer pop() {
        Integer data = dataStack.pop();
        if (data.intValue() == minStack.peek().intValue()) {
            minStack.pop();
        }
        return data;
    }

    private Integer mini() {
        return minStack.peek();
    }
}
