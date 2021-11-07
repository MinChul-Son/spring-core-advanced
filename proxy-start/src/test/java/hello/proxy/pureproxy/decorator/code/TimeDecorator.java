package hello.proxy.pureproxy.decorator.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeDecorator implements Component {

    private Component component;

    public TimeDecorator(Component component) {
        this.component = component;
    }

    @Override
    public String operation() {
        long startMs = System.currentTimeMillis();
        log.info("TimeDecorator 실행!");
        sleep(1000);
        String result = component.operation();
        long finMs = System.currentTimeMillis();
        log.info("TimeDecorator 종료! 실행 시간={}", finMs - startMs);
        return result;
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
