package others.spring_practice.schedules;

import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class TestScheduler {

    private final TaskScheduler taskScheduler;
    private final List<TestJob> testJobList;

    public TestScheduler(TaskScheduler taskScheduler, List<TestJob> testJobList) {
        this.taskScheduler = taskScheduler;
        this.testJobList = testJobList;
    }

    @PostConstruct
    public void init() {
        testJobList.forEach(testJob -> taskScheduler.scheduleAtFixedRate(() -> testJob.doJob(), 5000L));
    }
}
