package pl.pollub.coWorker.web.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class NewCoWorker {

    private String name;

    private Set<Integer> tasksIds;

    public NewCoWorker(String name){
        this.name=name;
        tasksIds=new HashSet<>();
    }

    public NewCoWorker(String name, int id) {
        this.name=name;
        tasksIds=new HashSet<>();
        tasksIds.add(id);
    }
}
