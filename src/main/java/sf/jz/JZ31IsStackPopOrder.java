package sf.jz;

import java.util.Objects;
import java.util.Stack;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JZ31IsStackPopOrder {

    public static void main(String[] args) {
        int[] pushOrder = {1, 2, 3, 4, 5};
        int[] popOrder = {4, 5, 3, 2, 1};
        //log.info("{}", isPopOrder(pushOrder, popOrder));
        int[] popOrder2 = {4, 3, 5, 1, 2};
        log.info("{}", isPopOrder(pushOrder, popOrder2));
    }

    /**
     * 出栈序列是否是入栈序列的弹出
     */
    private static boolean isPopOrder(int[] pushOrder, int[] popOrder) {
        if (Objects.isNull(pushOrder) || Objects.isNull(popOrder)) {
            return false;
        }
        if (pushOrder.length != popOrder.length) {
            return false;
        }
        Stack<Integer> data = new Stack<Integer>();
        int pushIndex = 0;
        for (int popItem : popOrder) {
            //如果当前栈顶等于出栈元素，则出栈
            if (data.size() > 0 && data.peek().intValue() == popItem) {
                data.pop();
            } else {
                boolean find = false;
                //在未入栈的数组中查找，如果不等于就入栈，等于就结束查找
                while (!find && pushIndex < pushOrder.length) {
                    //如果不相等就入栈
                    if (pushOrder[pushIndex] != popItem) {
                        data.push(pushOrder[pushIndex]);
                    } else {
                        find = true;
                    }
                    pushIndex++;
                }
                //没找到就返回失败
                if (!find) {
                    return false;
                }
            }
        }
        return true;
    }
}
