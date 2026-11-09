package ladysnake.satin.api.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public final class Event<T> {
    private final List<T> listeners = new CopyOnWriteArrayList<>();
    private final T invoker;

    Event(Class<T> type, Function<List<T>, T> invokerFactory) {
        this.invoker = invokerFactory.apply(this.listeners);
    }

    public void register(T listener) {
        this.listeners.add(listener);
    }

    public void unregister(T listener) {
        this.listeners.remove(listener);
    }

    public boolean isRegistered(T listener) {
        return this.listeners.contains(listener);
    }

    public T invoker() {
        return this.invoker;
    }

    public List<T> listeners() {
        return this.listeners;
    }
}
