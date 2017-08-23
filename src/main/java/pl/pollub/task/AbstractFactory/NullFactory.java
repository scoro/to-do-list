package pl.pollub.task.AbstractFactory;

import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.NullObjects.NullNotifier;
import pl.pollub.task.NullObjects.NullReminder;
import pl.pollub.task.Reminder.Reminder;

public class NullFactory extends AbstractCommsFactory{

    @Override
    public Notifier createNotifier() {
        return new NullNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new NullReminder();
    }
}