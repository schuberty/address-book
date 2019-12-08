package gabrielschubert.addressbook;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private String bookName;
    private List<Person> address;
    private int entriesQuantity;

    public AddressBook(String bookName) {
        this.bookName = bookName;
        this.entriesQuantity = 0;
        address = new ArrayList<>();
    }

    public void addEntrie(Person person) {
        address.add(person);
        entriesQuantity++;
    }

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return ("BOOK \"" + bookName + "\" with " + entriesQuantity + " entrie(s)");
    }

}