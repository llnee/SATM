Feature: ATM

  Scenario Outline: View account balance
    Given I have <balance> in my account
    When I perform a balance inquiry
    Then I should have <account_balance>
    Examples:
      | balance | account_balance |
      | 100.00  | 100.00          |
      | 0.63    | 0.63            |

  Scenario Outline: Make a deposit
    Given I have <balance> in my account
    When I make a deposit of <dp_amount>
    Then I should have <account_balance>
    Examples:
      | balance | dp_amount | account_balance |
      | 100.00  | 100.00    | 200.00          |
      | 1.63    | 0.00      | 1.63            |
      | 0.00    | 5.99      | 5.99            |
      | 2.55    | 99.32     | 101.87          |

  Scenario Outline: Request to withdrawal
    Given I have <balance> in my account
    And The ATM has <currency> stored
    When I request to withdrawal <wd_amount>
    Then I should have <account_balance>
    And The ATM should have <updated_currency> leftover
    Examples:
      | balance | currency | wd_amount | account_balance | updated_currency |
      | 100.00  | 100.00   | 100.00    | 0.00            | 0.00             |
      | 1.63    | 100.00   | 0.00      | 1.63            | 100.00           |
      | 99.32   | 100.00   | 30.00     | 69.32           | 70.0             |

