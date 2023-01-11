package airplane.tickets;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {

    private final String firstName;
    private final String secondName;
    private final int age;
    private int loyalPoints;
    public Customer(String firstName, String secondName, int age, int loyalPoints) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.age = age;
        this.loyalPoints = loyalPoints;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", age=" + age +
                ", loyalPoints=" + loyalPoints +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return age == customer.age && Objects.equals(firstName, customer.firstName) && Objects.equals(secondName, customer.secondName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, secondName, age);
    }

    public void addLoyalPoints() {
        this.loyalPoints += 30;
    }

    public void chargeLoyalPoints() {
        this.loyalPoints -= 50;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public int getAge() {
        return age;
    }

    public int getLoyalPoints() {
        return loyalPoints;
    }
}
