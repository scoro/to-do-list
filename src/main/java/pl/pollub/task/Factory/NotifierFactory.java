package pl.pollub.task.Factory;

import pl.pollub.task.Notifier.EmailNotifier;
import pl.pollub.task.Notifier.Notifier;
import pl.pollub.task.Notifier.SmsNotifier;
import pl.pollub.task.NullObjects.NullNotifier;
import pl.pollub.task.user.User;

public class NotifierFactory {

    private SmsNotifier smsNotifier = new SmsNotifier();
    private EmailNotifier emailNotifier = new EmailNotifier();
    private NullNotifier nullNotifier = new NullNotifier();

    Notifier createNotifier(User user){
        if(!user.getEmail().isEmpty()){
            return emailNotifier;
        }
        else if(!user.getSms().isEmpty()){
            return smsNotifier;
        }
        else
            return nullNotifier;
    }

}
