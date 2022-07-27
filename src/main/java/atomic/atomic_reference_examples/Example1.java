package atomic.atomic_reference_examples;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @ClassName Example1
 * @Description TODO
 * @Author faro_z
 * @Date 2022/7/26 18:43
 * @Version 1.0
 **/
public class Example1 {
    public static void main(String[] args) {
        User jojo = new User("JOJO");
        User dio = new User("DIO");
        User newVal;
        AtomicReference<User> userAtomicReference = new AtomicReference<>(jojo);
        do {
            newVal = dio;
        } while (!userAtomicReference.compareAndSet(jojo,newVal));
        System.out.println(userAtomicReference.get());
    }

    static class User {
        private String name;

        public User(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}
