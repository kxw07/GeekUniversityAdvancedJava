package others.spring_practice.service;

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
