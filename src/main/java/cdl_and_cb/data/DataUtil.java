package cdl_and_cb.data;


import java.util.Random;


/**
 * @ClassName DataUtil
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/20 17:50
 * @Version 1.0
 **/
public class DataUtil {

    private static Random r = new Random();

    private static String[] ns;
    private static String[] vs;

    static {
        String vTmp = "大方，年轻，聪明，雪白，漂亮，笔直，固定，平等，优秀，慌张，俗气，马虎，" +
                "博学，主观，明快，高兴，幸福，清楚，明确，结实，具体，伟大，勇敢，坚强，温柔，平淡，简单，固执，醒目，" +
                "净净，傲慢，倔强，脆弱，乐观，爽朗，豪放，开朗，爱笑，娇柔，友好，活泼，昂贵，孤独，好动，愉快，热情，可亲，" +
                "健谈，轻松，机敏，外向，兴奋，强烈，率直，语言，行动，善良，文雅，整洁，内向，沉静，稳重，顺从，温和，老实，" +
                "沉著，和平，体贴，忠诚，知足，果断，首领，喜爱，善变，细节，保守，忠心，调解，自信，独立，不凡，悠然，从容，迷人，" +
                "淡定，海涵，洋气，高雅，风度，随和，王者，潇酒，宽容，迷茫，困惑，乏困，疲倦";
        vs = vTmp.split("，");

        String nTmp = "黑枣、橘子、金桔、兰撒、李子、李属、来檬、榴莲、绿橙、荔枝、莲雾、蓝莓"+
                "槟榔、芭蕉、荸荠、菠萝、刺梨、草莓、酢橘、鳄梨、鹅莓、丰脐、复果、番茄、枸杞、柑桔、柑橘、桂圆、橄榄、甘蔗、拐枣、黑梅、黄皮";
        ns = nTmp.split("、");
    }

    public static void insertRandomDiffData(OrderDB db,int n) {
        for (int i = 0; i < n; i++) {
            //this.userId = userId;
            //this.userName = userName;
            //this.address = address;
            //this.price = price;
            Long userId = Long.valueOf(i);
            String address = ns[r.nextInt(ns.length)] + "的地址---";
            String pName = createName();
            String dName = createName();
            Double price = Double.valueOf(r.nextInt(10000));
            Double pPrice = price;
            Double dPrice = price;
            if (r.nextInt(100)<10) {
                dName = createName();
                dPrice = Double.valueOf(r.nextInt(10000));
            }
            PreOrder preOrder = new PreOrder(userId, pName, address, pPrice);
            DeliveryOrder deliveryOrder = new DeliveryOrder(userId, dName, address, dPrice);
            db.putPOrder(preOrder);
            db.putDOrder(deliveryOrder);
            //System.out.println(preOrder);
            //System.out.println(deliveryOrder);
        }
    }

    private static String createName() {
        int vIndex = r.nextInt(vs.length);
        int nIndex = r.nextInt(ns.length);
        return vs[vIndex]+"的"+ns[nIndex];
    }
}
