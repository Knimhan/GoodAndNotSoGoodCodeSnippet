##Bad Code Description

1. Entire code is dump into one file. i.e ApplicationCrud.java

2. Naming convention not followed for methods and classes.
   For example:
   1. ApplicationCrud (It should be strictly noun form)
   2. findAllApplications (It should be strictly verb form i.e findAll make sense) as we are repeating Application whenever we call below method.
          
            applicationCrud.findAllApplications();

3. Code is not following S.O.L.I.D design principle. https://en.wikipedia.org/wiki/SOLID_(object-oriented_design)
    1. Java packages can be maintain
    2. Code through `Interface` can be added, to make it ready for extension and close for modifications
    2. Divide code to the respective classes for single responsibility.
    3. Alot can be implemented with Clean Code

4. Unnecessary comments used in the code.

5. Dependency Injection is not used in the code. private fields are instanciated using constructor.            

6. One by one field is mapping from one class to another. We can use some kind of bean mapper.(for more details, see the good-code example)

7. REST api endpoints are not following naming conventions derived by RoyFielding.
   For example:http://www.restapitutorial.com/lessons/restfulresourcenaming.html
   
8. Unnecessary history maintain on the top of class declaration.
    
        /*
        @Creation Date: 15.10.2018
        @Author Kumu
        @Modified by: Sama @Date: 18.10.2017 @Changes ....bla bla
        @Modified by Myra @Date: 20.10.2017  @Changes ....bla bla
        @Modified by Oliver @Date: 22.10.2017 @changes ....bla bla
         */
         
     We have various version control tools like Git, SVN where history can be maintained.

9. No user-defined runtime exception is thrown from the business logic. 

10. Its always good to have Exception which consists standard message like error codes and message.


###Conclusion:
This code will surely work, but its not maintainable.