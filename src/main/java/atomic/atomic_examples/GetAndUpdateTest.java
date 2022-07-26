package atomic.atomic_examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName GetAndUpdateTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 18:21
 * @Version 1.0
 **/
public class GetAndUpdateTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);

        // getAndUpdate 函数式编程，默认参数是 AtomicInteger 的 value 属性
        // 下面这段代码，就是对 AtomicInteger 的 val 进行 10 次 2倍操作
        for (int i = 0; i < 10; i++) {
            atomicInteger.getAndUpdate((val)-> val<<1);
            //等同于 atomicInteger.getAndUpdate((val)-> val*2);
        }

        System.out.println(atomicInteger.get()); // 1024
    }
}
