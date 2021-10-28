package hello.advanced.trace;

import java.util.UUID;
import lombok.Getter;

@Getter
public class TraceId {

    private final String id;
    private int level;

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(String id, int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * HTTP 트랜잭션의 깊이를 표현하기 위해 동일한 id로 레벨을 +1 해주는 메소드
     * @return TraceId
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

}
