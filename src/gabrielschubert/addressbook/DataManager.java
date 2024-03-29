package gabrielschubert.addressbook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataManager{
    private final String dataPath = ".." + File.separator + ".." + File.separator + "data" + File.separator;
    private List<AddressBook> bookFiles;
    private int dataQuantity;

    public DataManager() {
        File dataDir = new File(dataPath);
        bookFiles = new ArrayList<>();
        dataQuantity = 0;
        String file;

        if(!dataDir.exists()) {
            if(!dataDir.mkdir()) {
                Menu.infoTimer("Data file wasn't created. Exiting.");
                System.exit(1);
            }
        } else {
            for(final File f : dataDir.listFiles()) {
                if(f.isFile()) {
                    file = f.getName();
                    try {
                        bookFiles.add(loadBook(file));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    increaseQuantity();
                }
            }
        }
    }

    private AddressBook loadBook(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(dataPath + fileName));
        String line = null;
        AddressBook newBook;

        line = reader.readLine();
        if(line != null) {
            newBook = new AddressBook(line);
            do {
                line = reader.readLine();
                if(line != null) {
                    Person person = parsePerson(line);
                    if(person != null) {
                        newBook.addEntry(person);
                    }
                }
            } while(line != null);
        } else {
            reader.close();
            return null;
        }
        reader.close();
        return newBook;
    }

    private Person parsePerson(String line) {
        Person person = null;
        String vars[] = line.split(";");
        if(vars.length == 5) {
            person = new Person(
                vars[0], vars[1], vars[2], vars[3], vars[4]
            );
        } else {
            return null;
        }
        return person;
    }

    public Boolean newBook(String bookName) throws IOException {
        String filePath = dataPath + bookName + ".txt";
        File file = new File(filePath);
        if(file.exists()) {
            System.out.print(
                "\n# Address Book \"" + bookName + "\" alread exists, want to overwrite?\n" +
                "1.: Yes (all entries will be deleted)\n2.: No\n"
            );
            int option = Menu.getOption(true);
            if(option == 2)
                return false;
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        bookFiles.add(new AddressBook(bookName));
        writer.write(bookName);

        writer.close();
        increaseQuantity();
        return true;
    }

    public void removeBook(int index) {
        String bookName = bookFiles.get(index).getBookName();
        File file = new File(dataPath + bookName + ".txt");
        if(file.delete()) {}
            bookFiles.remove(index);
        decreaseQuantity();
    }

    public void listBook(int index) {
        if(index == -1) {
            for(AddressBook addressbook : bookFiles) {
                System.out.println("--" + addressbook);
                for(Person person : addressbook.getAddresses()) {
                    System.out.println(
                        "___________________\n" +
                        person
                    );
                }
                System.out.println();
            }
        } else {
            index--;
            System.out.println("--" + bookFiles.get(index));
            for(Person person : bookFiles.get(index).getAddresses()) {
                System.out.println(
                    "___________________\n" +
                    person
                );
            }
        }
    }

    public void saveToData() throws IOException {
        BufferedWriter writer;
        String filePath = "";
        for(AddressBook addressbook : bookFiles) {
            filePath = dataPath + addressbook.getBookName() + ".txt";
            writer = new BufferedWriter(new FileWriter(filePath));
            writer.write(addressbook.getBookName() + "\n");
            for(Person person : addressbook.getAddresses()) {
                writer.write(person.getToFile());
            }
            writer.close();
        }
    }

    public void searchSpecificLetter(char letter) {
        System.out.println();   
        int count = 0;
        for(AddressBook addressbook : bookFiles) {
            for(Person person : addressbook.getAddresses()) {
                String[] compare = person.getFullName().toLowerCase().split(" ");
                if(letter == compare[1].charAt(0)) {
                    System.out.println(person);
                    System.out.println("___________________");
                    count++;
                }
            }
        }
        System.out.println("Numbers of Last Names beginning with \"" + letter + "\" is " + count);
    }

    @Override
    public String toString() {
        String toString = "";
        int i = 1;
        for(AddressBook addressbook : bookFiles) {
            toString += i++ + ".: ";
            toString += addressbook.toString();
            toString += "\n";
        }
        return toString;
    }

    private void increaseQuantity() {
        dataQuantity++;
    }

    private void decreaseQuantity() {
        dataQuantity--;
    }

    public AddressBook getBook(int index) {
        return bookFiles.get(index);
    }

    public int getQuantity() {
        return dataQuantity;
    }
}