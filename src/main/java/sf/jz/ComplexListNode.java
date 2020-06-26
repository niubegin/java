package sf.jz;

import java.util.Objects;
import lombok.Data;

@Data
public class ComplexListNode {

    private int value;
    private ComplexListNode next;
    private ComplexListNode sibling;

    @Override
    public String toString() {
        return "ComplexListNode{" +
            "value=" + value +
            ", next=" + next +
            ", sibling=" + (Objects.nonNull(sibling) ? sibling.getValue() : "null") +
            '}';
    }
}
