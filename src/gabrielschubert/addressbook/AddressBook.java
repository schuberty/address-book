package gabrielschubert.addressbook;

import gabrielschubert.datastructure.OneArray;

public class AddressBook {
    private OneArray address;

    public AddressBook() {
        this.address = new OneArray();
    }

    public static void Start() {
        System.out.println("Started");
    }

    public static void main(String[] args) {
        if (args.length != 2)
            System.out.println("At least one argument.");
        else
            AddressBook.Start();
    }
}
