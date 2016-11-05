package reuse;

public class Person {
    private String name,email,date;
    private int nos;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNos() {
        return nos;
    }

    public void setNos(int nos) {
        this.nos = nos;
    }

    public Person(String name, String email, String date, int nos) {
        this.name = name;
        this.email = email;
        this.date = date;
        this.nos = nos;
    }
    
    
}
