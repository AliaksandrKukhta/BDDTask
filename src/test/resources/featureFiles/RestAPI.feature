Feature: Weather

  Scenario Outline: Check weather forecast for cities
    Given Request current weather for city "<city>"
    Then Response is <statusCode>
    Examples:
    | city   | statusCode|
    | Minsk  | 200       |
    | London | 200       |
    | Moscow | 200       |
    | Berlin | 200       |


  Scenario Outline: Check weather information for cities
    Given Request current weather for city "<city>"
    Then Information are "<language>", "<name>", "<region>", "<country>"
    Examples:
      | city   | language | name  | region                        | country       |
      | Minsk  | en       | Minsk | Minsk                         | Belarus       |
      | London | en       | London| City of London, Greater London| United Kingdom|
      | Moscow | en       | Moscow| Moscow City                   | Russia        |
      | Berlin | eng      | Berlin| Berlin                        | Germany       |


  Scenario Outline: Check weather negative scenario without Access key
    Given Request negative for city "<city>" without Access key
    Then Error Response is <statusCode>
    Examples:
      | city   | statusCode|
      | Minsk  | 101       |

  Scenario Outline: Check weather negative scenario with double query
    Given Request negative for city "<city>" with double query
    Then Error Response is <statusCode>
    Examples:
      | city   | statusCode|
      | Minsk  | 601       |
#
  Scenario Outline: Check weather negative scenario with bad query
    Given Request current weather for city "<city>"
    Then Error Response is <statusCode>
    Examples:
      | city             | statusCode|
      | MinskMinskMinsk  | 615       |

  Scenario Outline: Check weather negative scenario with bad URL
    Given Request negative for city "<city>" with bad URL
    Then Error Response is <statusCode>
    Examples:
      | city   | statusCode|
      | Minsk  | 105       |
