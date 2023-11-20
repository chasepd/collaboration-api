# collaboration-api
RESTful API/Backend for managing tasks and projects

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
- POST /tasks/{taskId}/comments - Add a comment to a task.
- GET /tasks/{taskId}/comments - List comments on a task.
- DELETE /tasks/{taskId}/comments/{commentId} - Delete a comment.
### NLP Service Integration
- POST /nlp/summarize - Generate a summary for a given task description or comment thread.
- POST /nlp/prioritize - Analyze task details and suggest a priority level.
### Notifications
- GET /users/{userId}/notifications - Retrieve user's notifications.
- POST /users/{userId}/notifications - Send a notification to a user.
### File Management (for Attachments)
- POST /tasks/{taskId}/attachments - Upload an attachment to a task.
- GET /tasks/{taskId}/attachments - List attachments for a task.
- DELETE /tasks/{taskId}/attachments/{attachmentId} - Delete an attachment.
### Admin Operations
- GET /admin/users - Admin view of all users.
- GET /admin/projects - Admin view of all projects.
