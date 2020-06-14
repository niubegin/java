package sf.jz;

import lombok.Data;

@Data
public class LinkedListNode {

    private Integer value;
    private LinkedListNode next;

    @Override
    public String toString() {
        return "LinkedListNode{" +
            "value=" + value +
            '}';
    }
}
