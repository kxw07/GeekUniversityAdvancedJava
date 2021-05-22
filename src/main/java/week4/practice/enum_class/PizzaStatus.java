package week4.practice.enum_class;


public enum PizzaStatus {
    ORDERED(5) {
        @Override
        public boolean isOrdered() {
            return true;
        }
    },
    READY(2) {
        @Override
        public boolean isReady() {
            return true;
        }
    },
    DELIVERED(0) {
        @Override
        public boolean isDelivered() {
            return true;
        }
    };

    private final int timeToDelivery;

    public boolean isOrdered() {
        return false;
    }

    public boolean isReady() {
        return false;
    }

    public boolean isDelivered() {
        return false;
    }

    public int getTimeToDelivery() {
        return timeToDelivery;
    }

    PizzaStatus(int timeToDelivery) {
        this.timeToDelivery = timeToDelivery;
    }
}