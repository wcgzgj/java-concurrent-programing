package cdl_and_cb;

import cdl_and_cb.data.DataUtil;
import cdl_and_cb.data.Diff;
import cdl_and_cb.data.OrderDB;

import java.util.Map;

/**
 * @ClassName CdlTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 18:12
 * @Version 1.0
 **/
public class CdlTest {
    public OrderDB db = OrderDB.getInstance();

    public static void main(String[] args) throws Exception {
        CdlTest test = new CdlTest();
        test.db.clearDB();
        DataUtil.insertRandomDiffData(test.db,100);
        CdlExample example = new CdlExample();
        example.checkAndSaveDiff();
        Map<Long, Diff> diffMap = test.db.getDiffMap();
        for (Long key : diffMap.keySet()) {
            System.out.println(diffMap.get(key));
        }
    }
}
