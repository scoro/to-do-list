package pl.pollub.task.Reminder;

public class SmsReminder implements Reminder {
    @Override
    public void remind(int userId) {
        System.out.println("Sms przypominajacy wysłany do: "+userId);
    }
}
