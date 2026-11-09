package org.ladysnake.satin.api.event;

import java.util.List;
import java.util.function.Function;

public final class EventFactory {
    private EventFactory() {
    }

    public static <T> Event<T> createArrayBacked(Class<T> type, Function<List<T>, T> invokerFactory) {
        return new Event<>(type, invokerFactory);
    }

    public static <T> Event<T> createArrayBacked(Class<T> type, Function<List<T>, T> invokerFactory, T emptyInvoker) {
        return new Event<>(type, listeners -> listeners.isEmpty() ? emptyInvoker : invokerFactory.apply(listeners));
    }
}
