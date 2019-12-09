package gabrielschubert.addressbook;

public class Person {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String residenceAddress;
    private String emailAddress;

    public Person(String firstName, String lastName, String phoneNumber, String residenceAddress, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.residenceAddress = residenceAddress;
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return ("Name....: " + firstName + " " + lastName + "\n" +
                "Phone...: " + phoneNumber + "\n" +
                "Address.: " + residenceAddress + "\n" +
                "Email...: " + emailAddress);
    }

    public String getInfo() {
        return firstName + " " + lastName +
        ", phone is " + phoneNumber +
        ", living at " + residenceAddress +
        " and email is " + emailAddress;
    }

    public String getToFile() {
        return firstName + ";" + lastName + ";" + phoneNumber + ";" + residenceAddress + ";" + emailAddress + "\n";
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setResidenceAddress(String residenceAddress) {
        this.residenceAddress = residenceAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}