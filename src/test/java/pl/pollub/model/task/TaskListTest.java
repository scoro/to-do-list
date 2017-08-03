package pl.pollub.model.task;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.model.task.DTO.TaskDTO;

@RestController
@RequestMapping("/api")
public class TaskListTest {

    @Test
    public void whenICreateNewTaskThenThisTaskIsOnTheTaskList() throws Exception {
        TaskList taskList = new TaskList();
        Task created1 = taskList.add(new TaskDTO("task1"));
        taskList.add(new TaskDTO( "task2"));

        assertEquals(2, taskList.getAllTasks().size());
        assertTrue(taskList.getAllTasks().contains(created1));
    }


    public void ICanRemoveExistingTask(){
        //given: a task
        TaskList taskList = new TaskList();
        taskList.add(new TaskDTO( "task1"));

        //when: i remove it
        Task task = taskList.getAllTasks().get(0);

        //then: it diesappears
    }

}