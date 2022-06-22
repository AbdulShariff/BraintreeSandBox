@add_paymentMethod
Feature: Refund Transaction

  Scenario Outline: Refund Transaction Success Flow

    Given RefundTransaction request
    And set "<transaction_id>" as the transaction id
    When refundTransaction method is then called
    Then "<message>" returned as the response message

    Examples:
      | transaction_id | message                                  |
      | 5aak39y8       | The given transaction has been refunded  |

