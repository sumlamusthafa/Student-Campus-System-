# CIT300 – University Student Record and Campus Route Management System

**Module:** CIT300 Data Structures and Algorithms
**Assignment:** Graded Practical Assignment 1 (Week 10)
**Coverage:** Linear Data Structures, Trees, Hashing, and Graphs

## 1. Project Overview
A Java console application that:
- Manages university student records (add, update, delete, search, display).
- Tracks recent actions/history using a stack.
- Manages student service requests using a queue.
- Organizes and searches student records using a Binary Search Tree (BST).
- Provides fast Student ID lookup using a custom hash table.
- Models the campus as a graph (locations = vertices, roads/paths = edges) with
  add/remove operations and BFS/DFS traversal.

## 2. How to Compile and Run
```bash
cd src
javac *.java
java Main
```

## 3. File Structure
| File | Purpose |
|---|---|
| `Student.java` | Student record model (ID, Name, Programme, Marks) |
| `StudentLinkedList.java` | Custom linked list — primary storage for student records |
| `ActionStack.java` | Custom stack — recent actions / undo history |
| `ServiceQueue.java` | Custom circular queue — student service requests |
| `StudentBST.java` | Binary Search Tree — organizes/searches students by ID |
| `StudentHashTable.java` | Custom hash table — O(1) average Student ID search |
| `CampusGraph.java` | Adjacency-list graph — campus locations/connections, BFS/DFS |
| `Main.java` | Menu-driven console interface tying all components together |

## 4. Group Members and Responsibilities
> **IMPORTANT:** Fill in your actual group details below before submission.
> Missing or incorrect information may result in marks being deducted.

| Name | Student ID | Assigned Responsibility | Individual Contribution |
|---|---|---|---|
| [Fathima Sumla] | [23DA2-0852] | Linked list implementation and student-record management | [Describe specific contribution] |
| [Fathima Atheefa] | [23DA2-0953] | Stack and queue implementation and related operations | [Describe specific contribution] |
| [Fathima Nuska] | [23DA2-0936] | BST/AVL tree implementation and hashing/search functionality | [Describe specific contribution] |
| [Shamil Ahamed] | [23DA2-0521] | Graph implementation, campus locations, connections, BFS/DFS | [Describe specific contribution] |

All members: Integration, validation, testing, debugging, documentation, and GitHub collaboration.

## 5. Testing
The application has been manually tested end-to-end covering all 16 menu options,
including: adding/updating/deleting students, duplicate ID handling, invalid marks,
service queue processing, undo/history stack, BST in-order display, hash-based
search, and campus graph add/remove/traversal (BFS & DFS).

## 6. Notes
- Input validation is implemented for numeric choices, empty strings, marks range (0–100),
  duplicate Student IDs, duplicate/missing campus locations, and missing connections.
- The graph uses an **adjacency list** (via `HashMap<String, List<String>>`) for efficient
  neighbour lookups and traversal.
