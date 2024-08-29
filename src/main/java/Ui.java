import java.util.ArrayList;
import java.time.LocalDate;

public class Ui {
    public void showWelcome() {
        System.out.println("Hello! I'm Velma\nWhat can I do for you?");
    }

    public void showGoodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void showSavingError() {
        System.out.println("Error saving file");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
    }

    public void showTaskDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
    }

    public void showAllTasks(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void showTasksOnDate(ArrayList<Task> tasks, LocalDate date) {
        System.out.println("Here are the tasks on " + date + ":");
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                Deadline deadlineTask = (Deadline) task;
                if (deadlineTask.by.toLocalDate().isEqual(date)) {
                    System.out.println((i + 1) + ". " + task);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No tasks found on this date.");
        }
    }

    public void showMarkUnmarkTask(Task task, boolean isMarked) {
        if (isMarked) {
            System.out.println("Nice! I have marked this task as done:");
        } else {
            System.out.println("OK! I have marked this task as not done yet:");
        }
        System.out.println("  " + "[" + task.getStatusIcon() + "] " + task.description);
    }
    public void showError(String message) {
        System.out.println(message);
    }
}
