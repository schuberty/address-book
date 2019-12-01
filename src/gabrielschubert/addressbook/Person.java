package gabrielschubert.addressbook;

public class Person {
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String residenceAddress;
    private String emailAddress;

    public Person(String firstName, String lastName, int phoneNumber, String residenceAddress, String emailAddress) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.residenceAddress = residenceAddress;
        this.emailAddress = emailAddress;
    }
}