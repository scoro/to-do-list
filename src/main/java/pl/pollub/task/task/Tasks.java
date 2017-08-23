package pl.pollub.task.task;

import java.util.List;

public class Tasks {
    public static Task createTaskWithOnlyOwner(int id, int userid){
        return new Task(id, userid);
    }

    public static Task createTaskWithContributors(int id, int userid, List<Integer> contributors){
        return new Task(id, userid, contributors);
    }
}
