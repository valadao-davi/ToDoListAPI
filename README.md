# TimerToDo API

The Timer API is a secure RESTful service built with Java Spring, PostgreSQL, and Spring Security, offering task management and time tracking in minutes. It includes user authentication (login/registration) via JWT tokens, ensuring secure access. Each user has a personalized profile and exclusive access to their task lists, with CRUD operations for tasks.


# Features

- **Login and Register**: Register and Login to see and managemeant your own tasks.
- **Task Management**: Easily manage tasks with status tracking: Pending, In Progress, and Done.
- **Time Tracking**: Track task duration in minutes with start/stop timers for precise time management.
- **PostgreSQL Database**: A scalable database for storing task data, status, and time tracking in minutes.
- **Docker Compatibility**: Simplifies deployment, ensuring smooth integration and easy setup.
- **Future Scalability**: Ready for integration into a full-stack project for complete task and time management.
- **Efficient API Design**: Built with Java Spring for high performance and easy integration.
- **Architecture Pattern**: Built with the Controller-Service-Repository pattern for better maintainability and scalability.
- **Spring Security**: All passwords are securely hashed before storage, ensuring maximum user security.



# Setup

## 1. Clone repository

```bash
git clone https://github.com/valadao-davi/ToDoListAPI
```

## 2. Navigate to the Project Directory

```bash
cd ToDoListAPI
```

## 3. Build the Project
Make sure you have Maven installed. If not, you can install Maven.


```bash
./mvnw clean install
```

## 4. Docker Setup (Optional)
If you'd like to run the project inside a Docker container, follow these steps:

### 4.1 Build the docker Image
In the project directory, create the Docker image using the following command:

```
docker build -t todolist-api:1.0 .
```

### 4.2 Run the Docker 
Now, run the Docker container with the command:

```
docker run -p 8080:8080 todolist-api
```

## 5. Verify the Application is Running

Open your browser and navigate to http://localhost:8080/tasks You should see your application running.



# API Documentation

## Requests Users 
Requests to the API should follow these standards:
| Method | Description |
|---|---|
| `GET` | Retrieve the logged-in user's profile. |
| `POST` | Register and Login |
| `PUT` | Update the logged-in user's profile. |
| `DELETE` | Delete the logged-in user's account. |


## Retrieve (Get) [GET  /profile]

### Retrive user safe logged-in information:
- **Authentication:** Necessary
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`
```
GET https://todolistapi-9n7j.onrender.com/profile
```
+ Example response:
```json    
{
    "userId": 2,
    "email": "john.doe10@example.com",
    "userName": "Davi"
}
 ```

## Register (Post) [POST /auth/register]:

### Register the user in the server:
```
POST https://todolistapi-9n7j.onrender.com/auth/register
```

+ Body:
```json    
{
    "userName": "Davi",
    "email": "john.doe10@example.com",
    "password": "password12345678910"
}
 ```

+ Example response:
```    
User registered
 ```


## Login (Post) [Post  /auth/login]

### Log the user and retrieve a token to acess other endpoints:
```
POST https://todolistapi-9n7j.onrender.com/auth/login
```

+ Body:
```json    
{
    "email": "john.doe10@example.com",
    "password": "password12345678910"
}
 ```
+ Example response:
```json    
{
    "token": "Token"
}
 ```

## Edit (Put) [PUT  /profile]

### Edit logged-in user:
- **Authentication:** Necessary
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`
#### Note: All fields are optional. You can update any of them or leave them unchanged.

```
```http
PUT https://todolistapi-9n7j.onrender.com/profile
```

+ Body:
```json    
{
    "userName": "Davi100"
}
 ```

 + Example response:
```    
User Edited.
 ```

## Delete (Delete) [DELETE  /profile]

### Delete User Logged-in
- **Authentication:** Necessary
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

```http
DELETE https://todolistapi-9n7j.onrender.com/profile
```

 + Example response:
```    
User deleted.
 ```


## Requests Tasks (All these endpoints require an bearer Token given by login):
- **Authentication:** Necessary
  - **Type:** Bearer Token
  - **Header:** `Authorization: Bearer <token>`

Requests to the API should follow these standards:
| Method | Description |
|---|---|
| `GET` | Returns information about one or more tasks. |
| `POST` | Used to create a new task. |
| `PUT` | Edits and start/stop task. |
| `DELETE` | Removes a task. |


## Read (Get) [GET  /tasks]

### Read all tasks of User Logged-in:
```
GET https://todolistapi-9n7j.onrender.com/tasks
```
+ Example response:
```json    
[
    {
        "idTask": 1,
        "nameTask": "Clean room",
        "durationTask": 10,
        "priority": "LOW",
        "status": "PENDING"
    },

    {
        "idTask": 2,
        "nameTask": "Finish home lesson",
        "durationTask": 60,
        "priority": "HIGH",
        "status": "PENDING"
    }
]
 ```

### Read task of user logged in by id:
```
GET https://todolistapi-9n7j.onrender.com/tasks/:id
```
+ Example response:
```json    
{
    "idTask": 1,
    "nameTask": "Clean room",
    "durationTask": 10,
    "priority": "HIGH",
    "status": "PENDING"

}

 ```



## Create (Post) [Post  /tasks]

### Create task:
#### Note: The status attribute will be automatically set to PENDING.


+ Body:
```json    
{
    "idTask": 3,
    "nameTask": "Study & Pratice Java",
    "durationTask": 30,
    "priority": "HIGH",
    "status": pending
}
 ```

```http
POST https://todolistapi-9n7j.onrender.com/tasks
```

 + Example response:
```    
Task created.
 ```

## Edit (Put) [PUT  /tasks]


### Edit a task of user logged-in
#### Note: All fields are optional. You can update any of them or leave them unchanged, limited to these only.
+ Body:
    ```json    
        {
            "nameTask": "Clean room",
            "durationTask": 10,
            "priority": "HIGH"
        }
    ```

```http
PUT https://todolistapi-9n7j.onrender.com/tasks
```

 + Example response:
```    
Task Edited.
 ```


### Start a task

#### Note: The request remains active until the task is either manually stopped or completed.

```http
PUT https://todolistapi-9n7j.onrender.com/tasks/start/:id
```



 + Example response:

```json    
{
    "priority": "HIGH",
    "nameTask": "Clean room",
    "durationTask": 10.0,
    "idTask": 2,
    "status": "IN_PROGRESS"
}
 ```



### Stop a task

#### Note: This request only works if a task is in IN_PROGRESS status. If no task is active, it will return a 404 (Not Found) code.

```http
PUT https://todolistapi-9n7j.onrender.com/tasks/stop/:id
```

+ Body:
    ```json    
        {
            "actualDuration": 6.5
        }
    ```

 + Example response when a task is active:
```json    
{
    "priority": "HIGH",
    "nameTask": "Clean room",
    "durationTask": 3.5,
    "idTask": 2,
    "status": "PENDING"
}
 ```

 + Example response when there's no task active:
```json    
{
    "status": "NOT_FOUND",
    "message": "There's no task to stop"
}
 ```

### Finish a task

#### Note: This request only works if request is in IN_PROGRESS status. If no task is active, it will return a 404 (Not Found) code.

```http
PUT https://todolistapi-9n7j.onrender.com/tasks/finish/:id
```

 + Example response:

```json    
{
    "priority": "HIGH",
    "nameTask": "Clean room",
    "durationTask": 10.0,
    "idTask": 2,
    "status": "DONE"
}
 ```



## Remove (Delete) [DELETE  /tasks]


### Remove a task by Id based in user logged-in


```http
DELETE https://todolistapi-9n7j.onrender.com/tasks/:id
```

+ Example response:
```    
Task deleted.
 ```
