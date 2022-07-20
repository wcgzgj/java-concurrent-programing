package ext;

import org.junit.Test;

/**
 * @ClassName LongTest
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 16:38
 * @Version 1.0
 **/
public class LongTest {
    @Test
    public void equalsTest() {
        Integer i1 = new Integer(1);
        Integer i2 = new Integer(1);
        Integer i3 = new Integer(200);
        Integer i4 = new Integer(200);
        System.out.println(i1==i2);
        System.out.println(i1.equals(i2));
        System.out.println(i3.equals(i4));
    }
}
