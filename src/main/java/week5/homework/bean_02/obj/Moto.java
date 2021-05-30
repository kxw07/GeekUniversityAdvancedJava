package week5.homework.bean_02.obj;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Bean;

@Data
@NoArgsConstructor
@ToString
public class Moto {
    private int price;
    private int years;
}
