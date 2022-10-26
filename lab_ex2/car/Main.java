package lab_ex2.car;

public class Main {

    public static void main(String[] args) {
        Car car1 = new Car("BMW", "X5", "black", 100, "4", "6");
        Car car2 = new Car("Mercedes", "C-class", "black", 100, "4", "6");
        Car car3 = new Car("Audi", "A6", "black", 100, "4", "6");
        Car car4 = new Car("BMW", "X6", "black", 100, "4", "6");
        Car car5 = new Car("Mercedes", "X6", "black", 100, "4", "6");

        Car[] cars = new Car[5];
        cars[0] = car1;
        cars[1] = car2;
        cars[2] = car3;
        cars[3] = car4;
        cars[4] = car5;

        Car[] newCars = Car.carsWithStartingLetter(cars, 'A');
        for (Car c : newCars) {
            System.out.println(c.getBrand());
        }

        System.out.println();

        Car[] newCars1 = Car.sortingCarsByBrand(cars, false);
        for (Car c : newCars1) {
            System.out.println(c.getBrand());
        }

        System.out.println();

        Car[] newCars2 = Car.checkingForDuplicates(cars);
        for (Car c : newCars2) {
            System.out.println(c.getBrand());
        }
    }
}
