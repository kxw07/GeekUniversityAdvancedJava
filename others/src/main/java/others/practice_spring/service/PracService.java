package others.practice_spring.service;

import org.springframework.stereotype.Service;

@Service
public class PracService {

    private int number;

    public void add() {
        this.number++;
    }

    public int get() {
        return number;
    }
}
