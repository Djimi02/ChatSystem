# ChatSystem using Spting Boot
Here I present a REST API that represents a chat system consisting of users, chats and messages.
In short, the API is build using Java, Spring Boot 3, Spring Data JPA, PostgreSQL, Spring Security, JWT tokens for authorization.

### Models and Entity Relationship Diagram
User has: 
* Id
* Name
* Email
* Password
* Role (User/Admin)
* Chats

Chat has:
* Id
* Name
* Messages
* Users

Message has:
* Id
* Text
* Chat
* Creator(User)

![alt text](https://github.com/Djimi02/ChatSystem/blob/main/Images/ChatSystemERD.png)

There is Many-To-Many relationship between User and Chat entities.
There is Many-To-One relationship between Message and User entities.
There is Many-To-One relationship between Message and Chat entities.

### Security
Authentication is implemented using the DaoAuthenticationProvider, UserDetailsService and UserDetails.

Authorization is implemented using JWT tokens. Upon login or registration, the user is given a fresh JWT token that is valid for 24 minutes.

### Endpoints
The endpoints /auth/signup and /auth/signin are responsible for registering and signing in a user to the chat system. No user authentication is needed for them.

The endpoint /user/all returns all the users available in the database. User uthentication and role Admin is required.

The endpoint /user/profile is where the authenticated user information is returned. User authentication is required.

The endpoints /chat/save, /chat/join, /chat/leave, /chat/update, /chat/addmessage are responsible for the interaction with Chat objects. User authentication is required.

Finally, the endpoint /about is where some simple information is returned. No user authentication is required.

### Exception Handling
Exceptions are handled via the class RestControllerExceptionHandler annotated with @ControllerAdvice. Upon exception in one of the controller classes, a ResponseEntity object is created, with the appropriate error code and message, and returned.

### Testing
Last but not least, several classes testing the User, Chat, Message repositories and services are implemented using JUnit.