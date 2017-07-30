package pl.pollub.coWorker.web.model;

import lombok.Data;
import pl.pollub.task.data.model.Task;

import java.util.List;

@Data
public class NewCoWorker {

    private String name;

    private List<Integer> tasksIds;

}
