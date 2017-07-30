package pl.pollub.coWorker.web.model;

import lombok.Data;
import java.util.Set;

@Data
public class NewCoWorker {

    private String name;

    private Set<Integer> tasksIds;

}
