# Exercise 10

This week's tasks consists in writing a small book shop manager application 
and applying what you have learned about design patterns.


## Book Store Manager

Your tasks is to implement a simple application that can help owners of book 
stores to manage their books, clients, and orders. The clients, identified by 
name and address, can place book orders. They can order individual books (with 
one or more copies) or a group of books. Such a group is identified by a name 
and can contain individual books or other groups. An example is a group "P2 
reading", containing the books "Design Patterns" and "Pharo by Example", as 
well as the subgroup "P1" (which itself contains the books relevant to P1).

Another example is the following. A user can order a batch of books named 
"Main order" that contains three other batches of books: "Software Books", 
"Cooking Books" and "Travel Books". The "Travel Books" batch can further 
contain two other batches of books labeled "Europe" and "Asia".

A book has the following information:
  - title,
  - author(s),
  - publisher,
  - publication date,
  - number of pages,
  - category, and
  - price.

By default the price of an order is computed by summing up the prices of all 
individual books from that order. However, to attract clients different types 
of discounts can be applied to an order:

- Buying 10 or more books results in a 10% discount.
- Buying books worth more than 1000 CHF results in a 90 CHF discount.
- Buying 5 or more books from the category "Software Engineering" results in a 
  5% discount.
- Orders made during the month of May have a 5% discount.
- Other types of discounts will be added in the future.

More than one discount can be applied to an order, however discounts should be 
applied in the order in which they are presented above. Thus, if a client 
makes in March an order of 11 books from the category "Software Engineering" 
amounting to 1010 CHF he gets the first and the third discount. He does not 
get the second discount, because after getting the first discount his order 
values only 909 CHF. The third discount (for ordering 5 or more "Software 
Engineering" books) is computed using the current value of the order which is 
909 CHF, and amounts to 45.45 CHF. Thus, in the end the client has to pay 
863.55 CHF.

The application should further be able to print a full description and a 
summary description of an order.

The summary description contains the final price of the order after applying 
all the discounts plus a list with the title, the author(s) and the price of 
all individual books. For example if a client makes an order for a batch named 
"Software" containing two other batches named "OOP" and "Functional 
Programming", each with two books, then the summary will look as follows.

```
Date: March 12, 2014
Client: Anonymous
Number of books: 5
Final Price: 264.385

Books

1. Title: Design patterns : elements of reusable object-oriented software
   Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides
   Price: 40.2CHF
   Quantity: 2
2. Title: Clean Code: A Handbook of Agile Software Craftsmanship
   Author(s): Robert C. Martin
   Price: 50CHF
   Quantity: 1
3. Title: Introduction Functional Programming
   Author(s): Richard Bird, Philip Wadler
   Price: 112.74CHF
   Quantity: 1
4. Title: Real-World Functional Programming
   Author(s): Tomas Petricek, Jon Skeet
   Price: 35.16CHF
   Quantity: 1
```

The detailed description should include the total cost of the order plus the 
discount. It should also print a detailed description of the ordered books, 
including information about how books are grouped in batches. For the previous 
order, the detailed description should look as follows.
```
Date: March 12, 2014
Client: Anonymous
Number of books: 5
Total Price: 278.3
Discount: 13.915
Final Price: 264.385

Batch 'Software'

    Batch 'OOP'

        Title: Design patterns : elements of reusable object-oriented software
        Author(s): Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides
        Publisher:  Addison Wesley
        Publication Date: 31 October 1994
        Number of pages: 416
        Category: Software Engineering
        Price: 40.2CHF
        Quantity: 2

        Title: Clean Code: A Handbook of Agile Software Craftsmanship
        Author(s): Robert C. Martin
        Publisher: Prentice Hall
        Publication Date: 1 August 2008
        Number of pages: 464
        Category: Software Engineering
        Price: 50CHF
        Quantity: 1

    Batch 'Functional Programming'

        Title: Introduction Functional Programming
        Author(s): Richard Bird, Philip Wadler
        Publisher: Prentice Hall
        Publication Date: 29 April 1998
        Number of pages: 448
        Category: Software Engineering
        Price: 112.74CHF
        Quantity: 1

        Title: Real-World Functional Programming
        Author(s): Tomas Petricek, Jon Skeet
        Publisher: Manning Publications
        Publication Date: 25 January 2010
        Number of pages: 500
        Category: Software Engineering
        Price: 35.16CHF
        Quantity: 1
```

Your task is to implement the book store application (see description below). 
A very light skeleton is provided to help you get started, but you are free to 
change everything you want. You should include appropriate unit and 
integration tests, and you should adhere to the principles that are covered so 
far and include them appropriately (design by contract, JavaDoc, 
object-oriented design principles, ...).

You must use at least 3 of the following design patterns in your solution:

  - Composite (e.g., to model books and batches of books).

  - Visitor (e.g., to add the functionality for printing the summary and the 
    detailed view to books and batches of books).

  - Chain of Responsibility (e.g., for computing the discount of an order).

  - Template Method (e.g., to implement the algorithm for printing the summary 
    and the detailed view).

  - Null Object.

**Note:** You have access to the Gang of Four book 
[here](http://scgresources.unibe.ch/~scg/Literature/Books/GOF/contfso.htm), 
which defined the pattern vocabulary. However as time moves on, there were 
more commonly used pattern discovered. Wikipedia has an extensive list 
[here](https://en.wikipedia.org/wiki/Design_pattern_(computer_science)

## Tasks

Create a UML class diagram showing the design of the book store application. 
Make sure to include the patterns stated above in your design. If you find it 
relevant, you can add other design patterns too. Sketch the UML Class diagram 
using a pencil before starting the implementation. It is ok if the final 
design will be different than the one from the diagram. Take a picture of or 
scan the handwritten diagram and add it to your repository.

For the second task you have to implement the application. In your 
implementation you must use at least three of the patterns presented above. It 
should also follow, as much as possible, the UML Class diagram. However, if 
you realise during the implementation that some parts could be better 
implemented in another way, you should do that. Then add a comment in the code 
explaining why you choose to do the change (what are its advantages over your 
previous solution).

**You do *not* have to implement the functionality for adding books or placing 
orders through the command line, or through a user interface.**

Instead, to verify that the application works, write tests that exercise the 
main features. You solution should contain, at least, tests that

- check the addition of books to a shop,
- check the construction of an order,
- verify that the number of available copies of a book is updated correctly 
  after an order,
- verify that the discount of an order is computed correctly, and
- verify that the summary and the detailed descriptions are printed correctly.

**Note that when you implement this, you do not have to have the exact same 
format as above. However, your summary and detailed printed orders should 
contain all the information as in the examples above.**


## Deadline

Submit your solutions by pushing your code to the git repository by 
___Thursday, 25 May, 13:00___.
