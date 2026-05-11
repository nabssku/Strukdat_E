package demo.modul2.task1;

public class Passenger {

    private static int nextId = 1001;


    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private int age;

    public Passenger(String fullName, String email, String phoneNumber, int age) {
        this.id = nextId++;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void updateData(String fullName, String email, String phoneNumber, int age) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public void showDetail(int index) {
        System.out.println("["+ index +"]|[" + id + "] "
                + fullName + " | " + email
                + " | " + phoneNumber
                + " | Age: " + age);
    }
}