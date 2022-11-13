package lab_ex.lab_ex4;

public class Main {

    public static void main(String[] args) {

        UserRepository userRepository = new UserRepository();

        try {
            userRepository.addUser("Vasil", "12vr43c3");
            userRepository.addUser("Stoyan", "13y4u32432");
//            userRepository.addUser("Petyr", "532");
            userRepository.addUser("Ivan", "421421412412");
            userRepository.addUser("Dragan", "f43f4343");
        } catch (TooManyUsersException  | WrongInputDataException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Testing finally!");
        }
    }
}
