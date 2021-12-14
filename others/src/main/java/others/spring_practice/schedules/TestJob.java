package others.spring_practice.schedules;

import lombok.Setter;

@Setter
public abstract class TestJob implements TestJobInterface {
    private String name;

    public TestJob(String name) {
        this.name = name;
    }

    @Override
    public void doJob() {
        System.out.println("Say my name:" + this.name);
    }
}
