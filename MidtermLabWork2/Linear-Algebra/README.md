# Programming Assignment 1  
## 3×3 Matrix Determinant Solver

### Course
Math 101 — Linear Algebra

### Student
Frances Priceah S. Guzman

### School
University of Perpetual Help System DALTA  
Molino Campus

### Date
March 16, 2026

---

# Project Description

This program computes the **determinant of a 3×3 matrix** using **cofactor expansion along the first row**.

The matrix is hardcoded in the program and the solution is calculated step-by-step.

The program prints:

• The original matrix  
• Each **2×2 minor**  
• Each **cofactor term**  
• The **final determinant**

This makes the solution easy to follow and understand.

---

# Matrix Used
| 7, 2, 1 |
| 3, 5, 4 |
| 2, 1, 6 |

---

# Mathematical Method

The determinant is computed using:

det(A) = a11M11 − a12M12 + a13M13

where:

Mij = determinant of the 2×2 minor matrix.

---

# How to Run

Compile:
javac DeterminantSolver.java

Run:
java DeterminantSolver

DO NOT INLCUDE IN THE RUN COMMAND:
.java
.class

---

# Expected Output

The program prints all intermediate steps and the final result.

Final determinant:

DETERMINANT = 155

---

# Learning Objectives

This program demonstrates:

• Cofactor expansion  
• Minor computation  
• Determinant calculation  
• Java methods and arrays  
• Step-by-step algorithm tracing