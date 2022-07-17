package lock_and_condition.asynchronous_to_synchronous;

/**
 * @ClassName AbstractService
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/18 01:07
 * @Version 1.0
 **/
public abstract class AsyncService {

    protected Response resp;

    /**
     * 异步执行方法
     * 只会调用实际执行业务
     * @return
     * @throws Exception
     */
    public boolean doService() throws Exception {
        new Thread(()->{
            // 模拟执行过程中的延迟（比如 rpc 的延迟）
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            invoke();
        }).start();
        return true;
    }

    /**
     * 获取执行结果
     * @return
     */
    public Response getResult() {
        return this.resp;
    }

    /**
     * 实际执行业务
     */
    protected void invoke() {
        Response resp = new Response("执行成功！");
        invokeCallBack(resp);
    }

    /**
     * 执行成功回调函数
     */
    abstract void invokeCallBack(Response resp);

    static class Response {
        private String msg;

        public Response(String msg) {
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Response{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }
}
