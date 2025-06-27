public class Equipment {
    private String name;
    private String lastname;
    private String id;
    private String lastServicedDate;
    private String nextServiceDate;
    private String status;

    public Equipment(String name, String lastname, String id, String lastServicedDate, String nextServiceDate, String status) {
        this.name = name;
        this.lastname=lastname;
        this.id = id;
        this.lastServicedDate = lastServicedDate;
        this.nextServiceDate = nextServiceDate;
        this.status = status;
    }

    public String getName() { return name; }

    public String getLastname() {
        return lastname;
    }
    public String getId() { return id; }
    public String getLastServicedDate() { return lastServicedDate; }
    public String getNextServiceDate() { return nextServiceDate; }
    public String getStatus() { return status; }

    public String[] toArray() {
        return new String[]{name, id, lastServicedDate, nextServiceDate, status};
    }
}
