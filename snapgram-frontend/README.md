## Available Scripts

In the project directory, you can run:

### `npm start`

Runs the app in the development mode.\
Open [http://localhost:3000](http://localhost:3000) to view it in your browser.

The page will reload when you make changes.\
You may also see any lint errors in the console.


# Snapgram Frontend Features

## Features

### Authentication Pages:
- **Login**: Page where users can enter their username and password to authenticate.
- **Signup**: Page for new users to create an account by providing username, email, and password.

### Profile Management:
- **User Profile Page**: Displays user’s profile information, including username, bio, profile picture, and follower and following count.
- **Edit Profile**: Users can edit their profile details, such as bio, profile picture, and update account information.
- **View User's Posts and Saved Posts**: Display posts and comments made by the user, allowing easy navigation and interaction.

### Content Interaction:
- **Post Creation**: Interface for users to create and share posts with text, images, or videos.
- **View Posts**: Feed displaying posts from followed users, with the ability to like, comment, and share.
- **Like and Unlike Posts**: Enable users to like and unlike posts, with a real-time update on the like count.
- **Comment on Posts**: Users can add, edit, and delete comments on posts. Comments should appear below the corresponding post.

### Social Features:
- **Follow/Unfollow Users**: 
  - Buttons to follow or unfollow users directly from their profile or in the user feed.
  - Real-time follow/unfollow updates.
- **Search Users**: 
  - A search bar where users can search for other users by their username.
  - Display user profile suggestions based on search results.
- **Popular Users**: Display a list of popular users based on certain metrics like followers count or activity.

### UI Components:
- **Modal Popups**: For creating posts, editing profiles, or confirming actions like follow/unfollow or deleting comments.
- **User Lists**: Display a list of followers, following, and user suggestions.

### Authentication:
- **JWT Token Handling**: Store JWT token securely in the frontend (localStorage/sessionStorage or cookies) for authenticated requests.
- **Token Management**: Attach JWT tokens to API requests in the `Authorization` header for protected endpoints.

### Error Handling:
- **Error Display**: Show error messages for failed actions (e.g., incorrect credentials, network errors, unauthorized access).
- **Form Validation**: Frontend validation for input fields, including username, email, password, etc.

