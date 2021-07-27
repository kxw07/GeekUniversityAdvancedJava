package homework.sharding_sphere_10_yaml_version;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleService {

    private SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    public List<User> query() throws SQLException, IOException {
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            userList.addAll(this.simpleDao.query());
        }

        return userList;
    }
}
