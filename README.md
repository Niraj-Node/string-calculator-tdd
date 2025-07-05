# 🧮 String Calculator - TDD Kata in Java

A clean and extensible implementation of the classic **String Calculator Kata**, developed using **Test-Driven Development (TDD)** principles.
The implementation follows the kata requirements, incrementally adding support for basic sums, custom delimiters, negative number validation, and more.

The purpose of this exercise is to demonstrate software craftsmanship by writing clean, well-tested code using TDD principles.

---

## ✅ Features Implemented

This calculator supports the following features step-by-step as per the original kata specification:

1. **Basic addition**:
   - Returns `0` for empty input
   - Returns the number itself if only one number is passed
   - Adds two or more comma-separated numbers

2. **Support for new line delimiters**:
   - Example: `"1\n2,3"` returns `6`
   - Invalid inputs like `"1,\n"` are **not** handled (as per kata)

3. **Custom delimiters**:
   - Single character: `"//;\n1;2"` returns `3`
   - Any-length delimiters: `"//[***]\n1***2***3"` returns `6`
   - Multiple delimiters: `"//[*][%]\n1*2%3"` returns `6`
   - Multiple any-length delimiters: `"//[**][%%]\n1**2%%3"` returns `6`

4. **Negative number handling**:
   * Throws `IllegalArgumentException("negatives not allowed: -1")`
   * If multiple negatives are found:
     → `IllegalArgumentException("negatives not allowed: -1, -3")`
   
5. **Ignore numbers larger than 1000**:
   - `"2,1001"` returns `2`

6. **Track method calls**:
   - `getCalledCount()` returns how many times the `add()` method was invoked

7. **Event-style callback support** *(Java adaptation of .NET feature)*:
   * Instead of .NET’s `event Action<string, int>`, Java uses:
     `setAddListener(AddListener listener)` with:

     ```java
     @FunctionalInterface
     public interface AddListener {
         void onAdd(String input, int result);
     }
     ```
   * Triggers callback after every successful `add()` call:

     ```java
     calculator.setAddListener((input, result) ->
         System.out.println("Input: " + input + ", Result: " + result));
     ```
---

## 📁 Project Structure

```

src/
├── main/
│   └── java/
│       └── com/
│           └── incubyte/
│               ├── StringCalculator.java
│               └── listeners/
│                   └── AddListener.java
└── test/
    └── java/
        └── com/
            └── incubyte/
                └── StringCalculatorTest.java

````

---

## 📋 Requirements

This project is built using:

* **Java 21**
* **Maven** for build and dependency management
* An IDE like **IntelliJ IDEA** or **VS Code** is recommended

---

## 🛠️ Setup and Run

### 🔧 Step 1: Clone the Project

```bash
git clone https://github.com/Niraj-Node/string-calculator-tdd.git
cd string-calculator-tdd
```

### 📦 Step 2: Build and Run Tests

### Run with Maven:
```bash
  mvn clean test
````

### Run in IntelliJ IDEA:
* Right-click on `StringCalculatorTest.java` → Run Tests

You should see a test summary like:

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.incubyte.StringCalculatorTest
[INFO] Tests run: 16, Failures: 0, Skipped: 0
[INFO] -------------------------------------------------------
[INFO] BUILD SUCCESS
```

---

## 📸 TDD Proof (Red → Green Screenshots)

This project follows **strict Test-Driven Development (TDD)** practices.
Every feature was implemented by:

1. Writing a failing test (Red)
2. Writing minimum code to make it pass (Green)
3. Refactoring for clarity and quality

All test execution stages (fail → pass) are documented in screenshots under [`/tdd-proof`](./tdd-proof) folder.

### Screenshot Samples:

| Test Case                 | Before                                                                     | After                                                                        |
| ------------------------- |----------------------------------------------------------------------------|------------------------------------------------------------------------------|
| Negative number exception | ![](./tdd-proof/8(red)-shouldThrowExceptionWhenSingleNegativeIsPassed.png) | ![](./tdd-proof/8(green)-shouldThrowExceptionWhenSingleNegativeIsPassed.png) |
| Multi-delimiter           | ![](./tdd-proof/15(red)-shouldSupportMultipleCustomDelimiters.png)         | ![](./tdd-proof/15(green)-shouldSupportMultipleCustomDelimiters.png)         |

---

## 📚 Learning Objectives

* Apply **Red-Green-Refactor** cycle properly
* Practice writing **unit tests first**
* Learn **incremental feature development**
* Write code that is **readable, modular, and testable**

---