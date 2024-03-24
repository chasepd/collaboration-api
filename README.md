# collaboration-api
RESTful API/Backend for managing tasks and projects, with a microservice for each function. Written in Java except for the NLP service which is written in Python.

Mainly a practice project to keep skills sharp.

## Endpoints
### Authentication
- POST /auth/register - Register a new user.
- POST /auth/login - Authenticate a user and return a token.
- POST /auth/logout - Logout a user.
### User Management
- GET /users - Retrieve a list of users.
- GET /users/{userId} - Retrieve a specific user's details.
- PUT /users/{userId} - Update user details.
- DELETE /users/{userId} - Delete a user.
### Project Management
- POST /projects - Create a new project.
- GET /projects - List all projects.
- GET /projects/{projectId} - Get details of a specific project.
- PUT /projects/{projectId} - Update a project.
- DELETE /projects/{projectId} - Delete a project.
### Task Management
- POST /projects/{projectId}/tasks - Add a new task to a project.
- GET /projects/{projectId}/tasks - List tasks in a project.
- GET /projects/{projectId}/tasks/{taskId} - Get details of a specific task.
- PUT /projects/{projectId}/tasks/{taskId} - Update a task.
- DELETE /projects/{projectId}/tasks/{taskId} - Delete a task.
### Comments
- POST /projects/{projectId/tasks/{taskId}/comments - Add a comment to a task.
- GET /projects/{projectId/tasks/{taskId}/comments - List comments on a task.
- GET /projects/{projectId/tasks/{taskId}/comments/{commentId} - Get a single comment.
- PUT /projects/{projectId/tasks/{taskId}/comments/{commentId} - Update a comment.
- DELETE /projects/{projectId/tasks/{taskId}/comments/{commentId} - Delete a comment.
### NLP Service Integration
- POST /nlp/summarize - Generate a summary for a given task description or comment thread.
- POST /nlp/prioritize - Analyze task details and suggest a priority level.
### Notifications
- GET /users/{userId}/notifications - Retrieve user's notifications.
- POST /users/{userId}/notifications - Send a notification to a user.
