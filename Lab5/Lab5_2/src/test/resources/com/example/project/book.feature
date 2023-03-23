Feature: Book search
  To allow a customer to find his favourite books quickly, the library must offer multiple ways to search for a book.
 
  Scenario: Search books by publication year
    Given a book with the title 'One good book', written by 'Anonymous', published in 2013-03-14
      And another book with the title 'Some other book', written by 'Tim Tomson', published in 2014-08-23
      And another book with the title 'How to cook a dino', written by 'Fred Flintstone', published in 2012-01-01
    When the customer searches for books published between 2013-01-01 and 2014-12-31
    Then 2 books should have been found
      And Book 1 should have the title 'Some other book'
      And Book 2 should have the title 'One good book'