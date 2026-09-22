
public class StudentLinkedList {

    private static class Node {
        Student data;
        Node next;
        Node(Student data) { this.data = data; }
    }

    private Node head;
    private int size;

    public StudentLinkedList() {
        head = null;
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public boolean add(Student s) {
        if (contains(s.getStudentId())) {
            return false; 
        }
        Node newNode = new Node(s);
        if (head == null) {
            head = newNode;
        } else {
            Node cur = head;
            while (cur.next != null) cur = cur.next;
            cur.next = newNode;
        }
        size++;
        return true;
    }

    public Student search(String studentId) {
        Node cur = head;
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(studentId)) {
                return cur.data;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean contains(String studentId) {
        return search(studentId) != null;
    }

    public boolean update(String studentId, String name, String programme, double marks) {
        Student s = search(studentId);
        if (s == null) return false;
        s.setName(name);
        s.setProgramme(programme);
        s.setMarks(marks);
        return true;
    }

    public Student delete(String studentId) {
        Node cur = head, prev = null;
        while (cur != null) {
            if (cur.data.getStudentId().equalsIgnoreCase(studentId)) {
                if (prev == null) {
                    head = cur.next;
                } else {
                    prev.next = cur.next;
                }
                size--;
                return cur.data;
            }
            prev = cur;
            cur = cur.next;
        }
        return null;
    }

    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("---- All Student Records (Linked List) ----");
        Node cur = head;
        int count = 1;
        while (cur != null) {
            System.out.println(count + ". " + cur.data);
            cur = cur.next;
            count++;
        }
    }

    public Student[] toArray() {
        Student[] arr = new Student[size];
        Node cur = head;
        int i = 0;
        while (cur != null) {
            arr[i++] = cur.data;
            cur = cur.next;
        }
        return arr;
    }
}
