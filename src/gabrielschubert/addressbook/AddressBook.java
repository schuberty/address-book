package gabrielschubert.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private String bookName;
    private List<Person> addresses;
    private int entriesQuantity;

    public AddressBook(String bookName) {
        this.bookName = bookName;
        this.entriesQuantity = 0;
        addresses = new ArrayList<>();
    }

    public void addEntry(Person person) {
        addresses.add(person);
        entriesQuantity++;
    }

    public void deleteEntry(int index) {
        addresses.remove(index);
        entriesQuantity--;
    }

    public void deleteEntry(Person person) {
        addresses.remove(person);
        entriesQuantity--;
    }

    public String getBookName() {
        return bookName;
    }

    public List<Person> getAddresses() {
        return addresses;
    }

    public Person getPerson(int index) {
        return addresses.get(index);
    }

    public int getQuantity() {
        return entriesQuantity;
    }

    @Override
    public String toString() {
        return ("BOOK \"" + bookName + "\" with " + entriesQuantity + " entrie(s)");
    }

}