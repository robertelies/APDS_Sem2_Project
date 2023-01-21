package Tree_Folder;

import java.time.Instant;

public class DIY_printer_tree {

    public DIY_printer_tree() {
    }

    public static void printFeedMenu(){
        System.out.println("\tA. Add algorithm");
        System.out.println("\tB. Remove algorithm");
        System.out.println("\tC. List algorithms");
        System.out.println("\tD. Search by timestamp (exact)");
        System.out.println("\tE. Search by timestamp (range)");
        System.out.println("\tF. Show Tree (Only useful for TreeXXS)\n");

        System.out.println("\tG. [AVL] Add algorithm");
        System.out.println("\tH. [AVL] Remove algorithm");
        System.out.println("\tY. [AVL] List algorithms");
        System.out.println("\tJ. [AVL] Search by timestamp (exact)");
        System.out.println("\tK. [AVL] Search by timestamp (range)");
        System.out.println("\tL. [AVL] Show Tree (Only useful for TreeXXS)\n");

        System.out.println("\tM. Go back\n");

        System.out.print("\tWhat functionality do you want to run? ");
    }

    public static void printAddNodeAtTheTree(){
        System.out.println("\tEnter algorithm's: identifier, name, language, cost & timestamp (ALL SEPARATED BY 1 SPACE)");
    }

    public static void printNodeTime(Node_T node){
        Instant instant = Instant.ofEpochSecond(node.timestamp);
        System.out.println(node.id + ") " + node.name + ": " + node.language + ", " + node.cost + ": " + instant);
    }

}
