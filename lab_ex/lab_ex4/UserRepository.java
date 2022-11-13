package lab_ex.lab_ex4;

public class UserRepository {

    private final User[] users;
    private int userCnt;

    public UserRepository() {
        users = new User[3];
        userCnt = 0;
    }

    public void addUser(String nickName, String password) throws TooManyUsersException, WrongInputDataException {
        if (userCnt >= 3) {
            throw new TooManyUsersException("The max number of users already reached!");
        }

        if (nickName.length() <= 3 || password.length() <= 3) {
            throw new WrongInputDataException("Nickname or password must be more than 3 characters!");
        }

        User user = new User(nickName, password);
        users[userCnt++] = user;
    }

    public User[] getUsers() {
        return users;
    }
}
