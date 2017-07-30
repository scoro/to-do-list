package pl.pollub.task.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pollub.coWorker.data.model.CoWorker;

import java.util.List;

@Data
@AllArgsConstructor
public class Task {

    private final int id;

    private String content;

    private boolean done;

    private List<CoWorker> coWorkers;

}
