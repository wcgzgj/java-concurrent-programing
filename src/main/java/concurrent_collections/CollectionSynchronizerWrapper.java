package concurrent_collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName CollectionSynchronizerWrapper
 * @Description Collections 工具类可以将非并发安全的集合，转换为并发安全的集合
 * @Author faro_z
 * @Date 2022/7/25 16:16
 * @Version 1.0
 **/
public class CollectionSynchronizerWrapper {
    public static void main(String[] args) {
        List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<Integer>());
    }
}
