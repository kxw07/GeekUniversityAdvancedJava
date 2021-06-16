package week7.homework.abstract_routing_datasource_09;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import week7.homework.abstract_routing_datasource_09.datasource.ChangeDataSource;
import week7.homework.abstract_routing_datasource_09.datasource.DataSourceName;

@Service
public class SimpleService {

    private SimpleDao simpleDao;

    @Autowired
    public SimpleService(SimpleDao simpleDao) {
        this.simpleDao = simpleDao;
    }

    @ChangeDataSource(DataSourceName.READ)
    public Order getOrder(String orderId) {
//        DynamicDataSource.setDataSource(DataSourceName.READ);
        return this.simpleDao.getOrder(orderId);
    }

    @ChangeDataSource(DataSourceName.WRITE)
    public Order insertOrder() {
//        DynamicDataSource.setDataSource(DataSourceName.WRITE);
        return this.simpleDao.insertOrder();
    }
}
