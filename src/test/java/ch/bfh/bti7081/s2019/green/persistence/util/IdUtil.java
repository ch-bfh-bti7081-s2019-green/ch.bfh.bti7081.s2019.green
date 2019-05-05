package ch.bfh.bti7081.s2019.green.persistence.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class IdUtil {
    private static final Map<Class<?>, AtomicLong> idsByClass = new ConcurrentHashMap<>();

    public static Long next(Class<?> clazz){
        return idsByClass.computeIfAbsent(clazz, c -> new AtomicLong()).incrementAndGet();
    }
}
