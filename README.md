[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# PCCCS495 – Term II Project

## Project Title

---Vehicle Rental Management System

## Problem Statement (max 150 words)

---Managing vehicle rentals manually can be confusing and time-consuming. It can lead to errors in booking, vehicle tracking, and rental cost calculation. This project solves that problem by providing a simple desktop-based application where users can add vehicles, register customers, create bookings, and view records in an organized way. The project demonstrates the use of Object-Oriented Programming concepts in Java along with a graphical user interface built using Swing.

## Target User

-Rental shop owners 
- Small vehicle rental businesses 
- Admin users 
- Students learning Java OOP and Swing

## Core Features

- Add new vehicles 
- Register customers 
- Create bookings 
- View vehicle, customer, and booking records -
- Check vehicle availability , Calculate rental cost , Simple and beginner-friendly Swing-based GUI
---

## OOP Concepts Used

- Abstraction:Common behavior is defined in a base vehicle class
- Inheritance:Different vehicle types extend the base vehicle class
- Polymorphism:Rental cost is calculated differently for different vehicle types
- Exception Handling:Custom exceptions are used to handle invalid booking duration and unavailable vehicles  
- Collections:Data is managed using `HashMap` and `ArrayList` for efficient storage and retrieval  

---

## Proposed Architecture Description

---## Proposed Architecture Description

The project follows a layered architecture to ensure separation of concerns and better organization:

- **Model Layer (`model/`)**  
  Contains core classes such as `vehicle`, `car`, `bike`, `truck`, `customer`, and `booking`. These classes represent real-world entities and store data.

- **Service Layer (`service/`)**  
  Contains the `rentalservice` class which handles all business logic like adding vehicles, managing customers, and processing bookings. It uses collections such as `HashMap` and `ArrayList` for data storage.

- **GUI Layer (`gui/`)**  
  Built using Java Swing. It provides user interaction through forms, buttons, and tables. The `MainFrame` class acts as the main interface.

- **Exception Layer (`exception/`)**  
  Contains custom exceptions such as invalid booking duration and vehicle availability errors to ensure proper validation and error handling.

The system follows a modular approach where each layer interacts with others in a structured way, improving maintainability and scalability.

## How to Run

---### Using Command Line
write in VS code,intellij,Eclipse

javac -d out src/model/*.java src/service/*.java src/exception/*.java src/gui/*.java

java -cp out gui.VehicleRentalApp

## Git Discipline Notes

- A total of **12+ meaningful commits** have been made during the development of this project.
- Each commit represents a specific stage of development, ensuring incremental progress.

### Key Development Stages:
- Project structure initialization
- Base vehicle model and subclasses (car, bike, truck)
- Customer and booking model implementation
- Service layer and exception handling
- Swing GUI development (initialization and main window)
- README documentation
- UML diagram integration
- GUI improvements and updates
- Report and presentation (PPT) addition

- Commit messages are descriptive and reflect the work done at each stage.
- Development was carried out over multiple days, avoiding a single bulk upload.
- Version control was properly maintained using Git and GitHub.
