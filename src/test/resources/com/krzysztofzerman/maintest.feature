Feature: Interview test for Intrum

  Scenario Outline: POST person data and check status code
    Given provide data for request: "<id>", "<firstName>", "<lastName>", "<email>", "<companyId>"
    When send data with POST method to "/v1/contact"
    Then assert response for status code 201


# @csvFile=src/test/resources/com/krzysztofzerman/examples.csv
    Examples:
|id|firstName|lastName|email|companyId|
|1|John|Doe|johndoe@intrum.com|123|
|2|Will|Smith|will@test.com|321|
|3|Krzysztof|Zerman|kz@test.pl|6677|
|4|Meryl|Streep|meryll@streep.io|92|
|5|Agnieszka|Holand|ahhh@zaapfjdgspo.com|1|
