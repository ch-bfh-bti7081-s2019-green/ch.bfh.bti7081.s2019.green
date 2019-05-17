package ch.bfh.bti7081.s2019.green.persistence.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IdUtil {
    private static final Map<Class<?>, AtomicInteger> idsByClass = new ConcurrentHashMap<>();

    public static Integer next(Class<?> clazz){
        return idsByClass.computeIfAbsent(clazz, c -> new AtomicInteger()).incrementAndGet();
    }
}
