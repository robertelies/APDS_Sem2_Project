package Table_Folder;

import java.util.ArrayList;
import java.util.Objects;

public class Table {

    private ArrayList<Node_TB>[] nodes_list;
    public int total_business;

    public Table(int total_business) {
        int [] nodes = new int[20];
        nodes_list = new ArrayList[total_business];
        for(int i = 0; i < total_business; i++) {
            this.nodes_list[i] = new ArrayList <Node_TB>();
        }
        this.total_business = total_business;
    }

    public void addBusiness(String name, String day, int price, inteT counterTime){


        addNode(new Business(name, day, price), getKey(name), counterTime);

    }

    public void deleteBusiness(String Name) {
        deleteNode(getKey(Name));
    }

    private void deleteNode(int key){
        if(key < total_business){
            //System.out.println(business.name + " with the key: " + key);
            this.nodes_list[key].removeIf(node -> node.key == key);

        }else{
           deleteNode(key/2);
        }
    }

    public Business searchBusiness(String name){


        return searchNode(getKey(name), null).business;
    }

    private Node_TB searchNode(int key, Node_TB node_ToReturn){
        if(key < total_business){
            for(Node_TB node : this.nodes_list[key]) {
                if(node.key == key) {
                    node_ToReturn = node;
                }
            }
        }else{
            searchNode(key/2, node_ToReturn);
        }

        if(node_ToReturn == null){
            return new Node_TB(new Business("NOT FOUND", "0", 0), -1);
        }else {
            return node_ToReturn;
        }
    }

    /**
     - As the Key can be bigger than total_business, we reduce it so it fits in a recursive way,
     that is why we have 2 functions: addBusiness and addNode, so we recursively call addNode
     reducing the Key.
     */
    private void addNode(Business business, int key, inteT counterTime){
        //time counter
        counterTime.x++;

        if(key < total_business){
            //System.out.println(business.name + " with the key: " + key);
            this.nodes_list[key ].add(new Node_TB(business, key));

        }else{
            //System.out.print(key + "/2 = " + key/2 + "-> " );
            addNode(business, key/2, counterTime);
        }
    }

    /**
    - This function returns a Key with the same digits as the T0tal users, for example is totaBusiness is: 323,
     a possible key is 234, 003, 986, 098...
     */
    private int getKey(String name) {
        int key = 0;
        for (int i = 0; i < name.length(); i++) {
            key += (name.charAt(i) * i);
        }
        return key % ((int) (Math.pow(10,String.valueOf(total_business).length())));
    }

    public Business getBusiness(String name){

        int key = getKey(name);
        while(key > total_business){
            key = key/2;
        }
        return nodes_list[key].get(0).business;
    }

    //---------------------------------------------------------------Count days
    public int[] countDays(){
        int[] counter = new int[7];
        for(int i = 0; i  < this.total_business; i++){
            for(Node_TB single_node: this.nodes_list[i]){
                if(Objects.equals(single_node.business.date, "Monday")){
                    counter[0]++;
                }else if(Objects.equals(single_node.business.date, "Tuesday")){
                    counter[1]++;
                }else if(Objects.equals(single_node.business.date, "Wednesday")){
                    counter[2]++;
                }else if(Objects.equals(single_node.business.date, "Thursday")){
                    counter[3]++;
                }else if(Objects.equals(single_node.business.date, "Friday")){
                    counter[4]++;
                }else if(Objects.equals(single_node.business.date, "Saturday")){
                    counter[5]++;
                }else if(Objects.equals(single_node.business.date, "Sunday")){
                    counter[6]++;
                }else{
                    System.out.println("Not able to read the day");
                }
            }
        }
        System.out.println("Monday: " + counter[0] + ", Tuesday: " + counter[1] + ", Wednesday: " + counter[2] +
                ", Thursday: " + counter[3]+ ", Friday: " + counter[4] + ", Saturday: " + counter[5] + ", Sunday: " + counter[6]);
        return counter;

    }
}
