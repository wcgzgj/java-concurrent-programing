package atomic.atomic_examples;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName GetAndAccumulateTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 18:33
 * @Version 1.0
 **/
public class GetAndAccumulateTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(1);
        for (int i = 0; i < 5; i++) {
            //atomicInteger.getAndAccumulate(5,(val,core)-> val*core);
            atomicInteger.getAndUpdate((val)->val*5);
            System.out.println(atomicInteger.get());
        }
    }
}
