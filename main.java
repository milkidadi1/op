import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Mini Java Project: Patient Management System");

        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> addPatient(scanner);
                case "2" -> listPatients();
                case "3" -> updateVisitStatus(scanner);
                case "4" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please choose 1-4.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nChoose an option:");
        System.out.println("1) Register patient");
        System.out.println("2) View all patients");
        System.out.println("3) Mark patient as visited");
        System.out.println("4) Exit");
        System.out.print("> ");
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter patient name: ");
        String name = scanner.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Patient name cannot be empty.");
            return;
        }

        System.out.print("Enter age: ");
        String ageInput = scanner.nextLine().trim();

        int age;
        try {
            age = Integer.parseInt(ageInput);
            if (age <= 0) {
                System.out.println("Age must be greater than 0.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid age.");
            return;
        }

        System.out.print("Enter disease/concern: ");
        String concern = scanner.nextLine().trim();

        if (concern.isEmpty()) {
            System.out.println("Disease/concern cannot be empty.");
            return;
        }

        patients.add(new Patient(name, age, concern));
        System.out.println("Patient registered successfully.");
    }

    private static void listPatients() {
        if (patients.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }

        System.out.println("\nPatient List:");
        for (int i = 0; i < patients.size(); i++) {
            Patient patient = patients.get(i);
            String status = patient.visited ? "Visited" : "Waiting";
            System.out.printf(
                "%d. %s | Age: %d | Concern: %s | Status: %s%n",
                i + 1,
                patient.name,
                patient.age,
                patient.concern,
                status
            );
        }
    }

    private static void updateVisitStatus(Scanner scanner) {
        if (patients.isEmpty()) {
            System.out.println("No patients to update.");
            return;
        }

        listPatients();
        System.out.print("Enter patient number to mark as visited: ");
        String input = scanner.nextLine().trim();

        try {
            int index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= patients.size()) {
                System.out.println("Invalid patient number.");
                return;
            }

            patients.get(index).visited = true;
            System.out.println("Patient status updated to visited.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
        }
    }

    private static class Patient {
        private final String name;
        private final int age;
        private final String concern;
        private boolean visited;

        private Patient(String name, int age, String concern) {
            this.name = name;
            this.age = age;
            this.concern = concern;
            this.visited = false;
        }
    }
}
