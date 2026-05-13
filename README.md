# YUG_NHSBSA

## NHS Jobs Search Automation Project
This project is a Functional Acceptance Automation Test Suite for the NHS Jobs search functionality.
The framework is developed using:
- Java 21
- Selenium WebDriver
- Cucumber BDD
- JUnit
- Maven
- Docker
- Selenium Grid
- Allure Report

## Application Under Test
https://www.jobs.nhs.uk/candidate/search

---
# User Story
As a job seeker on the NHS Jobs website,  
I want to search for jobs with my preferences,  
So that I can get recently posted job results.

---

# Features Covered
- Search jobs using job title and location
- Search jobs using distance filter
- Search jobs using pay range filter
- Sort search results by newest date posted
- Validate job search results based on user preferences
- Validate search with invalid job title
- Validate search with invalid location
- Validate “No results found” message
- Filter jobs by Contract Type
- Filter jobs by Working Pattern
- Validate jobs matching contract type or working pattern filters
- validate clear filter option 

---

# Framework Design
The framework follows:
- BDD approach using Cucumber
- Page Object Model (POM)
- Reusable step definitions
- Hooks for browser setup and teardown
- Cross-browser execution
- Screenshot capture for failed scenarios
- Selenium Grid execution using Docker

---
## Project Structure

```text
YUG_NHSBSA
├── src
│   └── test
│       ├── java
│       │   ├── Utility
│       │   │   └── Utility/helper classes for browser setup, configuration, waits and reusable methods
│       │   ├── pageObject
│       │   │   └── Page Object Model classes for NHS Jobs search pages and web elements
│       │   ├── runner
│       │   │   └── Cucumber/JUnit test runner classes for executing feature files
│       │   └── stepDefinition
│       │       └── Step definition classes mapping Gherkin steps to Selenium actions
│       │
│       └── resources
│           ├── nhs_job_search.feature
│           │   └── BDD feature file covering NHS Jobs search scenarios
│           ├── config.properties
│           │   └── Browser, URL and execution configuration
│           ├── allure.properties
│           │   └── Allure report configuration
│           └── logback.xml
│               └── Logging configuration
│
├── docker-compose.yml
│   └── Selenium Grid setup for Chrome and Firefox execution
│
├── pom.xml
│   └── Maven dependencies and build configuration
│
├── .gitignore
│   └── Files/folders excluded from Git tracking
│
└── README.md
    └── Project documentation, setup steps and execution guide
---

# Tech Stack
- Java 21
- Selenium WebDriver
- Cucumber
- JUnit
- Maven
- Docker
- Selenium Grid
- Allure Report
- GitHub

---

# Steps to Run the Project
## 1. Clone the Repository
```bash
git clone https://github.com/patelyug1412/YUG_NHSBSA.git
```
---
## 2. Open Project Folder
```bash
cd YUG_NHSBSA
```
---
## 3. Start Selenium Grid using Docker Compose
```bash
docker-compose up -d
```
- This command will automatically start:
- Selenium Chrome container
- Selenium Firefox container
---
### Live Execution URLs
- Chrome Live View:
```bash
http://localhost:7900
```
- Firefox Live View:
```bash
http://localhost:7901
```
### Selenium Grid URLs
Chrome Grid:
```bash
http://localhost:4444
```
Firefox Grid:
```bash
http://localhost:4445
```
## 4. Run Tests in Chrome
```bash
mvn clean test -Dbrowser=chrome
```
---
## 5. Run Tests in Firefox
```bash
mvn clean test -Dbrowser=firefox
```
---
## 6. Stop Docker Containers
```bash
docker-compose down
```

# Reports
- After test execution, the following reports are generated:
- 
### Cucumber HTML Report
- Location:
```bash
target/cucumber-report.html
```
- Open the file in browser to view the execution summary.

---

### Allure Report

- Allure result files are generated inside:
```bash
allure-results
```
- To generate and open the Allure Report:
```bash
allure serve allure-results
```

- The Allure Report includes:
- Passed/Failed scenarios
- Step execution details
- Execution timeline
- Failure screenshots
- Error logs
