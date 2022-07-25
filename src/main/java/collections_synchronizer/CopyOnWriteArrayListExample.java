package collections_synchronizer;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @ClassName CopyOnWriteArrayListExample
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/25 16:41
 * @Version 1.0
 **/
public class CopyOnWriteArrayListExample {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        /*
        将原数组复制一份
        在复制的那一份上进行元素的增加
        最后再将副本写回原数组
        public boolean add(E e) {
            synchronized(this.lock) {
                Object[] es = this.getArray();
                int len = es.length;
                es = Arrays.copyOf(es, len + 1);
                es[len] = e;
                this.setArray(es);
                return true;
            }
        }
         */
        list.add(1);

        /*
        读不加锁
        但是可能出现读写不一致的问题
        public E get(int index) {
            return elementAt(this.getArray(), index);
        }
         */
        list.get(0);
    }
}
