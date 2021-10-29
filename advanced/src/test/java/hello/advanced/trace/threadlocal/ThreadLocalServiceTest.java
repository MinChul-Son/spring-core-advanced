package hello.advanced.trace.threadlocal;

import hello.advanced.trace.threadlocal.code.FieldService;
import hello.advanced.trace.threadlocal.code.ThreadLocalService;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ThreadLocalServiceTest {

    private ThreadLocalService service = new ThreadLocalService();

    @Test
    void field() {
        log.info("main start");

        Runnable userA = () -> {
            service.logic("userA");
        };
        Runnable userB = () -> {
            service.logic("userB");
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");

        threadA.start();
//        sleep(2000);
        sleep(100);
        threadB.start();
        
        sleep(3000); // Thread-B가 완료되기전까지 main Thread가 종료되지 않기 위해
        log.info("main Thread exit");
    }

    @Test
    void fieldUseCountDownLatch() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        Runnable userA = () -> {
            service.logic("userA");
            countDownLatch.countDown();
        };
        Runnable userB = () -> {
            service.logic("userB");
            countDownLatch.countDown();
        };
        Runnable userC = () -> {
            service.logic("userC");
            countDownLatch.countDown();
        };
        Runnable userD = () -> {
            service.logic("userD");
            countDownLatch.countDown();
        };
        Runnable userE = () -> {
            service.logic("userE");
            countDownLatch.countDown();
        };

        Thread threadA = new Thread(userA);
        threadA.setName("thread-1");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-2");
        Thread threadC = new Thread(userC);
        threadA.setName("thread-3");
        Thread threadD = new Thread(userD);
        threadB.setName("thread-4");
        Thread threadE = new Thread(userE);
        threadA.setName("thread-5");

        threadA.start();
        threadB.start();
        threadC.start();
        threadD.start();
        threadE.start();

        countDownLatch.await();
        log.info("main Thread Finished");
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}


