# Snapgram Backend API

## Features

### User Management:
- **User Registration (Signup)**: Allows users to register with a unique username and password.
- **User Login**: Authenticates users and generates JWT token for secure sessions.
- **Profile Management**: Users can view, update, and delete their profiles.
- **Follow and Unfollow**: Allows users to follow and unfollow other users.
- **Get User by Username/ID**: Retrieve user details by username or user ID.
- **Popular Users**: Get a list of popular users based on certain criteria (e.g., number of followers).
- **Search Users**: Allows searching for users by query or partial match on the username.

### Content Interaction:
- **Create Comments**: Allows users to post comments on content such as posts.
- **Like and Unlike Comments**: Users can like or unlike comments.

### Authentication:
- **JWT Token Generation**: Secure user login via JWT tokens for session management.
- **Token Validation**: Endpoints secured by JWT validation to protect user data and ensure proper authorization.

### Error Handling:
- **Global Exception Handling**: Manages errors like User Not Found, Invalid Token, Unauthorized, etc.

### Security Features:
- **Password Encryption**: User passwords are securely stored using hashing (e.g., BCrypt).
- **Authorization Header**: All endpoints that require authentication use the Authorization header with a Bearer token.
