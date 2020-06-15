package sf.jz;

import java.util.Objects;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class LinkedListNode {

    private Integer value;
    private LinkedListNode next;

    @Override
    public String toString() {
        return "LinkedListNode{" +
            "value=" + value +
            '}';
    }

    public static void travelPrint(LinkedListNode head) {
        LinkedListNode cur = head;
        if (Objects.isNull(cur)) {
            log.info("empty list");
            return;
        }
        while (Objects.nonNull(cur)) {
            log.info("{}", cur.getValue());
            cur = cur.getNext();
        }
    }
}
