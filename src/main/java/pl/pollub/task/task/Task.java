package pl.pollub.task.task;

import lombok.Data;

import java.util.List;

@Data
public class Task {

    private final int id;

    private final int userId;

    private List<Integer> contributors;

    public Task(int id, int userId){
        this.id = id;
        this.userId = userId;
    }

    public Task(int id, int userId, List<Integer> contributors){
        this.id = id;
        this.userId = userId;
        this.contributors = contributors;
    }
}
