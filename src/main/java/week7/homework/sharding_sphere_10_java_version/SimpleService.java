package week7.homework.sharding_sphere_10_java_version;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleService {

    private SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public List<User> query() {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.addAll(this.simpleDao.query());
        }

        return userList;
    }
}
