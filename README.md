# Trains of Sheffield System

## Overview

This project was developed as part of the Systems Design and Security module (COM2008/COM3008) at the University of Sheffield. The goal was to design and implement a software system for the "Trains of Sheffield" store, a retailer of model railways, enabling both customers and staff to interact with the system efficiently.

---

## Project Objective

The objective of this system is to:
- **Analyze Business Requirements**: Understand data and operations from the client brief.
- **Design a Conceptual Information Model**: Use UML class diagrams for clarity.
- **Implement the System**: Develop a MySQL database, Java middleware, and Java Swing-based user interface.
- **Provide Robust Security**: Implement defenses against SQL injection and privilege escalation.

---

## Features

- **User Roles**: Customers, staff, and managers, each with tailored access and privileges.
- **Inventory Management**: Staff can add/update products, and stock levels automatically adjust based on sales.
- **Order Processing**: Customers can browse, create, and modify orders; staff can fulfill orders.
- **Security**: Passwords and sensitive details are stored securely, ensuring system robustness.
- **GUI Interface**: Seamless navigation for different roles through a Java Swing interface.

---

## Technologies Used

- **Programming Language**: Java
- **Database**: MySQL
- **GUI Framework**: Java Swing
- **Tools**: Gradle for builds, UML diagrams for design clarity.

---

## Installation and Setup

### Prerequisites
- Java Development Kit (JDK) installed.
- MySQL database setup with the provided schema.
- An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.

### Running the Program

1. **Using Gradle**:
   - Navigate to the project directory.
   - Run `gradle build` and execute the built file.

2. **Manual Execution**:
   - Locate `App.java` in `trainsheffield/app/src/main/java/trainsheffield/`.
   - Run the file via your preferred IDE or the command line.

---

## Usage Instructions

### Customer Actions:
- Register an account.
- Browse products by category.
- Create, edit, and confirm orders.
- View order history.

### Staff Actions:
- Manage product inventory.
- Process and fulfill customer orders.
- View a history of completed orders.

### Manager Actions:
- Promote users to staff roles.
- Manage staff privileges.

---

## Security Measures

- **Authentication**: Email-based login with secure password storage.
- **Authorization**: Role-based access to features.
- **Injection Prevention**: SQL injection and privilege escalation defenses.

---

## Screenshots

### Main Customer Interface:
- Product browsing and order creation.

### Staff Dashboard:
- Inventory management and order processing.

### Manager Controls:
- User role management.

---


