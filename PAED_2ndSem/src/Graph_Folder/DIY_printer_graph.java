package Graph_Folder;

public class DIY_printer_graph {

    public DIY_printer_graph() {

    }
    public static void printMainMenu(){
        System.out.print(".* LinkedTree *.");
        System.out.println(" ");
        System.out.println("1. Followers (Graphs)");
        System.out.println("2. Feed (Trees)");
        System.out.println("3. Canvas (R-Trees)");
        System.out.println("4. Advertising (Tables)");
        System.out.println(" ");
        System.out.println("5. Exit");
        System.out.println(" ");
        System.out.print("Choose an option: ");
    }

    public static void printFollowersMenu(){
        System.out.println("\tA. Explore the network");
        System.out.println("\tB. Suggest users");
        System.out.println("\tC. Contextualize drama");
        System.out.println("\tD. Networking\n");
        System.out.println("\tE. Go back\n");
        System.out.print("\tWhat functionality do you want to run? ");
    }



    public static void printGraph(Graph graph){
        for(Node_G sn : graph.getListNodes()) {
            if(sn != null) {
                System.out.print(sn.getId() + ", " + sn.getName() + ", " + sn.getAlias());
                for (String interest : sn.getInterests()) System.out.print(", " + interest);
                System.out.print("\n    Following:");
                for (Edge_G interaction : sn.getEdges()) System.out.print(" " + interaction.getFollowing().getName());
                System.out.println("\n");
            }

        }
    }

    public static void printNode(Node_G node){

        if(node != null) {
            System.out.println("    " + node.getId() + " - " + node.getName() + " (" + node.getAlias() + ")");
            System.out.print("    Interests: ");
            for (String interest : node.getInterests()) System.out.print(" -" + interest);
            System.out.println(" ");
        }


    }

    public static void printRecommendedUser(Node_G node, recommendUsers rec){
        System.out.println(node.getId() + ") " + node.getName() + " - " + rec.getNum_followedByFollowing() + " FollowByFollowed: ");
        for(Node_G node_2 : rec.getFollowedByFollowing()){
            if(node_2 != null) {
                System.out.println("\t\t\t\t\t\t\t\t\t\t\t- " + node_2.getId() + ") " + node_2.getName());
            }
        }
        if(rec.getNum_similarInterests() > 0) {

            System.out.println("\t\t\t\t"  + rec.getNum_similarInterests() + " similar interets:" + rec.getSimilarInterests());
        }

        if(rec.getIsFollowed()){
            System.out.println("is followed by the selected\n\n");
        }
    }
}
