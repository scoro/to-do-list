package pl.pollub.task.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import pl.pollub.coWorker.data.model.CoWorker;

import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "coWorkers")
@ToString(exclude = "coWorkers")
public class Task {

    private final int id;

    private String content;

    private boolean done;

    private Set<CoWorker> coWorkers;

    public Task(int id, String content, boolean done, Set<CoWorker> coWorkers){
        this.id=id;
        this.content=content;
        this.coWorkers=coWorkers;
        coWorkers.forEach(coWorker -> coWorker.getTasks().add(this));
    }

    public void setCoWorkers(Set<CoWorker> coWorkers){
        this.coWorkers=coWorkers;
        coWorkers.forEach(coWorker -> coWorker.getTasks().add(this));
    }

    public void addCoWorker(CoWorker coWorker){
        coWorkers.add(coWorker);
        coWorker.getTasks().add(this);
    }

    public void addCoWorkers(Set<CoWorker> coWorkers){
        this.coWorkers.addAll(coWorkers);
        coWorkers.forEach(coWorker -> coWorker.getTasks().add(this));
    }

    public void deleteCoWorker(CoWorker coWorker){
        coWorker.getTasks().remove(this);
        coWorkers.remove(coWorker);
    }

    public void deleteCoWorkers(Set<CoWorker> coWorkers){
        coWorkers.forEach(coWorker -> coWorker.getTasks().remove(this));
        this.coWorkers.removeAll(coWorkers);
    }

    public void deleteAllCoWorkers(){
        coWorkers.forEach(coWorker -> coWorker.getTasks().remove(this));
        coWorkers.clear();
    }
}
