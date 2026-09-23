public class StudentHashTable {

    private static class Entry {
        String key;
        Student value;
        Entry next;
        Entry(String key, Student value) {
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;
    private int capacity;
    private int size;

    public StudentHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new Entry[capacity];
        this.size = 0;
    }

    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = (hash * 31 + key.charAt(i)) & 0x7fffffff; 
        }
        return hash % capacity;
    }

    public void put(String studentId, Student s) {
        int index = hash(studentId);
        Entry cur = table[index];
        while (cur != null) {
            if (cur.key.equalsIgnoreCase(studentId)) {
                cur.value = s; 
                return;
            }
            cur = cur.next;
        }
        Entry newEntry = new Entry(studentId, s);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;
    }

    public Student get(String studentId) {
        int index = hash(studentId);
        Entry cur = table[index];
        while (cur != null) {
            if (cur.key.equalsIgnoreCase(studentId)) {
                return cur.value;
            }
            cur = cur.next;
        }
        return null;
    }

    public boolean remove(String studentId) {
        int index = hash(studentId);
        Entry cur = table[index], prev = null;
        while (cur != null) {
            if (cur.key.equalsIgnoreCase(studentId)) {
                if (prev == null) table[index] = cur.next;
                else prev.next = cur.next;
                size--;
                return true;
            }
            prev = cur;
            cur = cur.next;
        }
        return false;
    }

    public void rebuild(Student[] students) {
        table = new Entry[capacity];
        size = 0;
        for (Student s : students) {
            put(s.getStudentId(), s);
        }
    }

    public int size() { return size; }
}
