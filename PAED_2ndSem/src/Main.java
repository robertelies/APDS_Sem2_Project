import Graph_Folder.DIY_printer_graph;
import Graph_Folder.Graph;
import Graph_Folder.ReadFile_G;
import RTree_Folder.*;
import Table_Folder.*;
import Tree_Folder.*;

import java.io.*;
import java.util.*;

/**********************************
 * THINGS TO FINISH
 * TREE- FLIP the tree for option C
 *
 */

public class Main  {

    public static final String FILE_NAME_GRAPH = "FileL";
    public static final String FILE_NAME_TREE = "treeM";
    private static final String FILE_NAME_TABLE = "tablesS";
    private static final String FILE_NAME_RTREE = "rtreeS";


    public static void main(String[] args) throws IOException {

        //variables
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int main_menu_option = 0;
        char followers_menu_option = 'p';

        //feed option
        char feed_menu_option = 'p';
        String[] newNode_T = new String[5];
        int eraseID = 0;
        int timestamp_main = 0;
        int timestamp_main_initial = 0;
        int timestamp_main_final = 0;

        //circle option
        char circle_menu_option = 'p';

        //business option
        char business_menu_option = 'p';




        //----------------------------------------------initialize the graph
        ReadFile_G file_g = new ReadFile_G(FILE_NAME_GRAPH);
        //long end_G = System.nanoTime();
        Graph graph = file_g.getGraph();


        //----------------------------------------------initialize the tree
        ReadFile_T file_t = new ReadFile_T(FILE_NAME_TREE);

        Tree tree = file_t.getTree();

        //----------------------------------------------initialize the AVLtree
        ReadFile_AVL_T file_avl_t = new ReadFile_AVL_T(FILE_NAME_TREE);
        AVLTree avlTree = file_avl_t.getTree();
        //TreePrinter.print(avlTree.superiorNode);
        //----------------------------------------------initialize the rtree
        ReadFile_R file_r = new ReadFile_R(FILE_NAME_RTREE);
        //long end_R = System.nanoTime();
        RTree rtree =file_r.getRTree();

        //long start_TB = System.nanoTime();
        //----------------------------------------------initialize the table
        ReadFile_TB file_tb = new ReadFile_TB(FILE_NAME_TABLE);
        //long end_TB = System.nanoTime();
        Table table = file_tb.getTable();








        //-------------------------------------------SCANNER FOR MAIN MENU
        System.out.println();
        Scanner scanner = new Scanner(System.in);

        int screen = 350;

        /*
        JFrame graph_G =new JFrame();
        graph_G.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        graph_G.add(new G(file_g.getcoords()));
        graph_G.setSize(screen*2,screen );
        graph_G.setLocation(0,0);
        graph_G.setTitle("GRAPH\tFILE: " + FILE_NAME_GRAPH);
        graph_G.setVisible(true);


        JFrame tree_G =new JFrame();
        tree_G.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tree_G.add(new G(file_t.getcoords()));
        tree_G.setSize(screen*2,screen);
        tree_G.setLocation(screen*2,0);
        tree_G.setTitle("TREE\tFILE: " + FILE_NAME_TREE);
        tree_G.setVisible(true);

        JFrame rtree_G =new JFrame();
        rtree_G.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rtree_G.add(new G(file_r.getcoords()));
        rtree_G.setSize(screen*2,screen );
        rtree_G.setLocation(0,screen +100);
        rtree_G.setTitle("R-TREE\tFILE: " + FILE_NAME_RTREE);
        rtree_G.setVisible(true);

        JFrame table_G =new JFrame();
        table_G.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        table_G.add(new G(file_tb.getcoords()));
        table_G.setSize(screen*2,screen);
        table_G.setLocation(screen*2,screen +100);
        rtree_G.setTitle("TABLE\tFILE: " + FILE_NAME_TABLE);
        table_G.setVisible(true);
         */

        do {
            DIY_printer_graph.printMainMenu();
            main_menu_option = Integer.parseInt(reader.readLine());
            switch (main_menu_option) {
                case 1:
                    do {
                        DIY_printer_graph.printFollowersMenu();
                        followers_menu_option = scanner.next().charAt(0);
                        switch (followers_menu_option) {
                            case 'A':
                                System.out.println("\n\tThe user who follows the most accounts is:\n");
                                DIY_printer_graph.printNode(file_g.getMostfollowing());
                                System.out.println("");
                                graph.BFS_notConnected(file_g.getMostfollowing());
                                break;
                            case 'B':
                                System.out.print("\nEnter your identifier: ");
                                graph.recommendUsersFiller( scanner.nextInt(), 5);

                                break;
                            case 'C':
                                graph.contDrama(file_g.getMostfollowing());
                                System.out.println();
                                break;
                            case 'D':
                                graph.HelpingWithNetwork(15,276);
                                break;

                        }
                    }while (followers_menu_option != 'E');

                    break;
                case 2:
                    do {
                        DIY_printer_tree.printFeedMenu();
                        feed_menu_option = scanner.next().charAt(0);

                        switch (feed_menu_option) {
                            case 'A':

                                DIY_printer_tree.printAddNodeAtTheTree();

                                newNode_T[0] = scanner.next();
                                newNode_T[1] = scanner.next();
                                newNode_T[2] = scanner.next();
                                newNode_T[3] = scanner.next();
                                newNode_T[4] = scanner.next();
                                //add the node
                                inte counterrrr = new inte();
                                //TreePrinter.print(tree.superiorNode);
                                tree.addNode(tree.superiorNode, new Node_T(Integer.parseInt(newNode_T[0]), newNode_T[1], newNode_T[2], newNode_T[3], Integer.parseInt(newNode_T[4]) ),counterrrr);
                                //TreePrinter.print(tree.superiorNode);
                                System.out.println("\nSuccessfully added!\n");


                                break;
                            case 'B':

                                System.out.print("\nEnter the algorithm's identifier: ");
                                eraseID = scanner.nextInt();
                                //TreePrinter.print(tree.superiorNode);
                                tree.eraseNode_T(tree.superiorNode, eraseID, tree.superiorNode,  0);
                                //TreePrinter.print(tree.superiorNode);
                                System.out.println("\nSuccessfully erased!\n");

                                break;
                            case 'C':
                                tree.printTreeNodes(tree.superiorNode);
                                break;
                            case 'D':
                                System.out.print("\nEnter the timestamp to search for: ");
                                timestamp_main = scanner.nextInt();
                                tree.searchForNode(tree.superiorNode, timestamp_main);
                                break;
                            case 'E':
                                System.out.print("\nEnter the timestamp to search for: [initial] ");
                                timestamp_main_initial = scanner.nextInt();
                                System.out.print("\nEnter the timestamp to search for: [final] ");
                                timestamp_main_final = scanner.nextInt();
                                tree.searchARange(timestamp_main_initial, timestamp_main_final);

                                break;
                            case 'F':
                                TreePrinter.print(tree.superiorNode);
                                break;

                            case 'G':

                                DIY_printer_tree.printAddNodeAtTheTree();

                                newNode_T[0] = scanner.next();
                                newNode_T[1] = scanner.next();
                                newNode_T[2] = scanner.next();
                                newNode_T[3] = scanner.next();
                                newNode_T[4] = scanner.next();
                                //add the node
                                //TreePrinter.print(avlTree.superiorNode);
                                avlTree.insertNode( Integer.parseInt(newNode_T[0]), newNode_T[1], newNode_T[2], newNode_T[3], Integer.parseInt(newNode_T[4]) );
                                //TreePrinter.print(avlTree.superiorNode);
                                System.out.println("\nSuccessfully added!\n");

                                break;
                            case 'H':

                                System.out.print("\nEnter the algorithm's identifier: ");
                                eraseID = scanner.nextInt();
                                //TreePrinter.print(avlTree.superiorNode);
                                avlTree.eraseNode_T(avlTree.superiorNode, eraseID, avlTree.superiorNode, 0);
                                //TreePrinter.print(avlTree.superiorNode);
                                System.out.println("\nSuccessfully erased!\n");

                                break;
                            case 'Y':
                                avlTree.printTreeNodes();
                                break;
                            case 'J':
                                System.out.print("\nEnter the timestamp to search for: ");
                                timestamp_main = scanner.nextInt();
                                avlTree.searchForNode(timestamp_main);
                                break;
                            case 'K':
                                System.out.print("\nEnter the timestamp to search for: [initial] ");
                                timestamp_main_initial = scanner.nextInt();
                                System.out.print("\nEnter the timestamp to search for: [final] ");
                                timestamp_main_final = scanner.nextInt();
                                avlTree.searchARange(timestamp_main_initial, timestamp_main_final);

                                break;

                            case 'L':
                                TreePrinter.print(avlTree.superiorNode);

                                break;
                            case 'M':
                                break;

                        }
                    }while (feed_menu_option != 'F');
                    break;
                case 3:
                    do{
                        DIY_printer_r_tree.printCircleMenu();
                        circle_menu_option = scanner.next().charAt(0);

                        switch (circle_menu_option){
                            case 'A':
                                String trash = scanner.nextLine();
                                System.out.print("Enter the X coordinate of the circle's center: ");
                                float xcord = scanner.nextFloat();
                                System.out.print("Enter the Y coordinate of the circle's center: ");
                                float ycord = scanner.nextFloat();
                                System.out.print("Enter the circle's radius: ");
                                float radius = scanner.nextFloat();
                                trash = scanner.nextLine();
                                System.out.print("Enter the circle's color: ");
                                String color = scanner.nextLine();

                                rtree.superiorRectangle.addDescendant(new Point_R(xcord, ycord, radius, color));

                                System.out.println("The circle was correctly added to the canvas.");
                                break;
                            case 'B':
                                trash = scanner.nextLine();
                                System.out.print("Enter the X coordinate of the circle's center: ");
                                float deleteXcord = scanner.nextFloat();
                                System.out.print("Enter the Y coordinate of the circle's center: ");
                                float deleteYcord = scanner.nextFloat();

                                //remove circle from canvas

                                System.out.print("The circle was correctly removed from the canvas.");
                                break;
                            case 'C':
                                System.out.print("Generating the canvas visualization...");
                                Visialization_RTree.draw(rtree);

                                break;
                            case 'D':
                                trash = scanner.nextLine();
                                System.out.print("Enter the rectangle's first point X: ");
                                float firstPoint_X = scanner.nextFloat();
                                System.out.print("Enter the rectangle's first point Y: ");
                                float firstPoint_Y = scanner.nextFloat();
                                System.out.print("Enter the rectangle's last point X: ");
                                float lastPoint_X = scanner.nextFloat();
                                System.out.print("Enter the rectangle's last point Y: ");
                                float lastPoint_Y = scanner.nextFloat();


                                rectangle_R newRec = new rectangle_R(new Point_R(firstPoint_X, firstPoint_Y, 99.99F, "color"));
                                newRec.addDescendant( new Point_R(lastPoint_X , lastPoint_Y, 99.99F, "color"));

                                rtree.superiorRectangle.searchByArea(newRec, rtree);
                                System.out.println("A total of " + rtree.searchNumber +
                                        " Points inside the Area (" + firstPoint_X + ", " + firstPoint_Y + ")->(" + lastPoint_X + ", " + lastPoint_Y + ")");
                                System.out.println("Possible points: " + rtree.searchNumberMAX);

                                //print circles in the area

                                break;
                            case 'E':
                                trash = scanner.nextLine();
                                System.out.print("Enter the X coordinate of the search circle's center: ");
                                float search_xcord = scanner.nextFloat();
                                System.out.print("Enter the Y coordinate of the search circle's center: ");
                                float search_ycord = scanner.nextFloat();
                                System.out.print("Enter the search circle's radius: ");
                                float search_radius = scanner.nextFloat();
                                trash = scanner.nextLine();
                                System.out.print("Enter the search circle's color: ");
                                String search_color = scanner.nextLine();
                                System.out.print("Enter the criteria: (Float between 0.0 -> 0.5) : ");
                                float criteria = scanner.nextFloat();
                                System.out.println("\nThe following circles are nearby and look similar:\n");


                                rtree.superiorRectangle.searchSimilar(new Point_R(search_xcord, search_ycord, search_radius, search_color), criteria, rtree);

                                break;
                        }
                    }while(circle_menu_option != 'F');
                    break;
                case 4:
                    do{
                        DIY_printer_table.printBusinessMenu();
                        business_menu_option = scanner.next().charAt(0);

                        switch (business_menu_option){
                            case 'A':
                                String trash = scanner.nextLine();
                                System.out.print("Enter the name of the business to add: ");
                                String name = scanner.nextLine();
                                System.out.print("Enter the day of the week they are interested in: ");
                                String day = scanner.nextLine();
                                System.out.print("Enter the price they are willing to pay, in Euros: ");
                                int price = scanner.nextInt();
                                long startTime = System.nanoTime();
                                inteT ahhh = new inteT();
                                table.addBusiness(name, day, price, ahhh);
                                System.out.println("\nProgram time: "+((System.nanoTime()- startTime)/1000000000 )+"s "+(((System.nanoTime()- startTime)/1000000 )%1000)+"ms "+(((System.nanoTime()- startTime)/1000 )%1000)+"us");

                                System.out.println("The business has been correctly added to the advertising management system.");
                                break;
                            case 'B':
                                trash = scanner.nextLine();
                                System.out.print("Enter the name of the business to delete: ");
                                String deletename = scanner.nextLine();

                                table.deleteBusiness(deletename);

                                System.out.println("The business has been correctly deleted from the advertising management system.");
                                break;
                            case 'C':
                                trash = scanner.nextLine();
                                System.out.print("Enter the name of the business to query: ");
                                String queryname = scanner.nextLine();


                                Business selected = table.searchBusiness(queryname);
                                System.out.println(selected.name + ", " +selected.date + ", " + selected.price );

                                break;
                            case 'D':
                                System.out.println("Generating histogram...");

                                Visialization_Table.draw(table.countDays());

                                break;
                        }
                    }while(business_menu_option != 'E');
                    break;
            }

        }while(main_menu_option != 5);
    }

}