package gabrielschubert.datastructure;

import java.util.List;
import java.util.ArrayList;

import gabrielschubert.addressbook.Person;

public class OneArray implements OneList {
    private List<Person> address;
    private int size;

    public OneArray() {
        address = new ArrayList<Person>();
        size = 0;
    }

    public void Insert(Person data) {
        address.add(data);
        size++;
    }

    public void Remove(int index) {
        if (index <= size && index > 0) {
            index--;
            address.remove(index);
        }
    }
}