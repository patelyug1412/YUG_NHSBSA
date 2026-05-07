# YUG_NHSBSA
Functional Acceptance Automation Test Suite for NHS Jobs Search functionality using:
- Java 21
- Selenium WebDriver
- Cucumber BDD
- JUnit
- Maven
- Page Object Model (POM)

Repository: https://github.com/patelyug1412/YUG_NHSBSA.git

# Project Objective
This project automates the NHS Jobs Search functionality based on the provided user story and acceptance criteria.
The framework validates that a jobseeker can:
- Search jobs using preferences
- View matching search results
- Sort jobs by newest date posted
- Validate positive and negative search scenarios


# Technologies Used
| Technology         | Purpose                  |
|--------------------|--------------------------|
| Java 21            | Programming Language     |
| Selenium WebDriver | Browser Automation       |
| Cucumber           | BDD Framework            |
| JUnit              | Assertions & Test Runner |
| Maven              | Build Management         |
| Page Object Model  | Framework Design Pattern |


# Framework Design
The framework follows Page Object Model (POM) design pattern for:
- Reusability
- Maintainability
- Readability
- Separation of concerns

# Run tests on Selenium Grid Chrome
- In this project if you do not provide browser name, default browser is Chrome
- VNS Url = http://localhost:7900
- gridUrl = http://localhost:4444
- Terminal Command: mvn clean test -Dbrowser=chrome

# Run tests on Selenium Grid Firefox
- VNS Url = http://localhost:7901
- gridUrl = http://localhost:4444
- Terminal Command: mvn clean test -Dbrowser=firefox