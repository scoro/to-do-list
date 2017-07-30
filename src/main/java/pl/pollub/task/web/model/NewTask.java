package pl.pollub.task.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
public class NewTask {

    private String content;

    private Set<Integer> coWorkersIds;

    public NewTask(String content){
        this.content=content;
        coWorkersIds=new HashSet<>();
    }

}
