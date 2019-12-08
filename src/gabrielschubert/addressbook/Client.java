package gabrielschubert.addressbook;

import java.io.IOException;
import java.util.InputMismatchException;
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
                Menu.infoTimer("Exiting Address Book");
                System.out.println();
                break;
            default:
                valid = false;
                break;
            }
        } while (option != 4);
    }

    public static void manageBooks() {
        if(manager.getQuantity() == 0) {
            Menu.infoTimer("There's no Address Book to manage");
        } else {
            valid = true;
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
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    default:
                        valid = false;
                        break;
                }
            } while (option != 6);
        }
    }

    public static void createEntry() {
        
    }

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

    public static void main(String[] args) {
        startClient();
    }
}