

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
