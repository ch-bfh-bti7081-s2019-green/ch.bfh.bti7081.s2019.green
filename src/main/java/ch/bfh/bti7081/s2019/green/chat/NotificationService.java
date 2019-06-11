package ch.bfh.bti7081.s2019.green.chat;

import ch.bfh.bti7081.s2019.green.model.chat.Channel;
import ch.bfh.bti7081.s2019.green.model.chat.Message;
import com.vaadin.flow.shared.Registration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class NotificationService {
    private static Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private static Executor executor = Executors.newSingleThreadExecutor();
    private static Map<Channel, LinkedList<Consumer<Message>>> listeners = new HashMap<>();

    public static synchronized Registration register(Channel channel, Consumer<Message> listener) {
        listeners.computeIfAbsent(channel, unUsed -> new LinkedList<>()).add(listener);

        return () -> {
            synchronized (NotificationService.class) {
                if(listeners.containsKey(channel)){
                    listeners.get(channel).remove(listener);
                } else {
                  LOGGER.warn("Trying to de-register a listener that wasn't registered (channelId={})", channel.getId());
                }
            }
        };
    }

    public static synchronized void notify(Message msg) {
        for (Consumer<Message> listener : listeners.getOrDefault(msg.getChannel(), new LinkedList<>())) {
            executor.execute(() -> listener.accept(msg));
        }
    }
}
