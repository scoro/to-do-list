package pl.pollub.task;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TaskServiceTest {

    private TaskService taskService;
    @Before
    public void createTaskService(){
        taskService = new TaskServiceImpl(new TaskList());
    }

    @Test
    public void whenICreateNewTaskThenThisTaskIsOnTheTaskList() throws Exception {

        Task created1 = taskService.saveTask(new NewTask("task1"));
        taskService.saveTask(new NewTask("task2"));

        assertEquals(2, taskService.getAllTasks().size());
        assertTrue(taskService.getAllTasks().contains(created1));
    }



    @Test
    public void ICanRemoveExistingTask() {
        //given: a task
        taskService.saveTask(new NewTask("task1"));

        //when: i remove it
        Task task = taskService.getAllTasks().get(0);
        taskService.deleteById(task.getId());
        //then: it diesappears
        assertFalse("Item shouldn't be present on the list", taskService.getAllTasks().contains(task));
    }

    @Test
    public void ICantRemoveNotExistingTask() {
        //given: a task
        taskService.saveTask(new NewTask("task1"));
        taskService.saveTask(new NewTask("task2"));

        Task notOnListTask = new Task(3, "task3");

        //when: i remove it
        Task task = taskService.getAllTasks().get(0);
        taskService.deleteById(task.getId());
        //then: i cant remove it again
        assertFalse("you can remove same task twice", taskService.deleteById(task.getId()));
        assertFalse("you can remove item which is not on the list", taskService.deleteById(notOnListTask.getId()));
    }

}