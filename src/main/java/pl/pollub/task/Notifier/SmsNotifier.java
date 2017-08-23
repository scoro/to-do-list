package pl.pollub.task.Notifier;

import pl.pollub.task.Notifier.Notifier;

public class SmsNotifier implements Notifier {

    @Override
    public void notify(int userId) {
        System.out.println("Wysyłam sms do użytkownika z id: "+ userId);
    }
}
