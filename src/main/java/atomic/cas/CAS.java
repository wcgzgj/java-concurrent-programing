package atomic.cas;

/**
 * @ClassName CAS
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 15:36
 * @Version 1.0
 **/
public class CAS {
    private int count = 0;
    public synchronized int cas(int expect,int newVal) {
        int currVal = this.count;
        // 如果原内存中的值与期望值相符，修改内存中的值
        if (currVal==expect) {
            count = newVal;
        }
        // 返回写入前的值
        // 如果更新失败，可以借助新的内存值在此执行修改
        return currVal;
    }
}
