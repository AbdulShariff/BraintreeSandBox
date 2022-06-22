@capture_transaction
Feature: Capture Charge Success Flow

  Scenario Outline: Capture Charge Success Flow

    Given a capture transaction request
    And set "<transaction_id>" as transaction_id
    When capture api is called
    Then "<transaction_message>" returned as  response transaction_message must match
    And "<status>" returned as  response status must match

    Examples:
      | transaction_id | transaction_message | status                   |
      | dx0qhqmh       | Ok                  | SUBMITTED_FOR_SETTLEMENT |