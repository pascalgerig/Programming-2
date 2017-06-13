# Exercise 4

The fourth lecture covers *Test Driven Development* (TDD). In TDD, test cases 
are written in order to check whether a component behaves as expected. Good 
tests cover as much code as possible and use diverse method inputs. Complex 
methods should have multiple tests in order to cover all paths, and one should 
think of corner cases and special circumstances in which a method could be 
executed. In this exercise, we will revisit the Snakes & Ladders game and 
practice writing good tests using JUnit and Mockito.


## Mocking Frameworks

For this exercise, you will need to use the [Mockito](http://mockito.org/) 
framework for mocking objects. A quick introduction is given in the exercise 
session slides, but you should check the project website to familiarize 
yourself with the framework. In particular, take a look at [the 
documentation](http://mockito.github.io/mockito/docs/current/org/mockito/Mockito.html) 
to find out how you can use mock objects to your advantage.


## Code coverage

You have seen different qualitative criteria that make up good tests. For 
example, you should keep edge cases in mind when writing tests, and you should 
test one thing at a time, using few assertions per test method.

One quantitative criterion is called *test coverage*. Test coverage measures 
how much code is reached by your tests. For example, this measurement tells 
you whether your tests can reach both branches in an `if then else` construct. 
Of course, it is desirable to cover as much code with the tests as possible. A 
lack of coverage may indicate that you do not use certain kinds of inputs in 
your tests.

To help you determine the coverage of your tests, there are many tools that 
trace which parts of the source code are reached by the tests. One such tool 
that integrates into Eclipse is called [EclEmma](http://eclemma.org/). You can 
install this plugin by selecting the *Help* menu in Eclipse and choosing 
*Eclipse Marketplace...*. In the search field, write "EclEmma" and you will 
see an entry for the plugin which you can then choose to install.

You should use this or any other code coverage tool during this exercise in 
order to make sure that your tests sufficiently cover your code. We will use 
EclEmma during the corrections to check how well your code is covered. Note 
that you are not required to cover 100% of your code. Your focus should still 
be on the qualitative criteria, but a coverage tool can and will help you come 
up with better tests. An example of code that you probably do not want to test 
is a trivial getter method (e.g. `int getDestination()` which simply returns 
the field `int destination`).


## Problem Description

Your task this week is to improve the code of your Snakes & Ladders and add 
tests. In order to pass this exercise, you must do the tasks described in the 
following two sections (*Testing Snakes & Ladders* and *Covering the code*).


### Testing Snakes & Ladders

You are given an implementation of the Snakes & Ladders game and have to use 
this one, **not** your own implementation of exercise 2. Unfortunately, this 
implementation is almost untested. Only the test from the exercise session 
slides is there. Your job of course is to change this situation.

`Game#play(Die)` is not properly covered by unit tests. To test this method, 
you must write a test that mocks the `Die` class. In order to do that, you 
should first use Eclipse's *extract interface* feature (right click on the 
class `Die`, then choose *Refactor*, and finally choose *extract 
interface...*) to create an interface `IDie` with the method `roll()`. Make 
sure that `Game#play(IDie)` now takes an `IDie` as an argument. Next, write 
unit tests that cover the `Game#play(IDie)` method. You must implement two 
different types of tests that cover `Game#play(IDie)` by using two different 
approaches to mocking as follows:

- In the first variant, you must mock an `IDie` object using Mockito. This 
  way, you can specify the behaviour of some die and do not have to rely on a 
  correct implementation of some die.
- Another approach to mocking is creating explicit mock objects without 
  relying on a framework or library. To do that, you should create a class 
  `MockDie` that implements `IDie`. In this `MockDie`, you should implement 
  the die behaviour as you need it for your test (you are of course free to do 
  whatever you want in `MockDie`).

In the exercise_04 folder of your group repository, create a new text file 
(.txt, .md, etc.) and write a short paragraph about these two approaches to 
mocking. Tell us which approach is better in your opinion and why.

Note that you are not allowed to copy the `DieTest` from exercise 2 or the 
files available on the course site.

**You should also write tests for all squares in your game**. Again, you 
should use Mockito to mock the objects that are unrelated to the current test. 
Take a look at the exercise session slides for an example on how to use 
mocking properly in these cases. Make sure that your tests cover various 
scenarios. A single test per square is not enough.

Example scenarios you should test are the following: Does the game work 
correctly when there are `WormholeEntrance` squares, but no exits? Or is the 
`SwapSquare` behaving correctly in games with more than two players? When you 
specify the expected behaviour (which is your decision), you need to make sure 
that the tests all pass. If the program does not behave as specified in your 
tests, change the program.


### Covering the code

Use a code coverage tool such as EclEmma to find out which parts of your code 
are covered by your tests. You do not need to achieve 100% coverage (see 
comment in section *Code coverage*). However, if there are parts of your games 
that are not covered, you should write a short statement explaining why you 
don't need to cover these particular parts. When you check coverage by running 
a single test class, the target class should be covered (for example, your 
`LadderSquareTest` should cover the code of the `LadderSquare`).

If you are using IntelliJ IDEA instead of Eclipse, you can use the included 
coverage facility or another external tool. We will use EclEmma to check your 
solutions.


### Notes

- The focus in this exercise is on unit tests that test an individual method. 
  Nevertheless, if you feel that it helps with your work, you are free to use 
  tests similar to what we have used in exercise 2 to test the behaviour of 
  the whole system. This does not free you from testing the die and squares 
  with mocking techniques.

- The provided implementation may contain bugs. If you encounter any, you 
  should first double-check whether your test assertions express how you 
  expect the program to behave. Then, fix the bug in the implementation. 
  Changing the tests to make them green is not TDD!


## Deadline

Submit your solutions by pushing your code to the git repository by
___Thursday, 23 March, 13:00___.
