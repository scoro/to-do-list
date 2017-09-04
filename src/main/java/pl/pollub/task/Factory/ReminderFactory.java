package pl.pollub.task.Factory;

import pl.pollub.task.NullObjects.NullReminder;
import pl.pollub.task.Reminder.EmailReminder;
import pl.pollub.task.Reminder.Reminder;
import pl.pollub.task.Reminder.SmsReminder;
import pl.pollub.task.user.User;

public class ReminderFactory {


    private SmsReminder smsReminder = new SmsReminder();
    private EmailReminder emailReminder = new EmailReminder();
    private NullReminder nullReminder = new NullReminder();

    Reminder createReminder(User user){
        if(!user.getEmail().isEmpty()){
            return emailReminder;
        }
        else if(!user.getSms().isEmpty()){
            return smsReminder;
        }
        else
            return nullReminder;
    }

}
