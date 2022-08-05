package atomic.atomic_reference_examples;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName RangeSetWithAtomicProblem
 * @Description 存在竞态条件的边界设置示例
 * @Author faro_z
 * @Date 2022/7/29 02:34
 * @Version 1.0
 **/
public class RangeSetWithAtomicProblem {
    // 上限
    private AtomicInteger up = new AtomicInteger(0);
    // 下限
    private AtomicInteger down = new AtomicInteger(0);

    /**
     * 检查上下限合不合理
     * @return
     */
    public boolean check() {
        // 发生竞态条件，不能保证这个 if 判断的原子性！
        if (up.get()<=down.get()) return false;
        return true;
    }
}
