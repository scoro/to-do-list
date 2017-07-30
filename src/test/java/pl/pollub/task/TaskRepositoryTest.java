package pl.pollub.task;

import static org.junit.Assert.*;

import org.junit.Test;
import pl.pollub.task.web.model.NewTask;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.data.repository.TaskRepository;

public class TaskRepositoryTest {

    @Test
    public void whenICreateNewTaskThenThisTaskIsOnTheTaskList() throws Exception {
        TaskRepository taskRepository = new TaskRepository(coWorkerRepository);
        Task created1 = taskRepository.add(new NewTask("task1"));
        taskRepository.add(new NewTask( "task2"));

        assertEquals(2, taskRepository.getAllTasks().size());
        assertTrue(taskRepository.getAllTasks().contains(created1));
    }


    public void ICanRemoveExistingTask(){
        //given: a task
        TaskRepository taskRepository = new TaskRepository(coWorkerRepository);
        taskRepository.add(new NewTask( "task1"));

        //when: i remove it
        Task task = taskRepository.getAllTasks().get(0);

        //then: it diesappears
    }

}