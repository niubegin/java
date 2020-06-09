package test;

import java.util.HashMap;
import java.util.Map;

public class Basic {
    public static void main(String[] args) {
        Integer zero = Integer.valueOf(1);
        System.out.println(zero == 0);
        Map<Long, Integer> secondWallAllocateResult = new HashMap<>();
        secondWallAllocateResult.put(6774325L, 1);
        secondWallAllocateResult.put(6774326L, 1);
        System.out.println(secondWallAllocateResult);
        Map<Long, Long> relationshipMap = new HashMap<>();
        relationshipMap.put(6774325L, 7088777L);
        relationshipMap.put(6774326L, 7088794L);
        //relationshipMap.put(6774326L,7088777L);
        Map<Long, Integer> subCompletedQty = new HashMap<>();
        secondWallAllocateResult.forEach((k, v) -> {
            Long tmpRelationship = relationshipMap.get(k);
            if (tmpRelationship == null) {
                return;
            }
            //对于一样subId的数据进行合并
            Integer completedCount = 0;
            if (subCompletedQty.containsKey(tmpRelationship)) {
                completedCount = subCompletedQty.get(tmpRelationship) + v;
            } else {
                completedCount = v;
            }
            subCompletedQty.put(tmpRelationship, completedCount);
        });
        System.out.println(secondWallAllocateResult);
        System.out.println(subCompletedQty);
        Integer tmp = subCompletedQty.get(6774326L);
        tmp = 0;
        tmp = secondWallAllocateResult.get(6774326L);
        tmp = Integer.valueOf(0);
        System.out.println(secondWallAllocateResult);
        System.out.println(subCompletedQty);
        for (Map.Entry<Long, Integer> entry : secondWallAllocateResult.entrySet()) {
            System.out.println(entry.getValue()==0);
        }
    }
}
