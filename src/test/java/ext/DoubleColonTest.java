package ext;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DoubleColonTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/8/7 17:28
 * @Version 1.0
 **/
public class DoubleColonTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i+1);
        }
        list.stream().forEach(System.out::println);
        System.out.println("---");
        list.stream().forEach((elem)->{
            System.out.println(elem);
        });
    }
}
