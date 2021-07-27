package homework.jdbc_10;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HikariApplication {

    public static void main(String[] args) throws SQLException {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        hikariDataSource.setUsername("postgres");
        hikariDataSource.setPassword("aaaa1234");
        hikariDataSource.setMaximumPoolSize(5);

        JdbcTemplate jdbcTemplate = new JdbcTemplate(hikariDataSource);
        try {
            String insertData = "INSERT INTO TEST_TABLE(NAME, PHONE) VALUES(?, ?);";
            List<HashMap<String, String>> paramMaps = new ArrayList<>();
            paramMaps.add(new HashMap<String, String>() {{
                              put("name", "John");
                              put("phone", "88888888");
                          }}
            );

            paramMaps.add(new HashMap<String, String>() {{
                              put("name", "Leo");
                              put("phone", "77777777");
                          }}
            );

            paramMaps.add(new HashMap<String, String>() {{
                              put("name", "Linda");
                              put("phone", "66666666");
                          }}
            );

            jdbcTemplate.batchUpdate(insertData, new BatchPreparedStatementSetter() {

                @Override
                public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                    HashMap<String, String> m = paramMaps.get(i);
                    preparedStatement.setString(1, m.get("name"));
                    preparedStatement.setString(2, m.get("phone"));
                }

                @Override
                public int getBatchSize() {
                    return paramMaps.size();
                }
            });

            String readData = "SELECT NAME, PHONE FROM TEST_TABLE;";
            List<Map<String, Object>> resultList = jdbcTemplate.queryForList(readData);
            resultList.stream().forEach(idx -> {
                System.out.println(idx);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
