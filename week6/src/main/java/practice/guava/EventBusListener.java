package practice.guava;

import com.google.common.eventbus.Subscribe;

public class EventBusListener {
    @Subscribe
    public void subscribedMessage(String msg) {
        System.out.println("EventBusListener got msg:" + msg);
    }

    @Subscribe
    public void subscribedCustomEvent(CustomEvent customEvent) {
        System.out.println("EventBusListener got CustomEvent:" + customEvent);
    }
}
