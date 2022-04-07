# MYEDU
### This is simple Spring Boot project, the idea was to build basic back-end for course management web app.
### It was made using Spring Boot, Spring Security, Spring Data JPA, RESTful API. Database is MySQL.

## API

### ALL ROLE
| Method | API            | Response Body                                             | Description     |
|--------|----------------|-----------------------------------------------------------|-----------------|
| POST   | auth/signin    | {id, username, roleName}                                  | login           |
| POST	  | auth/signout/	 | 	{message}                                                | logout          |
| POST	  | auth/signup/	  | 	{message}                                                | register        |
| GET    | classes/       | {classId, name, teacherUserId, subjectId, roomId, timeId} | show all classes |
|GET	|classes/{id}	 		|{classId, name, teacherUserId, subjectId, roomId, timeId}|get a class by id|
|GET	|/subjects			|{subjectId, name, description} |show all subjects|
|GET|	/rooms|		 		{roomId, name, capacity}|show all rooms|
|GET	|/time				|{timeId, weekDay} |show all time|


### ROLE STUDENT
| Method | API            | Response Body                                            | Description     |
|--------|----------------|----------------------------------------------------------|-----------------|
|GET	|student/info			|{userId, username, name} |show info of current student|
|POST	|student/classes/{id}	 	|{message}|student can enroll a class by classId|
|GET	|student/myClasses		|{classId, name, teacherUserId, subjectId, roomId, timeId}|show all classes which student are attending|

### ROLE TEACHER
| Method | API            | Response Body                                            | Description     |
|--------|----------------|----------------------------------------------------------|-----------------|
|GET	|teacher/info			|{userId, username, name} |show info of current teacher|
|GET	|teacher/mySubjects	 	|{subjectId, name, description}|show all subject that are taught by current teacher|
|GET	|teacher/myClasses		|{classId, name, teacherUserId, subjectId, roomId, timeId} |show all classes which teacher are attending|

### ROLE ADMIN
| Method | API            | Response Body                                            | Description     |
|--------|----------------|----------------------------------------------------------|-----------------|
|GET	|admin/info			|{userId, username, name} |show info of current admin|
|GET	|admin/users			|{userId, username, name} |show info of all users|
|POST	|admin/newTeacher	 	|{message} |admin can create a new teacher|
|POST	|admin/newClass	 	|{message} |admin can create a new class|
|PUT	|admin/classes/{id}	 	|{message} |admin can update a class by classId|
|DEL	|admin/classes/{id}	 	|{message} |admin can delete a class by classId|
|POST	|admin/newSubject		|{message} |admin can create a new subject|
|PUT	|admin/subjects/{id}		 |{message} |update a subject by subjectId|
|DEL	|admin/subjects/{id}	 	|{message} |admin can delete a subject by subjectId|
|POST	|admin/newRoom	 	|{message} |admin can create a new room|
|PUT	|admin/rooms/{id}	 	|{message} |admin can update a room by roomId|
|DEL	|admin/rooms/{id}	 	|{message} |admin can delete a room by roomId|
|POST	|admin/newTime	 	    |{message} |admin can create a new time|
|PUT	|admin/time/{id}	 	|{message} |admin can update a time by timeId|
|DEL	|admin/time/{id}	 	|{message} |admin can delete a time by timeId|
