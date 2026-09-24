import java.util.EmptyStackException;

public class ActionStack {

    public static class Action {
        String type;    
        String studentId;
        String details;

        public Action(String type, String studentId, String details) {
            this.type = type;
            this.studentId = studentId;
            this.details = details;
        }

        @Override
        public String toString() {
            return "[" + type + "] Student ID: " + studentId + " - " + details;
        }
    }

    private Action[] actions;
    private int top;
    private int capacity;

    public ActionStack(int capacity) {
        this.capacity = capacity;
        this.actions = new Action[capacity];
        this.top = -1;
    }

    public boolean isEmpty() { return top == -1; }
    public boolean isFull() { return top == capacity - 1; }

    public void push(Action action) {
        if (isFull()) {
            for (int i = 0; i < capacity - 1; i++) {
                actions[i] = actions[i + 1];
            }
            actions[capacity - 1] = action;
        } else {
            actions[++top] = action;
        }
    }

    public Action pop() {
        if (isEmpty()) throw new EmptyStackException();
        return actions[top--];
    }

    public Action peek() {
        if (isEmpty()) throw new EmptyStackException();
        return actions[top];
    }
    public void displayRecentActions() {
        if (isEmpty()) {
            System.out.println("No recent actions recorded.");
            return;
        }
        System.out.println("---- Recent Actions (Stack, most recent first) ----");
        for (int i = top; i >= 0; i--) {
            System.out.println((top - i + 1) + ". " + actions[i]);
        }
    }
}
