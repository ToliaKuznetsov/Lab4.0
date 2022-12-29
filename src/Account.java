public class Account {

    public Account() {}

    private String lastName;
    private String firstName;
    private String middleName;
    private String birthday;
    private String mail;
    private String password;
    private boolean blocked = true;
    private int count = 0;

    public Account(String lastName, String firstName, String middleName, String birthday, String mail, String password) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.birthday = birthday;
        this.mail = mail;
        this.password = password;
        this.blocked = false;
        this.count = 0;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }

    public void setBirthday(String birthday) { this.birthday = birthday; }

    public void setMail(String mail) { this.mail = mail; }

    public void setPassword(String password) { this.password = password; }

    public void setBlocked() { this.blocked = !this.blocked; }

    public void setCount(Integer count) { this.count = count; }

    public void logCount() { this.count++; }

    public void logTrue(Integer zero) { this.count = zero; }

    public String getPassword() { return password; }

    public String getMail() { return mail; }

    public Boolean getBlocked() { return blocked; }

    public int getCount() { return count; }

    @Override
    public String toString() {

        return lastName + "," + firstName + "," + middleName + "," + birthday +
                "," + mail + "," + password + "," + blocked + "," + count + "\n";
    }

}