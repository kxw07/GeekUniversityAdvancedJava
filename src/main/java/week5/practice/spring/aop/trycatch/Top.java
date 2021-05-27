package week5.practice.spring.aop.trycatch;

import java.sql.SQLException;
import java.sql.SQLRecoverableException;

public class Top {
    public static void main(String[] args) {
        SQLRecoverableException sqlRecoverableException = new SQLRecoverableException();
        System.out.println(sqlRecoverableException instanceof SQLException);

        Bottom bottom = new Bottom();
        Middle middle = new Middle(bottom);

        middle.get();
    }
}
