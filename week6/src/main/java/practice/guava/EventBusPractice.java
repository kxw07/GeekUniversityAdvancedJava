package practice.guava;

import com.google.common.eventbus.EventBus;

public class EventBusPractice {
    public static void main(String[] args) {
        EventBus eventBus = new EventBus();

        eventBus.register(new EventBusListener());
        System.out.println("published msg");
        eventBus.post("publish test msg");

        System.out.println("published customEvent msg");
        eventBus.post(new CustomEvent("CustomEvent test msg"));
    }
}
