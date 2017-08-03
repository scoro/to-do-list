package pl.pollub.model.task;

import org.junit.Before;
import org.mockito.Mockito;
import pl.pollub.repository.TaskRepository;
import pl.pollub.service.TaskService;
import pl.pollub.service.TaskServiceImpl;

public class TaskServiceTest {

    private TaskService taskService;
    private TaskRepository taskRepositoryMock;

    @Before
    public void setUp() {
        taskRepositoryMock = Mockito.mock(TaskRepository.class);
       // taskService = new TaskServiceImpl(taskRepositoryMock);
    }

}
