package pl.pollub.task.Reminder;

public class EmailReminder implements Reminder {
    @Override
    public void remind(int userId) {
        System.out.println("Mail przypominajacy wysłany do: "+userId);
    }
}
