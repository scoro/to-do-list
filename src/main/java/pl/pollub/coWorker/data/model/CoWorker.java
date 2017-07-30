package pl.pollub.coWorker.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pollub.task.data.model.Task;

import java.util.List;

@Data
@AllArgsConstructor
public class CoWorker {

    private final int id;

    private String name;

    private List<Task> tasks;

}
