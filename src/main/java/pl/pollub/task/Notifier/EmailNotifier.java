package pl.pollub.task.Notifier;


public class EmailNotifier implements Notifier {

    @Override
    public void notify(int userId) {
        System.out.println("Wysyłam email do użytkownika z id: "+ userId);
    }
}
