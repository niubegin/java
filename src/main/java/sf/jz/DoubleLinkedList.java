package sf.jz;

import java.util.Objects;
import lombok.Data;

@Data
public class DoubleLinkedList {

    private int value;
    private DoubleLinkedList next;
    private DoubleLinkedList pre;

    @Override
    public String toString() {
        return "DoubleLinkedList{" +
            "value=" + value +
            ", next=" + (Objects.nonNull(next) ? next.getValue() : "null") +
            ", pre=" + (Objects.nonNull(pre) ? pre.getValue() : "null") +
            '}';
    }
}
