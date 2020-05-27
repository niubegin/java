package sf.jz;

import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ09ImplementQueueUsingStack {

    private Stack<Integer> inStack = new Stack<>();
    private Stack<Integer> tmpStack = new Stack<>();

    public static void main(String[] args) {
        JZ09ImplementQueueUsingStack jz09ImplementQueueUsingStack = new JZ09ImplementQueueUsingStack();
        jz09ImplementQueueUsingStack.appendTail(Integer.valueOf(1));
        jz09ImplementQueueUsingStack.appendTail(Integer.valueOf(2));
        jz09ImplementQueueUsingStack.appendTail(Integer.valueOf(3));
        log.info("{}", jz09ImplementQueueUsingStack.inStack);
        log.info("{}", jz09ImplementQueueUsingStack.deleteHead());
        log.info("{}", jz09ImplementQueueUsingStack.inStack);
    }

    private void appendTail(Integer value) {
        inStack.push(value);
    }

    private Integer deleteHead() {
        while (!inStack.isEmpty()) {
            tmpStack.push(inStack.pop());
        }

        Integer head = tmpStack.pop();
        while (!tmpStack.isEmpty()) {
            inStack.push(tmpStack.pop());
        }

        return head;
    }
}
