package pl.pollub.task.AbstractFactory;

import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.Notifier.SmsNotifier;
import pl.pollub.task.Reminder.Reminder;
import pl.pollub.task.Reminder.SmsReminder;

public class SmsFactory extends AbstractCommsFactory {


    @Override
    public Notifier createNotifier() {
        return new SmsNotifier();
    }

    @Override
    public Reminder createReminder() {
        return new SmsReminder();
    }
}
