import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static StudentLinkedList studentList = new StudentLinkedList();
    private static ActionStack actionStack = new ActionStack(50);
    private static ServiceQueue serviceQueue = new ServiceQueue(50);
    private static StudentBST bst = new StudentBST();
    private static StudentHashTable hashTable = new StudentHashTable(101);
    private static CampusGraph campusGraph = new CampusGraph();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addStudentRecord(); break;
                case 2: updateStudentRecord(); break;
                case 3: deleteStudentRecord(); break;
                case 4: studentList.displayAll(); break;
                case 5: addServiceRequest(); break;
                case 6: processNextServiceRequest(); break;
                case 7: actionStack.displayRecentActions(); break;
                case 8: bst.displayInOrder(); break;
                case 9: searchStudentUsingHashing(); break;
                case 10: addCampusLocation(); break;
                case 11: removeCampusLocation(); break;
                case 12: addCampusConnection(); break;
                case 13: removeCampusConnection(); break;
                case 14: campusGraph.displayNetwork(); break;
                case 15: traverseCampusLocations(); break;
                case 16:
                    running = false;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a number between 1 and 16.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("=========================================================");
        System.out.println(" University Student Record and Campus Route Management ");
        System.out.println("=========================================================");
        System.out.println("1.  Add Student Record");
        System.out.println("2.  Update Student Record");
        System.out.println("3.  Delete Student Record");
        System.out.println("4.  Display All Records using Linked List");
        System.out.println("5.  Add Service Request to Queue");
        System.out.println("6.  Process Next Service Request");
        System.out.println("7.  Display Recent Actions using Stack");
        System.out.println("8.  Display Students using BST/AVL");
        System.out.println("9.  Search Student using Hashing");
        System.out.println("10. Add Campus Location");
        System.out.println("11. Remove Campus Location");
        System.out.println("12. Add Campus Connection/Road");
        System.out.println("13. Remove Campus Connection/Road");
        System.out.println("14. Display Campus Connections");
        System.out.println("15. Traverse Campus Locations using BFS or DFS");
        System.out.println("16. Exit");
    }


    private static void addStudentRecord() {
        String id = readNonEmptyString("Enter Student ID: ");
        if (studentList.contains(id)) {
            System.out.println("Error: A student with ID " + id + " already exists.");
            return;
        }
        String name = readNonEmptyString("Enter Student Name: ");
        String programme = readNonEmptyString("Enter Programme: ");
        double marks = readValidMarks("Enter Marks (0-100): ");

        Student s = new Student(id, name, programme, marks);
        studentList.add(s);
        bst.insert(s);
        hashTable.put(id, s);
        actionStack.push(new ActionStack.Action("ADD", id, "Added new student record."));
        System.out.println("Student record added successfully.");
    }

    private static void updateStudentRecord() {
        String id = readNonEmptyString("Enter Student ID to update: ");
        if (!studentList.contains(id)) {
            System.out.println("Error: No student found with ID " + id);
            return;
        }
        String name = readNonEmptyString("Enter new Name: ");
        String programme = readNonEmptyString("Enter new Programme: ");
        double marks = readValidMarks("Enter new Marks (0-100): ");

        studentList.update(id, name, programme, marks);
        bst.rebuild(studentList.toArray());
        hashTable.rebuild(studentList.toArray());
        actionStack.push(new ActionStack.Action("UPDATE", id, "Updated student record."));
        System.out.println("Student record updated successfully.");
    }

    private static void deleteStudentRecord() {
        String id = readNonEmptyString("Enter Student ID to delete: ");
        Student removed = studentList.delete(id);
        if (removed == null) {
            System.out.println("Error: No student found with ID " + id);
            return;
        }
        bst.delete(id);
        hashTable.remove(id);
        actionStack.push(new ActionStack.Action("DELETE", id, "Deleted: " + removed));
        System.out.println("Student record deleted successfully.");
    }

    private static void searchStudentUsingHashing() {
        String id = readNonEmptyString("Enter Student ID to search: ");
        Student s = hashTable.get(id);
        if (s == null) {
            System.out.println("No student found with ID " + id);
        } else {
            System.out.println("Found: " + s);
        }
    }

    private static void addServiceRequest() {
        String id = readNonEmptyString("Enter Student ID: ");
        String description = readNonEmptyString("Enter Service Request description: ");
        boolean added = serviceQueue.enqueue(new ServiceQueue.ServiceRequest(id, description));
        if (added) {
            System.out.println("Service request added to the queue.");
        }
    }

    private static void processNextServiceRequest() {
        ServiceQueue.ServiceRequest req = serviceQueue.dequeue();
        if (req == null) {
            System.out.println("No pending service requests to process.");
        } else {
            System.out.println("Processing request -> " + req);
            actionStack.push(new ActionStack.Action("SERVICE", req.studentId,
                    "Processed request: " + req.requestDescription));
        }
    }


    private static void addCampusLocation() {
        String location = readNonEmptyString("Enter new Campus Location name: ");
        boolean added = campusGraph.addLocation(location);
        System.out.println(added ? "Location added successfully."
                : "Error: Location already exists.");
    }

    private static void removeCampusLocation() {
        String location = readNonEmptyString("Enter Campus Location to remove: ");
        boolean removed = campusGraph.removeLocation(location);
        System.out.println(removed ? "Location removed successfully."
                : "Error: Location not found.");
    }

    private static void addCampusConnection() {
        String loc1 = readNonEmptyString("Enter first Location: ");
        String loc2 = readNonEmptyString("Enter second Location: ");
        boolean added = campusGraph.addConnection(loc1, loc2);
        System.out.println(added ? "Connection added successfully."
                : "Error: Could not add connection (check that both locations exist and are not already connected).");
    }

    private static void removeCampusConnection() {
        String loc1 = readNonEmptyString("Enter first Location: ");
        String loc2 = readNonEmptyString("Enter second Location: ");
        boolean removed = campusGraph.removeConnection(loc1, loc2);
        System.out.println(removed ? "Connection removed successfully."
                : "Error: Connection not found.");
    }

    private static void traverseCampusLocations() {
        if (campusGraph.isEmpty()) {
            System.out.println("No campus locations available to traverse.");
            return;
        }
        String start = readNonEmptyString("Enter starting Location: ");
        if (!campusGraph.hasLocation(start)) {
            System.out.println("Error: Location not found.");
            return;
        }
        System.out.println("Choose traversal type: 1) BFS   2) DFS");
        int type = readInt("Enter choice: ");
        List<String> result;
        if (type == 1) {
            result = campusGraph.bfs(start);
            System.out.println("BFS Traversal: " + result);
        } else if (type == 2) {
            result = campusGraph.dfs(start);
            System.out.println("DFS Traversal: " + result);
        } else {
            System.out.println("Invalid traversal choice.");
        }
    }


    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
    }

    private static double readValidMarks(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                double marks = Double.parseDouble(input);
                if (marks < 0 || marks > 100) {
                    System.out.println("Invalid marks. Please enter a value between 0 and 100.");
                    continue;
                }
                return marks;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
            }
        }
    }
}