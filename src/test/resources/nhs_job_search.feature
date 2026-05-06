Feature: NHS Jobs Search

  As a job seeker on the NHS Jobs website
  I want to search for jobs with my preferences
  So that I can get recently posted job results

  Background:
    Given I am on the NHS Jobs search page "https://www.jobs.nhs.uk/candidate/search"

  @positive
  Scenario Outline: Search jobs using multiple preferences
    When I search for jobs with position "<position>" and location "<location>"
    And I select distance "<distance>"
    And I click on more search options button
    And I select pay range "<payRange>"
    And I click on search button
    Then I should see jobs found for "<position>" in "<location>"
    And I sort the results by "Date Posted (newest)"

    Examples:
      | position   | location   | distance | payRange |
      | Nurse      | London     | 5        | 30-40    |
      | Healthcare | Manchester | 10       | 20-30    |
      | Admin      | Birmingham | 50       | 20-30    |
      | Dentist    | London     | 100      | 50-60    |

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
    Then I should see a message for invalid position"No result found"

  @negative
  Scenario: Search with invalid location
    When I search for jobs with position "Senior Nurse" and invalid location "-----"
    And I click on search button
    Then I should see a message for invalid location "Location not found"



