package pl.pollub.task.task;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import pl.pollub.task.AbstractFactory.AbstractCommsFactory;
import pl.pollub.task.user.User;
import pl.pollub.task.user.UserService;

@RequiredArgsConstructor
public class TaskService {

    private final UserService userService;

    private final Map<Integer, Task> tasks = new HashMap<>();

    private final AtomicInteger counter = new AtomicInteger();

    public Task createTaskForUser(int userId, Integer... contributors){
        Task task2 = new Task(counter.incrementAndGet(), userId,
                             contributors != null ? Arrays.asList(contributors) : Collections.emptyList());
        tasks.put(task2.getId(), task2);
        return task2;
    }

    public void completeTaskAndSendNotify(int taskId){
        Task task = tasks.get(taskId);
        List<Integer> userIds = new ArrayList(task.getContributors());
        userIds.add(task.getUserId());

        task.getContributors().forEach( userId -> {
            User user = userService.getUserById(userId);
            if(user.getHasPaidForSms()) {
                TaskSummariser.getInstance().notify(task, AbstractCommsFactory.createFactory(user).createNotifier());
            }
            else{
                TaskSummariser.getInstance().remind(task, AbstractCommsFactory.createFactory(user).createReminder());
            }
        });

    }

}
