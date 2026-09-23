public class StudentBST {

    private static class Node {
        Student data;
        Node left, right;
        Node(Student data) { this.data = data; }
    }

    private Node root;

    public void insert(Student s) {
        root = insertRec(root, s);
    }

    private Node insertRec(Node node, Student s) {
        if (node == null) return new Node(s);
        int cmp = s.getStudentId().compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = insertRec(node.left, s);
        } else if (cmp > 0) {
            node.right = insertRec(node.right, s);
        } else {
            node.data = s; 
        }
        return node;
    }

    public Student search(String studentId) {
        Node cur = root;
        while (cur != null) {
            int cmp = studentId.compareToIgnoreCase(cur.data.getStudentId());
            if (cmp == 0) return cur.data;
            cur = (cmp < 0) ? cur.left : cur.right;
        }
        return null;
    }

    public void delete(String studentId) {
        root = deleteRec(root, studentId);
    }

    private Node deleteRec(Node node, String studentId) {
        if (node == null) return null;
        int cmp = studentId.compareToIgnoreCase(node.data.getStudentId());
        if (cmp < 0) {
            node.left = deleteRec(node.left, studentId);
        } else if (cmp > 0) {
            node.right = deleteRec(node.right, studentId);
        } else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node successor = node.right;
            while (successor.left != null) successor = successor.left;
            node.data = successor.data;
            node.right = deleteRec(node.right, successor.data.getStudentId());
        }
        return node;
    }

    public void rebuild(Student[] students) {
        root = null;
        for (Student s : students) {
            insert(s);
        }
    }

    public void displayInOrder() {
        if (root == null) {
            System.out.println("No student records in the tree.");
            return;
        }
        System.out.println("---- Students Sorted by ID (BST In-Order) ----");
        int[] counter = {1};
        inOrderRec(root, counter);
    }

    private void inOrderRec(Node node, int[] counter) {
        if (node == null) return;
        inOrderRec(node.left, counter);
        System.out.println(counter[0] + ". " + node.data);
        counter[0]++;
        inOrderRec(node.right, counter);
    }
}
