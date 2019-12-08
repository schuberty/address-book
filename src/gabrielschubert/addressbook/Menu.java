package gabrielschubert.addressbook;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void printMain() {
        System.out.println("\t#~~ Address Book MAIN MENU ~~#\n\n" +
                "1.: MANAGE loaded address books\n" +
                "2.: CREATE a new address book\n" +
                "3.: DELETE an address book\n" +
                "4.:  EXIT  out of program\n");
    }

    public static void printManager() {
        System.out.println(
                "\t#~~ Address Book MANAGER ~~#\n\n" +
                "1.: CREATE an entry\n" +
                "2.: REMOVE an existing entry\n" +
                "3.:  EDIT  an existing entry\n" +
                "4.: SEARCH for a specific entry\n" +
                "5.:  SORT  an\\all address book(s)\n" +
                "6.:  EXIT  to main menu\n");
    }

    public static void printEdit() {

    }

    public static void printSearch() {

    }

    public static int getOption(Boolean valid) {
        System.out.print("# Choose " + (valid ? "an " : "a valid ") + "option\n" + " > ");
        try {
            return (new Scanner(System.in).nextInt());
        } catch (InputMismatchException e) {
            return 0;
        }
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
                System.out.println("[ERROR] Thread sleep error. Exiting.");
                System.exit(1);
            }
            System.out.print(".");
        }
    }
}