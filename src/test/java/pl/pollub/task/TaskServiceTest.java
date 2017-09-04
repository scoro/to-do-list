package pl.pollub.task;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import pl.pollub.task.Builder.UserBuilder;
import pl.pollub.task.Notifier.EmailNotifier;
import pl.pollub.task.task.TaskSummariser;
import pl.pollub.task.task.Task;
import pl.pollub.task.task.TaskService;
import pl.pollub.task.user.User;
import pl.pollub.task.user.UserService;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    @Mock
    private
    UserService userService;

    @Mock
    EmailNotifier emailNotifier;

    @Mock
    TaskSummariser taskSummariser;

    @InjectMocks
    TaskService taskService;

    @Captor
    private ArgumentCaptor<List<String>> emailsCaptor;

    @Test
    public void sendEmailToOwnerAndContributors() {
        //Niestety nie udało mi sie napisać testu ze wzgledu na to że nie miałem czasu
        Task task = taskService.createTaskForUser(1, 2, 3);

        Mockito.when(userService.getUserById(Mockito.anyInt())).then(invocationOnMock -> {
            Integer id = invocationOnMock.getArgumentAt(0, int.class);
            return new UserBuilder(new User(id)).paidForSms(false).withEmail("user" + id + "@wp.pl").build();
        });

        taskService.completeTaskAndSendNotify(task.getId());

        HashSet<String> notified = new HashSet<>(emailsCaptor.getValue());
        HashSet<String> expected = new HashSet<>(Arrays.asList("user1@wp.pl", "user2@wp.pl", "user3@wp.pl"));

        Assert.assertEquals(expected, notified);
    }

}