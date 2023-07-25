@tag
Feature: Restaurant API Test

    @RestaurantList
    Scenario: Get All Restaurant
      Given url 'http://localhost:8080/rest/api/restaurant'
      When method GET
      Then status 200