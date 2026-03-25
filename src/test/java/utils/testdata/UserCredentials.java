package utils.testdata;

import java.time.LocalDateTime;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(UserTestData user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCredentials random() {
        var rnd = LocalDateTime.now().getNano();
        return new UserCredentials("test" + rnd + "@gmail.ru", "testtest");
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

