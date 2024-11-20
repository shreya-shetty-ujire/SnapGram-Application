
# SnapGram

**SnapGram** is a social media application, designed for photo sharing, user interactions, and social networking. Users can post images, like or comment on others' posts, follow other users.

---

## Tech Stack

### Backend:
- **Spring Boot**: Version `3.3.5`
- **Java**: Version `23.0.1`
- **PostgreSQL**: Version `15.x.x` (Database)
- **JWT (JSON Web Tokens)** for Authentication

### Frontend:
- **Angular**: Version `18.2.12` (TypeScript, HTML, CSS)
- **Bootstrap** for responsive design

### Tools:
- **Postman** for API testing
- **Git** for version control
- **Maven** for build automation
- **Docker** for containerization

---

## Setup Instructions

### 1. Clone the Repository

git clone https://github.com/yourusername/snapgram.git

### 2. Database Setup

1. **Ensure PostgreSQL is installed**:

2. **Create a Database**:
   - Open **pgAdmin** to create a database for the project.
3. **Open the application.properties file** located in src/main/resources/ in your backend project.
   - Configure the PostgreSQL connection by adding your credentials in application.properties:

4. **application.properties** 
  **PostgreSQL Database Configuration**
    spring.datasource.url=                   # Database URL
    spring.datasource.username=yourusername  # Your PostgreSQL username
    spring.datasource.password=yourpassword  # Your PostgreSQL password

  **JPA and Hibernate Configuration**
    spring.jpa.hibernate.ddl-auto=update                                 # Auto schema generation (update schema if needed)
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect # Dialect for PostgreSQL

   - Replace yourusername and yourpassword with your actual PostgreSQL credentials.
