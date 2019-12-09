package gabrielschubert.addressbook;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void printMain() {
        System.out.println("\t#~~ Address Book MAIN MENU ~~#\n\n" + "1.: MANAGE loaded address books\n"
                + "2.: CREATE a new address book\n" + "3.: DELETE an address book\n" + "4.:  SAVE  all address books to file\n" + "5.:  EXIT  out of program\n");
    }

    public static void printManager() {
        System.out.println("\t#~~ Address Book MANAGER ~~#\n\n" + "1.: CREATE an entry\n"
                + "2.: REMOVE an existing entry\n" + "3.:  EDIT  an existing entry\n"
                + "4.: SEARCH for a specific entry\n" + "5.:  LIST  an\\all address book(s)\n"
                + "6.:  SORT  an\\all address book(s)\n" + "7.:  EXIT  to main menu\n");
    }

    public static void printSearch() {

    }

    public static String[] personEntry() {
        Scanner scanner = new Scanner(System.in);
        String[] entry = new String[5];

        System.out.print("First Name....: ");
        entry[0] = scanner.nextLine();
        System.out.print("Last Name.....: ");
        entry[1] = scanner.nextLine();
        System.out.print("Phone Number..: ");
        entry[2] = scanner.nextLine();
        System.out.print("Address.......: ");
        entry[3] = scanner.nextLine();
        System.out.print("Email Address.: ");
        entry[4] = scanner.nextLine();

        return entry;
    }

    public static int getOption(Boolean valid, String extra) {
        System.out.print("# Choose " + (valid ? "an " : "a valid ") + "option " + extra + " > ");
        try {
            return (new Scanner(System.in).nextInt());
        } catch (InputMismatchException e) {
            return 0;
        }
    }

    public static int getOption(Boolean valid) {
        return getOption(valid, "\n");
    }

    public static String getString() {
        return (new Scanner(System.in).nextLine());
    }

    public static void clearTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void infoTimer(String info) {
        System.out.print("\n[INFO] " + info);
        for (int i = 0; i < 5; ++i) {
            try {
                Thread.sleep(150 * i);
            } catch (InterruptedException e) {
                System.out.println("[ERROR] Fatal thread sleep error. Exiting.");
                System.exit(1);
            }
            System.out.print(".");
        }
    }

    public static void waitClick() {
        infoTimer("Press ENTER to continue");
        System.out.println();
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}