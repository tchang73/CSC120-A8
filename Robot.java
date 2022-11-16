import java.util.ArrayList;

public class Robot implements Contract{

    private double battery;
    private String name;
    private ArrayList<String> items;

    /**
     * constructor
     * @param name name of robot
     * @param battery battery percentage of robot
     */
    public Robot(String name, double battery){
        this.items = new ArrayList<String>(); //create arralylist for items that the robot will grab
        this.name = name;
        this.battery = battery;
        System.out.println("Robot " + name + " is " + battery + "% charged right now."); //default message when a robot is constructed
        this.checkBattery(); //check robot's current battery
        System.out.println("What would you like " + name + " to do? \n + grab(item) \n + drop(item) \n + examine(item) \n + use(item) \n + walk(direction) \n + fly(ft above ground, minutes)"); //offer user choices of actions to make the robot do
    }

    /**
     * "grab" item -- add item to the items arraylist; decrease battery; check battery percentage
     * @param item -- user input item they'd like the robot to get
     */
    public void grab(String item){
        items.add(item);
        System.out.println(name + " grabbed " + item + ".");
        this.shrink(); 
        this.checkBattery();
    }

    /**
     * "drop" item -- remove item from arralylist; decreade battery; check battery percentage
     * @param item -- item user would like robot to drop
     * @return
     */
    public String drop(String item){
        items.remove(item);
        this.shrink();
        System.out.println(name + " removed " + item + ".");
        this.checkBattery();
        return item;
    }

    /**
     * "examine" item -- check if item is still in arraylist
     * @param item -- item uesr would like to check
     */
    public void examine(String item){
        if (items.contains(item) == true){
            System.out.println(name + " has " + item + ".");
        } 
        if (items.contains(item) == false){
            System.out.println(name + " does not have " + item + ".");
        }
    }

    /**
     * print out prompt that robot is using an item if robot has this item, decrease and check battery percentage.
     * @param item -- item user would like the robot to use
     */
    public void use(String item){
        if (items.contains(item) == true){
        System.out.println(name + " is using " + item + "." );
        this.shrink();
        this.checkBattery();
        } 
        if (items.contains(item) == false){
            System.out.println("Sorry, " + name + " does not have this item yet. Please make " + name + " grab it first.");
        }
    }

    /**
     * if a direction is inputted, print out robot is walking in that direction, then decrease battery and check battery.
     * @param direction
     * @return
     */
    public boolean walk(String direction){
        if (direction == "left" | direction == "right"){
            System.out.println(name + " is walking " + direction + ".");
            System.out.println(name + " has stopped.");
            this.shrink();
            this.checkBattery();
            return true;
        }
        System.out.println(name + " is not walking.");
        return false;
    }

    /**
     * make robot fly for x ft above ground for y minutes.
     * @param x feet above ground
     * @param y minutes spent flying
     * @return
     */
    public boolean fly(int x, int y){
        if (x > 0 | y > 0){
            System.out.println(name + " is flying " + x + "ft above ground for " + y + " minutes.");
            int i = 0;
            while (i <= y){
                this.shrink();
                this.checkBattery();
                i++;
            }
            System.out.println(name + " has landed.");
            this.checkBattery();
            return true;
        }
        System.out.println(name + " is not flying.");
        return false;
    }

    /**
     * checks if battery is enough, low battery, or needs to be charged.
     */
    public void checkBattery(){
        if (battery <= 10 & battery > 0){
        System.out.println("Low battery, " + name + " will need to charge soon.");
    }
    if (battery == 0){
        System.out.println(name + " needs to be charged first before completeing the next task.");
        this.rest();
        this.grow();
    }
}

    /**
     * decreases battery by 5 
     * @return
     */
    public Number shrink(){
        battery -=5;
        System.out.println("battery is " + battery);
        return battery;
    }

    /**
     * charges battery back to 100
     * @return
     */
    public Number grow(){
        System.out.println(name + " is back to 100%!");
        return battery = 100;
    }

    /**
     * prints our prompt saying robot is charging
     */
    public void rest(){
        System.out.println(name + " is charging...");
    }

    /**
     * empties items arraylist
     */
    public void undo(){
        System.out.println("Clearing out inventory...");
        items.clear();
        System.out.println("Inventory cleared! " + name + " now has nothing in posession.");
    }

    /**
     * main method for testing
     * @param args
     */
public static void main(String[] args){
    Robot myRobot = new Robot("Steve", 25);
    myRobot.grab("apple");
    myRobot.examine("apple");
    myRobot.drop("apple");
    myRobot.examine("apple");
    myRobot.grab("apple");
    myRobot.fly(20,3);
    myRobot.walk("left");
    myRobot.walk("right");
    myRobot.undo();
    myRobot.examine("apple");
}
}
