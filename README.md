
# SnapGram

**SnapGram** is a social media application, designed for photo sharing, user interactions, and social networking. Users can post images, like or comment on others' posts, follow other users.

![Snapgram](snapgram-frontend/src/assets/images/name.png)
<img src="snapgram-frontend/src/assets/images/image1.PNG" alt="Snapgram" width="300" height="auto" />


---

## Tech Stack

### Backend:
- **Spring Boot**: Version `3.3.5`
- **Java**: Version `23.0.1`
- **PostgreSQL**: Version `15.x.x` (Database)
- **JWT (JSON Web Tokens)** for Authentication

### Frontend:
- **React.js**: Version `18.2.12` (TypeScript, HTML, CSS)

### Tools:
- **Postman** for API testing
- **Git** for version control
- **Maven** for build automation

---

## Setup Instructions

### 1. Clone the Repository

git clone https://github.com/shreya-shetty-ujire/SnapGram-Application.git

### 2. Database Setup

1. **Ensure PostgreSQL is installed**:

2. **Create a Database**:
   - Open **pgAdmin** to create a database for the project.
3. **Open the application.properties file** located in src/main/resources/ in your backend project.
   - Configure the PostgreSQL connection by adding your credentials in application.properties:

   **application.properties** 
   - PostgreSQL Database Configuration: Replace yourusername and yourpassword with your actual PostgreSQL credentials.
     
      ```properties
      spring.datasource.url=jdbc:postgresql://localhost:5432/snapgram        # Database URL
      spring.datasource.username=yourusername                                # Your PostgreSQL username
      spring.datasource.password=yourpassword                                # Your PostgreSQL password


   
