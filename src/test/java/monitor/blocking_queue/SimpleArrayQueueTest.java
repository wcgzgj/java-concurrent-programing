package monitor.blocking_queue;

import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleArrayQueueTest {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        int size = 5;
        SimpleArrayQueue<Integer> queue = new SimpleArrayQueue<Integer>(size);
        String line = null;
        while (!"quit".equals(line=sc.nextLine())) {
            String[] tmp = line.split(" ");
            String op = tmp[0];
            System.out.println("curr op:"+op);
            // push
            if (tmp.length>1) {
                System.out.println("curr val:"+tmp[1]);
                System.out.println(queue.push(Integer.parseInt(tmp[1])));
            } else {
                if ("pop".equals(op)) {
                    System.out.println(queue.pop());
                } else if ("size".equals(op)) {
                    System.out.println(queue.getSize());
                } else if ("peek".equals(op)){
                    System.out.println(queue.peek());
                }
            }
        }
    }

    @Test
    public void testSimpleArrayQueue() throws Exception {
        int size = 5;
        SimpleArrayQueue<Integer> queue = new SimpleArrayQueue<Integer>(size);
        String line = null;
        while (!"quit".equals(line=sc.nextLine())) {
            String[] tmp = line.split(" ");
            String op = tmp[0];
            // push
            if (tmp.length>1) {
                System.out.println(queue.push(Integer.parseInt(tmp[1])));
            } else {
                if ("pop".equals(op)) {
                    System.out.println(queue.pop());
                } else if ("size".equals(op)) {
                    System.out.println(queue.getSize());
                } else {
                    System.out.println(queue.peek());
                }
            }
        }
    }

    @Test
    public void splitTest() {
        String s1 = "haha hehe";
        String s2 = "haha   ";
        System.out.println(Arrays.toString(s1.split(" ")));
        System.out.println(Arrays.toString(s2.split(" ")));
    }
}