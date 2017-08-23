package pl.pollub.task.Builder;

import pl.pollub.task.user.User;

public class UserBuilder {
    User user;

    public UserBuilder(User user){
        this.user = user;
    }

    public UserBuilder createBuilder(int id){
        User user = new User(id);
        return new UserBuilder(user);
    }

    public UserBuilder withPhoneNumber(String phoneNumber){
        user.setSms(phoneNumber);
        return this;
    }

    public UserBuilder paidForSms(Boolean hasPaidForSms){
        user.setHasPaidForSms(hasPaidForSms);
        return this;
    }

    public UserBuilder withEmail(String email){
        user.setEmail(email);
        return this;
    }

    public User build(){
        return user;
    }
}
