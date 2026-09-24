
public class ServiceQueue {

    public static class ServiceRequest {
        String studentId;
        String requestDescription;

        public ServiceRequest(String studentId, String requestDescription) {
            this.studentId = studentId;
            this.requestDescription = requestDescription;
        }

        @Override
        public String toString() {
            return "Student ID: " + studentId + " | Request: " + requestDescription;
        }
    }

    private ServiceRequest[] queue;
    private int front, rear, count, capacity;

    public ServiceQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new ServiceRequest[capacity];
        this.front = 0;
        this.rear = -1;
        this.count = 0;
    }

    public boolean isEmpty() { return count == 0; }
    public boolean isFull() { return count == capacity; }

    public boolean enqueue(ServiceRequest request) {
        if (isFull()) {
            System.out.println("Service queue is full. Cannot add more requests.");
            return false;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = request;
        count++;
        return true;
    }

    public ServiceRequest dequeue() {
        if (isEmpty()) {
            return null;
        }
        ServiceRequest req = queue[front];
        front = (front + 1) % capacity;
        count--;
        return req;
    }
    public void displayAll() {
        if (isEmpty()) {
            System.out.println("No pending service requests.");
            return;
        }
        System.out.println("---- Pending Service Requests (Queue) ----");
        int idx = front;
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". " + queue[idx]);
            idx = (idx + 1) % capacity;
        }
    }
}
