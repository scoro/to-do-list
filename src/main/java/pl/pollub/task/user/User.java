package pl.pollub.task.user;

import lombok.Data;

@Data
public class User {

    private final int id;

    private String email;

    private String sms;

    private Boolean hasPaidForSms;

}
