package pl.pollub.task.AbstractFactory;

import pl.pollub.task.Notifier.EmailNotifier;
import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.Reminder.EmailReminder;
import pl.pollub.task.Reminder.Reminder;

public class EmailFactory extends AbstractCommsFactory{

    @Override
    public Notifier createNotifier() {
        return new EmailNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new EmailReminder();
    }
}
