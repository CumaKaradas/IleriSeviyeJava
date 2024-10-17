import java.io.*;
import java.util.*;

public class ToDoListApp {
    private static final String FILE_NAME = "tasks.txt";
    private static List<String> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);
        String command;

        System.out.println("Welcome to the To-Do List Application!");

        while (true) {
            System.out.println("\nCommands: [add], [remove], [list], [exit]");
            System.out.print("Enter command: ");
            command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "add":
                    addTask(scanner);
                    break;
                case "remove":
                    removeTask(scanner);
                    break;
                case "list":
                    listTasks();
                    break;
                case "exit":
                    saveTasksToFile();
                    System.out.println("Exiting the application. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid command. Try again.");
            }
        }
    }

    // Task ekleme
    private static void addTask(Scanner scanner) {
        System.out.print("Enter the task: ");
        String task = scanner.nextLine().trim();
        if (!task.isEmpty()) {
            tasks.add(task);
            System.out.println("Task added: " + task);
        } else {
            System.out.println("Task cannot be empty!");
        }
    }

    // Task silme
    private static void removeTask(Scanner scanner) {
        System.out.print("Enter the task number to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < tasks.size()) {
                String removedTask = tasks.remove(index);
                System.out.println("Task removed: " + removedTask);
            } else {
                System.out.println("Invalid task number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    // Taskları listeleme
    private static void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("\nTo-Do List:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    // Dosyadan task'leri yükleme
    private static void loadTasksFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(line);
            }
            System.out.println("Tasks loaded from file.");
        } catch (IOException e) {
            System.out.println("No previous tasks found.");
        }
    }

    // Task'leri dosyaya kaydetme
    private static void saveTasksToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String task : tasks) {
                writer.write(task);
                writer.newLine();
            }
            System.out.println("Tasks saved to file.");
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }
}
