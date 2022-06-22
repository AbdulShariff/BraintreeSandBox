@add_paymentMethod
Feature: Void Transaction

  Scenario Outline: Void Transaction Success Flow

    Given voidTransaction request
    And set "<transaction_id>" as transaction id
    When voidTransaction method is called
    Then "<message>" returned as response message




    Examples:
      | transaction_id | message                          |
      | 5aak39y8       | the transaction has been voided  |

