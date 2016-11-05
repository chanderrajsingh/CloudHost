
package reuse;

public class Profile {
    private String fname,lname,email,gen,country;
    long contact;

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGen() {
        return gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public Profile(String fname, String lname, String email, String gen, String country, long contact) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.gen = gen;
        this.country = country;
        this.contact = contact;
    }

    public Profile() {
    }
    
    
}
