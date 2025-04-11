package com.snapgram.backend.DTO;



import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.*;

@Embeddable
public class UserDto {


        private Integer userId;

        @NotBlank(message = "Username cannot be blank")
        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
        private String username;




    @NotBlank(message = "First name cannot be blank")
        @Pattern(regexp = "^[a-zA-Z ]+$", message = "First name must only contain letters and spaces")
        private String name;


        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        private String email;

    private String userImage;



    public UserDto() {

    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String userName) {
            this.username = userName;
        }

        public String getName() {
            return name;
        }

        public void setName(String firstName) {
            this.name = firstName;
        }



        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UserDto userDto = (UserDto) obj;
        return userId != null && userId.equals(userDto.userId);
    }

    @Override
    public int hashCode() {
        return userId != null ? userId.hashCode() : 0;
    }
}
