package cdl_and_cb.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName OrderDB
 * @Description 模拟一个数据库
 * @Author faro_z
 * @Date 2022/7/20 16:36
 * @Version 1.0
 **/
public class OrderDB {
    private final Map<Long,DeliveryOrder> dOrderMap = new HashMap<>();
    private final Map<Long,PreOrder> pOrderMap = new HashMap<>();
    private final Map<Long,Diff> diffMap = new HashMap<>();

    private static volatile OrderDB instance;

    private OrderDB() {}

    public static OrderDB getInstance() {
        if (instance==null) {
            synchronized (OrderDB.class) {
                if (instance==null) {
                    instance = new OrderDB();
                }
            }
        }
        return instance;
    }

    public boolean putDOrder(DeliveryOrder order) {
        dOrderMap.put(order.getUserId(),order);
        return true;
    }

    public boolean putPOrder(PreOrder order) {
        pOrderMap.put(order.getUserId(),order);
        return true;
    }

    public DeliveryOrder getDOrderByUserId(Long userId) {
        // 模拟IO延迟
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return dOrderMap.get(userId);
    }

    public PreOrder getPOrderByUserId(Long userId) {
        // 模拟IO延迟
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return pOrderMap.get(userId);
    }

    public Diff diff(PreOrder preOrder,DeliveryOrder deliveryOrder) throws Exception {
        if (preOrder==null || deliveryOrder==null) {
            return null;
        }
        if (!preOrder.getUserId().equals(deliveryOrder.getUserId())) {
            throw new Exception("比对的用户信息不匹配！");
        }
        Diff diff = new Diff(preOrder.getUserId());
        if (!preOrder.getAddress().equals(deliveryOrder.getAddress())) {
            diff.setAddress(preOrder.getAddress()+"<->"+deliveryOrder.getAddress());
        }
        if (!preOrder.getUserName().equals(deliveryOrder.getUserName())) {
            diff.setUserName(preOrder.getUserName()+"<->"+deliveryOrder.getUserName());
        }
        if (!preOrder.getPrice().equals(deliveryOrder.getPrice())) {
            diff.setPrice(String.valueOf(preOrder.getPrice())+"<->"+deliveryOrder.getPrice());
        }
        return diff;
    }

    public boolean saveDiff(Diff diff) {
        diffMap.put(diff.getUserId(),diff);
        return true;
    }

    /**
     * 获取存在异常的订单的用户 id
     * @return
     */
    public List<Long> getDiffUserIdList() {
        ArrayList<Long> list = new ArrayList<>();
        for (Long userId : pOrderMap.keySet()) {
            Diff diff=null;
            try {
                 diff = diff(pOrderMap.get(userId), dOrderMap.get(userId));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (diff!=null && diff.hasDiff()) {
                list.add(userId);
            }
        }
        return list;
    }

    public Map<Long, Diff> getDiffMap() {
        return diffMap;
    }

    public boolean clearDB() {
        pOrderMap.clear();
        dOrderMap.clear();
        diffMap.clear();
        return true;
    }
}
