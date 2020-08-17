package test;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToStringTest {

    /**
     * Data注解会带有toString，子类要输出父类要重写toString方法
     * @param args
     */
    public static void main(String[] args) {
        ChildObject childObject = new ChildObject();
        childObject.setAge(10);
        childObject.setName("zs");
        List<DataObject> dataObjectList = new ArrayList<>();
        DataObject dataObject = new DataObject();
        dataObject.setContainerCode("a");
        dataObject.setCompleteCount(2);
        dataObjectList.add(dataObject);
        childObject.setDataObjectList(dataObjectList);
        log.info("{}", childObject);
    }
}
