package gabrielschubert.addressbook;

import java.io.IOException;
import java.util.Scanner;

public final class Client {
    private static int option;
    private static boolean valid;
    private static DataManager manager = new DataManager();

    public static void startClient() {
        valid = true;
        do {
            Menu.clearTerminal();
            Menu.printMain();
            option = Menu.getOption(valid);
            valid = true;
            switch (option) {
            case 1:
                manageBooks();
                break;
            case 2:
                createBook();
                break;
            case 3:
                deleteBook();
                break;
            case 4:
                saveAll();
                break;
            case 5:
                Menu.infoTimer("Exiting Address Book");
                System.out.println();
                break;
            default:
                valid = false;
                break;
            }
        } while (option != 5);
    }

    public static void manageBooks() {
        if(manager.getQuantity() == 0) {
            Menu.infoTimer("There's no Address Book to manage");
        } else {
            do {
                Menu.clearTerminal();
                Menu.printManager();
                option = Menu.getOption(valid);
                valid = true;
                switch (option) {
                    case 1:
                        createEntry();
                        break;
                    case 2:
                        removeEntry();
                        break;
                    case 3:
                        editEntry();
                        break;
                    case 4:
                        searchEntries();
                        break;
                    case 5:
                        listEntries();
                        break;
                    default:
                        valid = false;
                        break;
                }
            } while (option != 6);
        }
    }

    public static void createEntry() {
        int index = 0;
        if(manager.getQuantity() != 1) {
            do {
                Menu.clearTerminal();
                System.out.print(
                    "\t#~~ Address Book CREATE an entry ~~#\n\n" +
                    manager + "0.: EXIT to manager menu\n\n"
                );
                index = Menu.getOption(true, "(book to insert an entry)\n");
                if(index == 0)
                    return;
                index--;
            } while(index < 0 || index >= manager.getQuantity());
        } else {
            Menu.clearTerminal();
            System.out.print("\t#~~ Address Book CREATE an entry ~~#\n");
        }
        System.out.println("\n# Insert entry at \"" + manager.getBook(index).getBookName() + "\" address book" );
        String vars[] = Menu.personEntry();
        Person person = new Person(
            vars[0], vars[1], vars[2], vars[3], vars[4]
        );
        manager.getBook(index).addEntry(person);
    }

    public static void removeEntry() {
        int index = 0;
        if(manager.getQuantity() != 1) {
            do {
                Menu.clearTerminal();
                System.out.print(
                    "\t#~~ Address Book REMOVE an entry ~~#\n\n" +
                    manager + "0.: EXIT to manager menu\n\n"
                );
                index = Menu.getOption(true, "(book to remove an entry)\n");
                if(index == 0)
                    return;
                index--;
            } while(index < 0 || index >= manager.getQuantity());
        } else {
            Menu.clearTerminal();
            System.out.print("\t#~~ Address Book REMOVE an entry ~~#\n");
        }

        AddressBook addressbook = manager.getBook(index);
        if(addressbook.getQuantity() == 0) {
            Menu.infoTimer("There's no Entries to remove");
        } else {
            do {
                System.out.println("\n--Removing at " + addressbook + "\n");
                for(int i = 0; i < addressbook.getQuantity(); ++i) {
                    System.out.println((i+1) + ".: " + addressbook.getPerson(i).getInfo());
                }
                System.out.println("0.: EXIT to manager menu\n");
                index = Menu.getOption(valid);
            } while(index < 0 || index > addressbook.getQuantity());
    
            if(index == 0) {
                return;
            } else {
                index--;
                Menu.infoTimer(addressbook.getPerson(index).getFullName() + " removed from " + addressbook.getBookName());
                addressbook.deleteEntry(index);
            }
        }
    }

    public static void editEntry() {
        int index = 0;
        if(manager.getQuantity() != 1) {
            do {
                Menu.clearTerminal();
                System.out.println(
                    "\t#~~ Address Book EDIT an entry ~~#\n\n" +
                    manager + "0.: EXIT to manager menu\n"
                );
                index = Menu.getOption(true);
                if(index == 0)
                    return;
                index--;
            } while(index < 0 || index >= manager.getQuantity());
        } else {
            Menu.clearTerminal();
            System.out.println("#~~ Address Book EDIT an entry ~~#\n\n");
        }
        
        AddressBook addressbook = manager.getBook(index);
        if(addressbook.getQuantity() == 0) {
            Menu.infoTimer("There's no Entries to edit");
            return;
        } else {
            do {
                System.out.println("\n--Select entry at " + addressbook + " to edit\n");
                for(int i = 0; i < addressbook.getQuantity(); ++i) {
                    System.out.println((i+1) + ".: " + addressbook.getPerson(i).getInfo());
                }
                System.out.println("0.: EXIT to manager menu\n");
                index = Menu.getOption(true, "to edit\n");
                if(index == 0)
                    return;
            } while(index < 0 || index > addressbook.getQuantity());
        }
        
        index--;
        Person person = addressbook.getPerson(index);
        Menu.infoTimer("Editing " + person.getFullName());
        valid = true;
        do {
            Menu.clearTerminal();
            System.out.println(
                "\t#~~ Address Book EDIT an entry ~~#\n\n" +
                "--Edit options to " + person.getInfo() + "\n" +
                "1.: EDIT First Name\n" +
                "2.: EDIT Last Name\n" +
                "3.: EDIT Phone Number\n" +
                "4.: EDIT Residence Address\n" +
                "5.: EDIT Email Address\n" +
                "6.: MOVE to another address book\n" +
                "7.: EXIT to manager menu\n"
            );
            option = Menu.getOption(valid);
            valid = true;
            switch (option) {
                case 1:
                    System.out.print("New first name > ");
                    person.setFirstName(Menu.getString());
                    break;
                case 2:
                    System.out.print("New last name > ");
                    person.setLastName(Menu.getString());
                    break;
                case 3:
                    System.out.print("New phone number > ");
                    person.setPhoneNumber(Menu.getString());
                    break;
                case 4:
                    System.out.print("New residence address > ");
                    person.setResidenceAddress(Menu.getString());
                    break;
                case 5:
                    System.out.print("New email address > ");
                    person.setEmailAddress(Menu.getString());
                    break;
                case 6:
                    System.out.println("\n# Move to address book:\n" + manager + "0.: EXIT to edit menu");
                    option = Menu.getOption(true);
                    if(option == 0)
                        continue;
                    else if(option > 0 && option <= manager.getQuantity()) {
                        option--;
                        Menu.infoTimer(
                            person.getFullName() + " moved from " +
                            addressbook.getBookName() + " to " +
                            manager.getBook(option).getBookName()
                            );
                        manager.getBook(option).addEntry(person);
                        addressbook.deleteEntry(index);
                        return;
                    }
                    break;
                default:
                    valid = false;
                    break;
            }

        } while(index != 7);
    }

    public static void searchEntries() {
        System.out.print("\n# Search for entries with first letter of last name being\n > ");
        String line = Menu.getString().toLowerCase();
        manager.searchSpecificLetter(line.charAt(0));
        Menu.waitClick();
    }

    public static void listEntries() {
        int index = 0;

        do {
            Menu.clearTerminal();
            System.out.print(
                "\t#~~ Address Book LIST entries ~~#\n\n" +
                manager +
                (manager.getQuantity() + 1) + ".: LIST all address book(s) entries\n\n"
            );
            index = Menu.getOption(true);
        } while(index <= 0 || index > (manager.getQuantity() + 1));
        
        System.out.println();
        if(index == (manager.getQuantity() + 1)) {
            manager.listBook(-1);
        } else if(index == 0) {
            return;
        } else {
            manager.listBook(index);
        }

        Menu.waitClick();
    }

    // public static void sortEntries() {
    //     int index = 0;
    //     if(manager.getQuantity() != 1) {
    //         do {
    //             Menu.clearTerminal();
    //             System.out.println(
    //                 "\t#~~ Address Book SORT books ~~#\n\n" +
    //                 manager + "0.: EXIT to manager menu\n"
    //             );
    //             index = Menu.getOption(true, "to sort\n");
    //             if(index == 0)
    //                 return;
    //             index--;
    //         } while(index < 0 || index >= manager.getQuantity());
    //     } else {
    //         Menu.clearTerminal();
    //         System.out.println("#~~ Address Book SORT books ~~#\n\n");
    //     }

    //     if(manager.getBook(index).getQuantity() == 0) {
    //         Menu.infoTimer("There's no Entries to sort");
    //         return;
    //     } else {
    //         System.out.println();
    //         do {
    //             System.out.println(
    //                 "1. SORT by first name\n" +
    //                 "2. SORT by last name\n" +
    //                 "3. SORT by residencial address\n" +
    //                 "4. SORT by email address\n" +
    //                 "5. EXIT to manager menu\n" 
    //             );
    //             index = Menu.getOption(true);

    //         } while(index < 0 || index > 5);
    //     }

    //     switch (index) {
    //         case 1:
    //             manager.sortByType();
    //             break;
    //         case 2:
                
    //             break;
    //         case 3:
                
    //             break;
    //         case 4:
                
    //             break;
    //         case 5:
    //             return;
    //         default:
    //             break;
    //     }
    // }

    public static void createBook() {
        System.out.print("\n# Type the new Address Book name\n" + " > ");
        String bookName = new Scanner(System.in).nextLine();
        try {
            if(manager.newBook(bookName))
                Menu.infoTimer("Address Book \"" + bookName + "\" created");
            else
                Menu.infoTimer("Address Book \"" + bookName + "\" wasn't overwrited");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBook() {
        int index = 1;
        if(manager.getQuantity() == 0) {
            Menu.infoTimer("There's no Address Book to delete");
        } else {
            valid = true;
            do {
                Menu.clearTerminal();
                System.out.println(
                    "\t#~~ Address Book LIST to remove ~~#\n\n" +
                    manager + "0.: EXIT to main menu\n"
                );
                index = Menu.getOption(valid);
                valid = true;
                if (index > 0 && index <= manager.getQuantity()) {
                    index--;
                    Menu.infoTimer("Address Book \"" + manager.getBook(index).getBookName() + "\" removed");
                    manager.removeBook(index);
                    index = 1;
                } else {
                    valid = false;
                }
            } while(index != 0 && manager.getQuantity() != 0);
        }
    }

    public static void saveAll() {
        try {
            manager.saveToData();
        } catch (IOException e) {
            Menu.infoTimer("Failed to save address books. Exiting.");
            System.exit(1);
            e.printStackTrace();
        }
        Menu.infoTimer("All " + manager.getQuantity() + " Address Books have been saved");
    }

    public static void main(String[] args) {
        startClient();
    }
}