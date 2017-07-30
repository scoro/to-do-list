package pl.pollub.task.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class NewTask {

    private String content;

    private Set<Integer> coWorkersIds;

}
