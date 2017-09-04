package pl.pollub.task.task;

import pl.pollub.task.Factory.NotifierFactory;
import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.NullObjects.NullNotifier;
import pl.pollub.task.NullObjects.NullReminder;
import pl.pollub.task.Reminder.Reminder;

import java.sql.Connection;
import java.util.*;

public class TaskSummariser {

    private Map<Date,Task> endedTasks = new HashMap<>();

    private static TaskSummariser instance = null;

    private TaskSummariser(){
    }

    public static synchronized TaskSummariser getInstance() {
        if(instance == null)
            instance = new TaskSummariser();
        return instance;
    }

    public void notify(Task task, Notifier notifier){
        endedTasks.put(new Date(),task);

        if(notifier==null)
            notifier=new NullNotifier();

        notifier.notify(task.getUserId());
    }

    public void remind(Task task, Reminder reminder){
        endedTasks.put(new Date(),task);

        if(reminder==null)
            reminder=new NullReminder();

        reminder.remind(task.getUserId());
    }

    public Map<Date, Task> getEndedTasks() {
        return endedTasks;
    }

    public Task getEndedTasksByDate(Date date) {
        return endedTasks.get(date);
    }
}
