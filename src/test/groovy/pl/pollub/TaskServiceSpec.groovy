package pl.pollub

import pl.pollub.task2.*
import spock.lang.Specification

class TaskServiceSpec extends Specification {

    UserService userService = Mock()

    EmailNotifier emailNotifier = Mock()

    TaskService taskService = new TaskService(userService, emailNotifier)

    def "sends email to owner and contributors"() {
        given:
        userService.getUserById(_) >> { int id -> new User(id, "user" + id + "@wp.pl") }
        Task task = taskService.createTaskForUser(1, 2, 3)

        when:
        taskService.completeTask(task.getId())

        then:
        1 * emailNotifier.notify(task.getId(), { Collection<String> recipients -> recipientsEqual(['user1@wp.pl', 'user2@wp.pl', 'user3@wp.pl'], recipients) })
    }

    private static boolean recipientsEqual(Collection<String> expected, Collection<String> recipients) {
        recipients.toSet() == expected.toSet()
    }

}
