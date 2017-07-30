package pl.pollub.task.data.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import pl.pollub.coWorker.data.model.CoWorker;
import pl.pollub.coWorker.data.repository.CoWorkerRepository;
import pl.pollub.coWorker.web.model.NewCoWorker;
import pl.pollub.task.data.model.Task;
import pl.pollub.task.web.model.NewTask;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private CoWorkerRepository coWorkerRepository;


    @Test
    public void addTaskAndCheckIfCoWorkersHaveThisTask() throws Exception {
        CoWorker Adrian=coWorkerRepository.add(new NewCoWorker("Adrian"));
        CoWorker Bartek=coWorkerRepository.add(new NewCoWorker("Bartek"));
        Set<CoWorker> coWorkersForTask = new HashSet<>(Arrays.asList(Adrian,Bartek));
        Task task=taskRepository.add(new NewTask("Zostac wielkim programista",
                coWorkersForTask.stream().map(CoWorker::getId).collect(Collectors.toSet())));
        Set<Task> addedTasks=taskRepository.getAllTasks();
        assertEquals(1, Adrian.getTasks().size());
        assertTrue("Adrian has added task", Adrian.getTasks().contains(task));
        assertTrue("Bartek has added task", Bartek.getTasks().contains(task));
    }
/*
    @Test
    public void add1() throws Exception {
    }

    @Test
    public void getTask() throws Exception {
    }

    @Test
    public void getTasks() throws Exception {
    }

    @Test
    public void getAllTasks() throws Exception {
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void delete1() throws Exception {
    }

    @Test
    public void deleteAll() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

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