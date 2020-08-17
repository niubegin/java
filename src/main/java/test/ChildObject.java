package test;

import java.util.List;
import lombok.Data;

@Data
public class ChildObject extends ParentObject {

    private static final long serialVersionUID = 1L;
    private String name;
    private List<DataObject> dataObjectList;

    @Override
    public String toString() {
        return "ChildObject{" +
            "name='" + name + '\'' +
            ", dataObjectList=" + dataObjectList +
            ", toString()=" + super.toString() + '}';
    }
}
