package pl.pollub.task.AbstractFactory;


import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.Reminder.Reminder;
import pl.pollub.task.user.User;

public abstract class AbstractCommsFactory {

    public static AbstractCommsFactory createFactory(User user){
        if(!user.getEmail().isEmpty()){
            return new EmailFactory();
        }
        else if(!user.getSms().isEmpty()){
            return new SmsFactory();
        }
        else
            return new NullFactory();
    }

    public abstract Notifier createNotifier();

    public abstract Reminder createReminder();
}
