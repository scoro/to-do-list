package pl.pollub.task.NullObjects;

import pl.pollub.task.Reminder.Reminder;

public class NullReminder implements Reminder {
    @Override
    public void remind(int userId) {
        System.out.println("jestem nullem");
    }
}
