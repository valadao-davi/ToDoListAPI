# TimerToDo API

The Timer API is a RESTful API built with Java Spring and PostgreSQL, designed for task management and time tracking in minutes. It allows you to create, view, edit, and delete tasks, while also tracking their duration in minutes with start/stop functionality. Tasks are initially created with a status of Pending, which changes to In Progress once the timer starts, and transitions to Done when the timer ends. This status tracking ensures that users can easily monitor the progress of their tasks. Scalable and efficient, this API is set to integrate into a full-stack project, offering essential tools for productivity and workflow management.


# Features

- **Task Management**: Easily manage tasks with status tracking: Pending, In Progress, and Done.
- **Time Tracking**: Track task duration in minutes with start/stop timers for precise time management.
- **PostgreSQL Database**: A scalable database for storing task data, status, and time tracking in minutes.
- **Docker Compatibility**: Simplifies deployment, ensuring smooth integration and easy setup.
- **Future Scalability**: Ready for integration into a full-stack project for complete task and time management.
- **Efficient API Design**: Built with Java Spring for high performance and easy integration.
- **Architecture Pattern**: Built with the Controller-Service-Repository pattern for better maintainability and scalability.
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

## Requests
Requests to the API should follow these standards:
| Method | Description |
|---|---|
| `GET` | Returns information about one or more tasks. |
| `POST` | Used to create a new task. |
| `PUT` | Edits and start/stop task. |
| `DELETE` | Removes a task. |


## Read (Get) [GET  /tasks]

### Read all tasks:
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
        "priority": "LOW"
    },

    {
        "idTask": 2,
        "nameTask": "Finish home lesson",
        "durationTask": 60,
        "priority": "HIGH"
    }
]
 ```

### Read task by id:
```
GET https://todolistapi-9n7j.onrender.com/tasks/:id
```
+ Example response:
```json    
{
    "nameTask": "Clean room",
    "durationTask": 10,
    "priority": "HIGH"
}

 ```



## Create (Post) [Post  /tasks]

### Create task:
#### Note: The status attribute will be automatically set to PENDING.


+ Body:
```json    
{
    "nameTask": "Study & Pratice Java",
    "durationTask": 30,
    "priority": "HIGH"
}
 ```

```http
POST https://todolistapi-9n7j.onrender.com/tasks
```

 + Example response:
```json    
Task created.
 ```

## Edit (Put) [PUT  /tasks]


### Edit a task
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
```json    
Task Edited.
 ```


### Start a task

#### Note: The request stays active until the task finishes, deducting 0.016 minutes per second. It stops if an error occurs or when stopped. Only one task can be active at a time.

```http
PUT https://todolistapi-9n7j.onrender.com/tasks/start/:id
```

 + Example response when duration is finished:
```json    
Done task.
 ```

 + Example response when duration is interrupted:
```json    
Task interrupted.
 ```


### Stop a task

#### Note: This request only works if a task is running. If no task is active, it will return a 406 (Not Acceptable) code.

```http
PUT https://todolistapi-9n7j.onrender.com/tasks/stop/:id
```

 + Example response when a task is active:
```json    
Task stopped.
 ```

 + Example response when there's no task active:
```json    
There's no task to stop.
 ```


## Remove (Delete) [DELETE  /tasks]


### Remove a task by Id


```http
DELETE https://todolistapi-9n7j.onrender.com/tasks/:id
```

+ Example response:
```json    
Task deleted.
 ```
