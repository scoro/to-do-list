package pl.pollub.task;

import static org.junit.Assert.*;

import org.junit.Test;

public class TaskListTest {

    @Test
    public void whenICreateNewTaskThenThisTaskIsOnTheTaskList() throws Exception {
        TaskList taskList = new TaskList();
        Task created1 = taskList.add(new NewTask("task1"));
        taskList.add(new NewTask( "task2"));

        assertEquals(2, taskList.getAllTasks().size());
        assertTrue(taskList.getAllTasks().contains(created1));
    }


    @Test
    public void ICanRemoveExistingTask(){
        //given: a task
        TaskList taskList = new TaskList();
        taskList.add(new NewTask( "task1"));

        //when: i remove it
        Task task = taskList.getAllTasks().get(0);
        taskList.deleteById(task.getId());
        //then: it diesappears
        assertFalse("Item shouldn't be present on the list",taskList.getAllTasks().contains(task));
    }
    @Test
    public void ICantRemoveNotExistingTask(){
        //given: a task
        TaskList taskList = new TaskList();
        taskList.add(new NewTask( "task1"));
        taskList.add(new NewTask("task2"));

        Task notOnListTask = new Task(3,"task3");

        //when: i remove it
        Task task = taskList.getAllTasks().get(0);
        taskList.deleteById(task.getId());
        //then: i cant remove it again
        assertFalse("you can remove same task twice",taskList.deleteById(task.getId()));
        assertFalse("you can remove item which is not on the list",taskList.deleteById(notOnListTask.getId()));
    }

}