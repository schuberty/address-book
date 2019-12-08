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
                "Email...: " + emailAddress + "\n");
    }
}