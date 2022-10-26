package lab_ex2.car;

public class Car {

    private String brand;
    private String model;
    private String color;
    private int horsePower;
    private String typeOfEngine;
    private String typeOfGearbox;

    public Car() {

    }

    public Car(String brand, String model, String color, int horsePower, String typeOfEngine, String typeOfGearbox) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.horsePower = horsePower;
        this.typeOfEngine = typeOfEngine;
        this.typeOfGearbox = typeOfGearbox;
    }

    public static Car[] carsWithStartingLetter(Car[] cars, char letter) {
        int cnt = 0;
        for (Car c : cars) {
            if (c.brand.startsWith(String.valueOf(letter))) {
                cnt++;
            }
        }

        Car[] newCars = new Car[cnt];
        int index = 0;

        for (Car c : cars) {
            if (c.brand.startsWith(String.valueOf(letter))) {
                newCars[index++] = c;
            }
        }

        return newCars;
    }

    public static Car[] sortingCarsByBrand(Car[] cars, boolean isAscending) {
        Car temp;
        for (int i = 0; i < cars.length - 1; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                if (cars[i].brand.compareTo(cars[j].brand) == 0) {
                    continue;
                }

                if (isAscending) {
                    if (cars[i].brand.compareTo(cars[j].brand) > 0) {
                        temp = cars[i];
                        cars[i] = cars[j];
                        cars[j] = temp;
                    }
                } else {
                    if (cars[i].brand.compareTo(cars[j].brand) < 0) {
                        temp = cars[j];
                        cars[j] = cars[i];
                        cars[i] = temp;
                    }
                }
            }
        }

        return cars;
    }

    public static Car[] checkingForDuplicates(Car[] cars) {
        int cnt = 0;
        for (int i = 0; i < cars.length - 1; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                if (cars[i].brand.equalsIgnoreCase(cars[j].brand)) {
                    cnt++;
                }
            }
        }

        Car[] newCars = new Car[cars.length - cnt];
        int index = 0;

        boolean[] isDuplicated = new boolean[cars.length];

        for (int i = 0; i < cars.length; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                if (cars[i].brand.equalsIgnoreCase(cars[j].brand)) {
                    isDuplicated[j] = true;
                }
            }
        }

        for (int i = 0; i < isDuplicated.length; i++) {
            if (!isDuplicated[i]) {
                newCars[index++] = cars[i];
            }
        }

        return newCars;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(String typeOfEngine) {
        this.typeOfEngine = typeOfEngine;
    }

    public String getTypeOfGearbox() {
        return typeOfGearbox;
    }

    public void setTypeOfGearbox(String typeOfGearbox) {
        this.typeOfGearbox = typeOfGearbox;
    }
}
