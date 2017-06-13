When mocking objects, you generally have two possible options - you can either use one of the existing mocking libraries, or you can write a custom mockup of your class.

Writing a custom mockup will require you to write a class which e.g. implements the required interfaces of the to-be-mocked clcass. Then you can replace its various methods with stripped-down versions, which are suitable for your test. E.g. if you mock an API client, you might make it return the same response all the time, instead of doing any actual API calls.

Writing a custom mock class has the advantage of easily allowing to have complex logic within the mocked classes' methods, as you have all the power of Java at your disposal. Whereas mocking libraries often limit what you can do with a mock.

Personally I try to stay clear from custom mocks, however, as having complex mocks with heavy logic is usually a code smell - a sign that your class does too much, and should be split into several smaller - inherently easier to mock - classes.
