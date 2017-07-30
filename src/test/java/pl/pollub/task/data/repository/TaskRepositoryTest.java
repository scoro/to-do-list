package pl.pollub.task.data.repository;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.coWorker.data.model.CoWorker;
import pl.pollub.coWorker.data.repository.CoWorkerRepository;
import pl.pollub.coWorker.web.model.NewCoWorker;
import pl.pollub.exceptions.ObjectNotFoundException;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.web.model.NewTask;
import pl.pollub.task.web.model.UpdateTask;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CoWorkerRepository coWorkerRepository;

    @Test
    public void addTasksAndCheckIfCoWorkerHaveIt() throws Exception {
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        Set<Integer> adrianIdSet=new HashSet<>();
        adrianIdSet.add(Adrian.getId());
        Task task1=taskRepository.add(new NewTask("Zostac wielkim programista", adrianIdSet));
        Task task2=taskRepository.add(new NewTask("Zdobyc wladze nad swiatem", adrianIdSet));
        Task task3=taskRepository.add(new NewTask("Zjesc ciastko", adrianIdSet));
        assertEquals(3, taskRepository.getAllTaskOfCoWorker(Adrian.getId()).size());
        assertTrue("Adrian has added task", taskRepository.getAllTaskOfCoWorker(Adrian.getId()).containsAll(new HashSet<>(Arrays.asList(task1,task2,task3))));
    }

    @Test
    public void addTaskAndCheckIfCoWorkersHaveThisTask() throws Exception {
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        CoWorker Bartek=coWorkerRepository.add(new NewCoWorker("Bartek"));
        Set<CoWorker> coWorkersForTask = new HashSet<>(Arrays.asList(Adrian,Bartek));
        Task task=taskRepository.add(new NewTask("Zostac wielkim programista",
                coWorkersForTask.stream().map(CoWorker::getId).collect(Collectors.toSet())));
        assertEquals(1, coWorkerRepository.getCoWorker(Adrian.getId()).getTasks().size());
        assertTrue("Adrian has added task", coWorkerRepository.getCoWorker(Adrian.getId()).getTasks().contains(task));
        assertTrue("Bartek has added task", coWorkerRepository.getCoWorker(Bartek.getId()).getTasks().contains(task));
    }

    @Test
    public void getTaskAndCheckIfAddedCoWorkersExistInIt() throws Exception {
        Task task=taskRepository.add(new NewTask("Zostac wielkim programista"));
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        CoWorker Bartek=coWorkerRepository.add(new NewCoWorker("Bartek"));
        taskRepository.addCoWorkersToTask(task.getId(),new HashSet<>(Arrays.asList(Adrian.getId(),Bartek.getId())));
        Set<Task> tasks=taskRepository.getAllTasks();
        assertEquals(1, tasks.size());
        assertEquals(2, tasks.stream().findFirst().get().getCoWorkers().size());
        assertTrue("Adrian has added task", tasks.stream().findFirst().get().getCoWorkers().contains(Adrian));
        assertTrue("Bartek has added task", tasks.stream().findFirst().get().getCoWorkers().contains(Bartek));
    }

    @Test(expected = ObjectNotFoundException.class)
    public void tryToGetTaskWhichNotExistAndCheckIfThrowException() throws Exception {
        taskRepository.getTask(1);
    }

    @Test(expected = ObjectNotFoundException.class)
    public void trySwitchDoneTaskWhichNotExistAndCheckIfThrowException() throws Exception {
        taskRepository.switchTaskDone(1);
    }

    @Test
    public void deleteTaskAndCheckIfCoWorkerHaveIt() throws Exception {
        Task task1=taskRepository.add(new NewTask("Zostac wielkim programista"));
        Task task2=taskRepository.add(new NewTask("Zjesc sniadanie"));
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        taskRepository.addCoWorkerToTask(Adrian.getId(),task1.getId());
        taskRepository.addCoWorkerToTask(Adrian.getId(),task2.getId());
        taskRepository.delete(task1.getId());
        assertEquals(1,taskRepository.getAllTasks().size());
        assertTrue("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task2));
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task1));
    }

    @Test
    public void deleteTasksAndCheckIfCoWorkerHaveThey() throws Exception {
        Task task1=taskRepository.add(new NewTask("Zostac wielkim programista"));
        Task task2=taskRepository.add(new NewTask("Zjesc sniadanie"));
        Task task3=taskRepository.add(new NewTask("Skonczyc studia"));
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        coWorkerRepository.addCoWorkersToTask(Adrian.getId(),new HashSet<>(Arrays.asList(task1.getId(),task2.getId(),task3.getId())));
        taskRepository.delete(new HashSet<>(Arrays.asList(task1.getId(),task2.getId())));
        assertEquals(1,taskRepository.getAllTasks().size());
        assertEquals(1,taskRepository.getAllTaskOfCoWorker(Adrian.getId()).size());
        assertTrue("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task3));
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task1));
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task2));
    }

    @Test
    public void deleteAllTaskAndCheckIfCoWorkerHaveThey() throws Exception {
        Task task1=taskRepository.add(new NewTask("Zostac wielkim programista"));
        Task task2=taskRepository.add(new NewTask("Zjesc sniadanie"));
        Task task3=taskRepository.add(new NewTask("Skonczyc studia"));
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        coWorkerRepository.addCoWorkersToTask(Adrian.getId(),new HashSet<>(Arrays.asList(task1.getId(),task2.getId(),task3.getId())));
        taskRepository.deleteAll();
        assertEquals(0,taskRepository.getAllTasks().size());
        assertEquals(0,taskRepository.getAllTaskOfCoWorker(Adrian.getId()).size());
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task3));
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task1));
        assertFalse("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task2));
    }

    @Test
    public void updateTaskAndCheckIfCoWorkerSeeChanges() throws Exception {
        Task task1=taskRepository.add(new NewTask("Zostac wielkim programista"));
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian",task1.getId()));
        Task task1Updated=taskRepository.update(new UpdateTask(task1.getId(),"Jednak chce zostac astronauta"));
        assertEquals(1,taskRepository.getAllTaskOfCoWorker(Adrian.getId()).size());
        System.out.println(taskRepository.getAllTaskOfCoWorker(Adrian.getId())
                .stream().findFirst().get());
        System.out.println(task1Updated);
        assertTrue("bla bla bla",taskRepository.getAllTaskOfCoWorker(Adrian.getId()).contains(task1Updated));
        assertTrue("Jednak chce zostac astronauta",taskRepository.getAllTaskOfCoWorker(Adrian.getId())
                .stream().findFirst().get().equals(task1Updated));
        assertEquals("Jednak chce zostac astronauta",taskRepository.getAllTaskOfCoWorker(Adrian.getId())
                .stream().findFirst().get().getContent());
    }

    @After public void clearData(){
        coWorkerRepository.deleteAll();
        taskRepository.deleteAll();
    }

/*

    @Test
    public void update1() throws Exception {
    }

    @Test
    public void switchTaskDone() throws Exception {
    }

    @Test
    public void switchTaskDone1() throws Exception {
    }

    @Test
    public void addCoWorkerToTask() throws Exception {
    }

    @Test
    public void addCoWorkersToTask() throws Exception {
    }

    @Test
    public void deleteCoWorkerFromTask() throws Exception {
    }

    @Test
    public void deleteCoWorkersFromTask() throws Exception {
    }*/

}