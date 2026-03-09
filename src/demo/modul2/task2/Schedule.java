package demo.modul2.task2;

public class Schedule {

    private static int autoNumber = 1;

    private int id;
    private String code;
    private String trainName;
    private String from;
    private String to;
    private String departure;
    private double price;

    public Schedule(String code, String trainName,
                    String from, String to,
                    String departure, double price) {

        this.id = autoNumber++;
        this.code = code;
        this.trainName = trainName;
        this.from = from;
        this.to = to;
        this.departure = departure;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public void printSchedule() {
        System.out.println(id + " | "
                + code + " | "
                + trainName + " | "
                + from + " -> " + to
                + " | " + departure
                + " | Rp" + price);
    }
}
