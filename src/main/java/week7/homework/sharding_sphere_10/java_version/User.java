package week7.homework.sharding_sphere_10.java_version;

import lombok.Data;

@Data
public class User {
    private String user_id;
    private String user_name;
    private String email;
    private String phone;
    private Long last_login;
    private Long create_time;
    private Long update_time;
}
