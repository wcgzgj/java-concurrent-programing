package semaphore.current_limiter;

/**
 * @ClassName LimiterCaller
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/18 13:40
 * @Version 1.0
 **/
public class LimiterCaller {
    public static void main(String[] args) throws InterruptedException {
        Limiter<Source, String> limiter = new Limiter<>(10, new Source("source_dummy"));
        for (int i = 0; i < 100; i++) {
            int id = i;
            limiter.exec(t-> {
                t.doTimeConsumingJob(1000L, id);
                return id+":"+t.getSourceName();
            });
        }
    }

    static class Source {
        private String sourceName;

        public Source(String sourceName) {
            this.sourceName = sourceName;
        }

        /**
         * 借助资源，执行耗时程序
         */
        public void doTimeConsumingJob(Long time,int id) {
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("id:"+id+" is running！");
        }

        public String getSourceName() {
            return sourceName;
        }
    }
}
