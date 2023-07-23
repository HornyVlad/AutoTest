package constant;

import models.UserModel;

public class Users {
    private static final String password = "TestPassword";

    public static final String Administrator = "Test";

    public static UserModel getUserByName(String userName) {
        switch (userName) {
            case Administrator:
                return new UserModel(userName, password, "Администратор (Администратор)");
            default:
                return new UserModel(null, null, null);
        }
    }
}
