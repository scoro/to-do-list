package pl.pollub.task.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import pl.pollub.coWorker.data.model.CoWorker;

import java.util.List;

@Data
@AllArgsConstructor
public class NewTask {

    private String content;

    private List<Integer> coWorkersIds;

}
