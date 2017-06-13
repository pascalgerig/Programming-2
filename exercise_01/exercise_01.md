# Exercise 1

In this warmup exercise, we implement a simple program and establish a 
workflow for future exercises, using git, Java, and JUnit.


## Getting started with Git

If you have not done so already, follow this tutorial to learn the very basics 
about git:
[Got 15 minutes and want to learn 
Git?](https://try.github.io/levels/1/challenges/1)

## Problem Description

*Glob patterns* are strings used to specify a set of filenames. They contain 
regular characters that are literally matched, as well as the *wildcard* 
characters `*` (matching any number of characters) and `?` (matching exactly 
one arbitrary character). Our goal is to implement an algorithm that checks 
whether a pattern matches a filename.

In order to pass this exercise, you must fulfill the following requirements:

- Implement the missing methods in class `FilePattern`.

- Pass all tests in `FilePatternTest`. Note that your solution must be 
  generic; you are not allowed to handle each test case individually in your 
  implementation.

- Write proper git commit messages. Read the [git 
  documentation](http://git-scm.com/book/ch5-2.html#Commit-Guidelines) for a 
  description of "best practices". This page also includes more general 
  descriptions for working with git.


## Hints

- You might want to implement additional tests to verify the handling of other 
  kinds of filenames not covered in `FilePatternTest`!

- There exist two main approaches: You can implement the pattern matching 
  algorithm yourself, or use the regular expression library (classes in the 
  `java.util.regex` package). If you opt for the latter, you need to pay 
  attention to special characters in regular expression patterns (for example, 
  the character `[` has a special meaning inside a regular expression 
  pattern).

- Either way, we suggest to implement your own matching algorithm: It is a 
  nice exercise and does not require knowledge of the regular expressions 
  classes.


## Mandatory Reading Material

- [Got 15 minutes and want to learn 
  Git?](https://try.github.io/levels/1/challenges/1)

  This is a very nice tutorial if you never used git before.


- [Git Commit Guidelines](http://git-scm.com/book/ch5-2.html#Commit-Guidelines)

  Note that these guidelines do not have to be implemented too strictly. For 
  example, you may want to prefer writing your commits in past tense (e.g., 
  "Fixed type" instead of "Fix typo"); it is more important to be consistent 
  in your commit history.


## Optional Reading Material

- [The Git Book](https://git-scm.com/book/en/v2)

  You do not need to read this book completely. Use it as a reference and read 
  sections you are interested in.


- [What is a versioning control 
  system?](http://www.git-tower.com/learn/ebook/command-line/basics/what-is-version-control)

  This book/course covers git in great detail. It is a longer read and it is 
  certainly not necessary to read it front to back. However, we still 
  recommend having a look at it and using it to understand individual concepts 
  better.


- [An introduction to version 
  control](http://guides.beanstalkapp.com/version-control/intro-to-version-control.html)

  Another short overview of git.


- [Become a git guru](https://www.atlassian.com/git/tutorials/) 

  Here you can find many git tutorials, the sections Beginner, Getting 
  Started, and Collaborating are particularly useful.


- [A Successful Git Branching 
  Model](http://nvie.com/posts/a-successful-git-branching-model/)

  This is a description of a possible workflow with git that includes 
  branching. Note that you are not forced by git to use a particular workflow, 
  this is just one way to keep the repositories clean and easy to understand.


## Submitting the solution

We will grade the last commit you publish using `git push origin master` 
before the deadline. To check if you have successfully uploaded your latest 
solution, simply ask your colleague to pull the repository using `git pull 
origin master` and see if the correct version of your code appears in his IDE.


## Deadline

Submit your solutions by pushing your code to the git repository by
___Friday, 4 March, 13:00___.
