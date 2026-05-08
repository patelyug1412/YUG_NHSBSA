Feature: NHS Jobs Search

  As a job seeker on the NHS Jobs website
  I want to search for jobs with my preferences
  So that I can get recently posted job results

  Background:
    Given I am on the NHS Jobs search page "https://www.jobs.nhs.uk/candidate/search"
    And Page with title "Search for jobs in the NHS" should be opened
    And I click on accept the cookies

  @positive
  Scenario Outline: Search jobs using multiple preferences
    When I search for jobs with position "<position>" and location "<location>"
    And I select distance "<distance>"
    And I click on more search options button
    And I select pay range "<payRange>"
    And I click on search button
    Then I sort the results by "Date Posted (newest)"
    And I should see jobs found for "<position>" in "<location>"
    And I should see results sorted by newest date posted

    Examples:
      | position   | location   | distance | payRange |
      | Nurse      | London     | 5        | 30-40    |
      | Healthcare | Manchester | 10       | 20-30    |
      | Admin      | Birmingham | 50       | 20-30    |
      | Dentist    | London     | 100      | 50-60    |

    @empty_search
  Scenario Outline: Search jobs with different input combinations
    When I search for jobs with position "<position>" and location "<location>"
    And I click on search button
    Then I should see message "<result>"

    Examples:
      | position | location | result               |
      | Nurse    |          | jobs found for Nurse |
      |          | London   | jobs found in London |
      | @@@###   | &&&$$$   | No result found      |
      |          |          | jobs found           |

  @negative
  Scenario: Search with invalid position
    When I search for jobs with invalid position "-----" and location "Peterborough"
    And I click on search button
    Then I should see a message for invalid position "No result found"

  @negative
  Scenario: Search with invalid location
    When I search for jobs with position "Senior Nurse" and invalid location "-----"
    And I click on search button
    Then I should see a message for invalid location "Location not found"

  @clearFilters
  Scenario: Clear selected search filters
    When I search for jobs with position "Nurse" and location "London"
    And I select distance "5"
    And I clear all selected filters
    Then the search filters should be cleared

  @pagination
  Scenario: Navigate to next and previous search result pages
    When I search for jobs with position "Nurse" and location "London"
    And I click on search button
    Then I sort the results by "Date Posted (newest)"
    And I should store the set of jobs for first page
    When I go to the next page of results
    Then I should store the set of jobs for second page
    And First and second page jobs should be different
    When I go back to the previous page of results
    Then I should store the set of jobs for first page again
    And Current and first page job should be same

  @jobDetails
  Scenario: Open first job and verify apply button
    When I search for jobs with position "HealthCare" and location "London"
    And I click on search button
    Then I sort the results by "Date Posted (newest)"
    When I click on the first job result
    Then I should be redirected to the job details page
    And The apply button should be visible