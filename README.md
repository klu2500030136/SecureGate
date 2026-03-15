<p align="center">
  <img src="src/assets/Screenshot 2026-03-15 231747.png" width="600">
</p>


# SecureGate – Access Control & Surveillance Management System

## Overview

SecureGate is a command-line based Java application designed to simulate a real-world access control and surveillance management system. The system manages user authentication, access logging, visitor handling, alert management, guard shift rotations, rule management, and access pattern analysis.

This project demonstrates the practical application of core Data Structures and Algorithms (DSA) concepts by mapping them to realistic security management operations. The system is modular, scalable, and designed with a layered architecture separating models, data structures, services, and utilities.

---

## Objectives

The primary objectives of the SecureGate system are:

- Simulate secure access control using card-based authentication.
- Record and manage entry/exit logs of users.
- Detect unauthorized access attempts and generate alerts.
- Manage visitor registration and processing.
- Rotate security guards efficiently using circular scheduling.
- Allow undo functionality for rule changes.
- Analyze access patterns for security monitoring.

---

## System Architecture

The project follows a modular layered structure:

```
SecureGate
│
├── main
│ └── SecureGateMain.java
│
├── models
│ ├── User.java
│ ├── LogEntry.java
│ ├── Alert.java
│ ├── Visitor.java
│ └── Guard.java
│
├── datastructures
│ ├── LogLinkedList.java
│ ├── VisitorQueue.java
│ ├── GuardCircularQueue.java
│ ├── AlertPriorityQueue.java
│ ├── UndoStack.java
│ └── ZoneArray.java
│
├── services
│ ├── AuthenticationService.java
│ ├── LogService.java
│ ├── AlertService.java
│ ├── VisitorService.java
│ ├── GuardService.java
│ ├── RuleService.java
│ └── AnalysisService.java
│
├── utils
│ ├── FileHandler.java
│ ├── InputValidator.java
│ └── DateTimeUtil.java
│
├── users.csv
└── zones.csv
```


---

## Core Functional Modules

### 1. Authentication Module

This module verifies access credentials using card IDs and zone permissions.

Features:
- Fast card verification
- Zone validation
- User lookup

Implementation:
- Users are loaded from `users.csv`
- Card ID → User mapping is stored using a HashMap

Time Complexity:
- Lookup: O(1)

---

### 2. Access Logging Module

Every access attempt is recorded with timestamp, user ID, zone ID, and access status.

Features:
- Store access logs dynamically
- Display access history
- Count total logs

Implementation:
- Custom singly linked list (`LogLinkedList`)

Time Complexity:
- Insert: O(1)
- Traversal: O(n)

---

### 3. Alert & Violation Management

Unauthorized access attempts generate security alerts.

Features:
- Alert generation
- Priority handling
- Alert monitoring

Implementation:
- Priority Queue (Heap)

Alerts are sorted by severity so that critical alerts are processed first.

Time Complexity:
- Insert: O(log n)
- Remove highest priority: O(log n)

---

### 4. Visitor Management System

Visitors are processed in the order they arrive.

Features:
- Visitor registration
- Queue management
- Visitor processing

Implementation:
- Queue (FIFO)

Time Complexity:
- Enqueue: O(1)
- Dequeue: O(1)

---

### 5. Guard Shift Rotation

Security guards rotate shifts automatically.

Features:
- Guard scheduling
- Current guard monitoring
- Shift rotation

Implementation:
- Circular Queue

Time Complexity:
- Rotation: O(1)

---

### 6. Rule Change Management

Security administrators can modify access rules and undo previous changes.

Features:
- Record rule updates
- Undo last rule change
- View last rule change

Implementation:
- Stack (LIFO)

Time Complexity:
- Push: O(1)
- Pop: O(1)

---

### 7. Access Pattern Analysis

Analyzes recorded logs to detect suspicious activity.

Features:
- Zone access frequency analysis
- Suspicious access detection
- Log sorting

Implementation:
- HashMap for counting frequencies
- Sorting algorithms using Comparators

---

## External Data Configuration

The system uses CSV files to allow easy modification of user and zone data without modifying source code.

### users.csv

userId,name,cardId,zoneId
U101,Alice,CARD1001,Z1
U102,Bob,CARD1002,Z2
U103,Charlie,CARD1003,Z1


### zones.csv

Z1
Z2
Z3


---

## Data Structures Used

| Data Structure | Usage |
|----------------|------|
| Array | Zone storage |
| Linked List | Access log management |
| HashMap | Fast user authentication |
| Queue | Visitor processing |
| Circular Queue | Guard shift rotation |
| Priority Queue (Heap) | Alert management |
| Stack | Undo rule changes |

---

## Algorithms Used

- Hash-based lookup
- Log traversal
- Frequency counting
- Sorting using Comparators
- Priority scheduling

---

## Technologies Used

- Java
- Command Line Interface (CLI)
- CSV File Handling
- Object-Oriented Programming

---

## Key Features

- Modular architecture
- External data configuration via CSV
- Efficient use of data structures
- Scalable design
- Real-world security system simulation

---

## How to Run

1. Compile the project.

```
javac main/SecureGateMain.java
```

2. Run the application.

```
java main.SecureGateMain
```


3. Use the CLI menu to interact with the system.

---

## Future Improvements

Possible extensions include:

- Database integration
- GUI-based interface
- Real-time surveillance integration
- Role-based access control
- Advanced anomaly detection

---

## Conclusion

SecureGate demonstrates how fundamental data structures and algorithms can be applied to build a functional access control and surveillance system. By mapping theoretical concepts to real-world operations, the project highlights the importance of efficient data handling, modular design, and algorithmic thinking in software engineering.