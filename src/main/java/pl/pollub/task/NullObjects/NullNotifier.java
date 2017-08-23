package pl.pollub.task.NullObjects;

import pl.pollub.task.Notifier.Notifier;

public class NullNotifier implements Notifier {
    @Override
    public void notify(int userId) {
        System.out.println("Jestem nullem");
    }
}
