@add_paymentMethod
Feature: Add paymentMethod

  Scenario Outline: Add paymentMethod Success Flow

    Given addMethodOfPayment  request
    And set "<customer_id>" as customer id
    And set "<nonce>" as nonce
    When add payment method api is called
    Then "<type>" returned as payment type
    And "<message>" returned as message
    And "<code>" returned as code

    Examples:
      | customer_id | nonce                 | type                                  | message | code |
      | 262629438   | fake-valid-visa-nonce | class com.braintreegateway.CreditCard | Ok      | 200  |

