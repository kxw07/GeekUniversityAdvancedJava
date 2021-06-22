package week8.homework.horizontal_division_02;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleService {

    private SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public List<Order> query() {
        return this.simpleDao.query();
    }

    public void insert() throws InterruptedException {
        for (int i = 0; i < 15; i++) {
            Thread.sleep(100);
            this.simpleDao.insert();
        }
    }

    public List<Order> queryByOrderId(long orderId) {
        return this.simpleDao.queryByOrderId(orderId);
    }

    public void delete() {
        this.simpleDao.delete();
    }

    public List<Order> queryByUserId(long userId) {
        return this.simpleDao.queryByUserId(userId);
    }
}
