Feature: Hepsiburada test cases

  @smoke @addProduct
  Scenario: Adding products to the basket by logged user
    Given Open hepsiburada.com website and wait for load scripts
    And Open login page from user process menu
    And User is at the login page of the application
    And User logs in using email as "jefofon@promaild.com" and password as "test1234"
    And Verifies that the user is on the home page
    And Verifies that the user has logged in
    And Open shopping cart
    And Empty shopping cart
    And Search product "samsung galaxy note 10"
    And Open first product
    And Add items to cart from different seller
    And Check added products from shopping cart

  @smoke @addProduct
  Scenario: Adding products to the basket by not logged in user
    Given Open hepsiburada.com website and wait for load scripts
    And Search product "samsung galaxy note 10"
    And Open first product
    And Add items to cart from different seller
    And Check added products from shopping cart



