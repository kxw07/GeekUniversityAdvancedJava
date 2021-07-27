package homework.abstract_routing_datasource_09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import homework.abstract_routing_datasource_09.datasource.DataSource;
import homework.abstract_routing_datasource_09.datasource.DataSourceName;

@Service
public class SimpleService {

    private SimpleDao simpleDao;

    @Autowired
    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    @DataSource(DataSourceName.READ)
    public Order getOrder(String orderId) {
//        DynamicDataSource.setDataSource(DataSourceName.READ);
        return this.simpleDao.getOrder(orderId);
    }

    @DataSource(DataSourceName.WRITE)
    public Order insertOrder() {
//        DynamicDataSource.setDataSource(DataSourceName.WRITE);
        return this.simpleDao.insertOrder();
    }
}
