##Good Code Description

1. Dependency Injection is implemented using spring framework's bean injection wherever is required.

2. BeanMapper i.e `DozerBeanMapper` is added to remove unnecessary redundant code of while mapping one class to another.

3. Lombok i.e. `@Data` is added to remove boiler-plate code in the model classes.

4. Customized Exception is thrown like `NotFoundException`. Error codes are maintained at one place. As it also helpful to make good communication between tester and developer or in case frontend and backend teams are different.

5. Here, exception is handled using aspect oriented programming from Spring by `@ControllerAdvice` annotation over `RestResponseEntityExceptionHandler` class.

6. Bean validation `@NotNull` is implement for input data. As well as different model classes are used to make desired changes in the input and output of RESTful api like InDTO and OutDTO where DTO stands for Data Transfer Object.

7. Code follows the Clean Code principle by Robert Martin.
   1. Followed naming convention for Class names and method names.
   2. No comments are flowing in the code as thruth lies in the code.
   3. No lines for maintaining  history of the class.
   
8. RESTful apis follow the resource naming conventions. http://www.restapitutorial.com/lessons/restfulresourcenaming.html 

9. Code follows the SOLID design principle. https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)
 
10. Code also follows KISS (Keep it silly simple) and DRY(Don't repeat yourself) design patterns. 

###Conclusion:
This code will surely work, as well as its also ready extension and close for modifications.