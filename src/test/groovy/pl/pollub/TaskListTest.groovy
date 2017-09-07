package pl.pollub

import pl.pollub.task.NewTask
import pl.pollub.task.Task
import pl.pollub.task.TaskList
import spock.lang.Specification

class TaskListTest extends Specification {

    def "adds"(){
        given: "a task list"
        TaskList taskList = new TaskList();

        when: "two tasks are added to that list"
        taskList.add(new NewTask( "task2"));
        Task created1 = taskList.add(new NewTask("task1"));

        then: "there are two task on the list"
        taskList.getAllTasks().size() == 2

        and: "the list contains an added task"
        taskList.getAllTasks().contains(created1) == false
    }

}
