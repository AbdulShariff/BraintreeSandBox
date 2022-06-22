@add_customer
Feature: Add Customer Success Flow

  Scenario Outline: Add Customer Success Flow

    Given an add customer request
    And set "<first_name>" as first name
    And set "<last_name>" as last name
    And set "<phone>" as phone
    And set "<email>" as email
    When instrument onboard api is called
    Then a non-empty customer_id should be generated
    Then "<message>" returned as  response message must match
    And "<code>" returned as response code
    And "<first_name>" returned as first name
    And "<last_name>" returned as last name

    Examples:
      | first_name  | last_name | phone      | email           | message | code |
      | Arjun       | Singh     | 7878987509 | Arjun@gmail.com | Ok      | 200  |


