// table import //
import org.nocrala.tools.texttablefmt.BorderStyle;
import org.nocrala.tools.texttablefmt.CellStyle;
import org.nocrala.tools.texttablefmt.ShownBorders;
import org.nocrala.tools.texttablefmt.Table;
import org.nocrala.tools.texttablefmt.CellStyle.HorizontalAlign;

// Security import //
import login.login;

// built-in import //
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    //---------------------Color------------------------//
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    //-------------------------------------------------//

    static Scanner scan = new Scanner(System.in);

    // datatype
    static int floor = 0, room = 0, gFloor, gRoom;
    static String houseMenu, choice;
    static String[][] guestHouse;



    public static boolean fixString(String checkNum){
        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(checkNum);
        boolean num = m.matches();
        return num;
    }
    static void menuGH(){
        //         Guest's House
        houseMenu = ANSI_YELLOW + """
                -------------------Guest’s House Management System----------------           \s
                        █▀ █▄█ █▀ ▀█▀ █▀▀ █▀▄▀█ ▄▄ █▀▄▀█ █▀▀ █▄░█ █░█
                        ▄█ ░█░ ▄█ ░█░ ██▄ █░▀░█ ░░ █░▀░█ ██▄ █░▀█ █▄█
                ------------------------------------------------------------------
                | 1- Check In                                                    |
                | 2- Checkout                                                    |
                | 3- Display                                                     |
                | 4- Search Guest’s Name                                         |
                | 5- Exit                                                        |
                ------------------------------------------------------------------""";
        System.out.println(houseMenu);
        System.out.print("%sChoose option (1-5): ".formatted(ANSI_PURPLE));
        choice = scan.next();

        // validation - Accept only number
        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher match = pattern.matcher(choice);
        boolean validate = match.matches();

        if (validate) {

            switch (choice) {
                case "1":
                    checkIn();
                    break;
                case "2":
                    checkOut();
                    break;
                case "3":
                    displayHouse();
                    break;
                case "4":
                    searchFR();
                    break;
                case "5":
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.println("%s=> Good bye, %sSee you again!".formatted(ANSI_RED, ANSI_BLUE));
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.exit(0);
                default :
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.println("%sPlease choose to correct option!".formatted(ANSI_RED));
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    menuGH();
            }
        }
        else {
            System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
            System.out.println("%s=> Wrong input, %sPlease try again!".formatted(ANSI_RED, ANSI_BLUE));
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            menuGH();
        }
    }

    static void checkIn(){

        String gFloor, gRoom;
        System.out.println("-------------------Check in from Guest’s House--------------------");
        System.out.print("Enter floor number: ");
        gFloor = scan.next();

        System.out.print("Enter room number: ");
        gRoom = scan.next();
        System.out.print("Guest's name: ");
        String gName = scan.next();

        Pattern pattern = Pattern.compile("^[0-9]*$");
        Matcher matchFloor = pattern.matcher(gFloor);
        Matcher matchRoom = pattern.matcher(gRoom);

        if (matchFloor.matches() && matchRoom.matches()) {

            int cFloor = Integer.parseInt(gFloor);
            int cRoom = Integer.parseInt(gRoom);

            guestHouse[cFloor - 1][cRoom - 1] = gName;
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.println(ANSI_BLUE + "=> Name: %s%s".formatted(ANSI_YELLOW, gName));
            System.out.println("%s=> Check in successfully".formatted(ANSI_BLUE));
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            menuGH();
        }
        else {
            System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
            System.out.println("%s=> Incorrect set up, %sPlease choose only number!".formatted(ANSI_RED, ANSI_BLUE));
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            menuGH();
        }
    }

    static void checkOut() {
        System.out.println("-------------------Checkout from Guest’s House--------------------");
        System.out.print("Enter floor number: ");
        int rFloor = scan.nextInt();

        System.out.print("Enter room Number: ");
        int rRoom = scan.nextInt();

        guestHouse[rFloor - 1][rRoom - 1] = null;

        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        System.out.println("%s=> Check out successfully".formatted(ANSI_BLUE));
        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        menuGH();
    }

    static void displayHouse(){

        CellStyle numberStyle = new CellStyle(HorizontalAlign.center);
        Table t = new Table(3,BorderStyle.UNICODE_ROUND_BOX,ShownBorders.ALL);

        System.out.println("---------------Display Guest’s House information------------------");
        for(int i = 0; i < guestHouse.length; i++) {
            System.out.print("Floor " + (i+1) + ": ");
            for(int j = 0; j < guestHouse[i].length; j++){
                System.out.print(guestHouse[i][j] + "\t");
                t.addCell("List of room in each floor",numberStyle);
                t.addCell("Floor",numberStyle);
                t.addCell("Rooms",numberStyle);
                t.addCell("",numberStyle);
                t.addCell("",numberStyle);
                t.addCell("",numberStyle);
                t.render();
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------");
        menuGH();
    }
    static void searchFR(){
        System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
        System.out.println("%s1. Search by name".formatted(ANSI_BLUE));
        System.out.println("%s2. Search by floor and room".formatted(ANSI_BLUE));
        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        System.out.print("%s-> Choose options: ".formatted(ANSI_PURPLE));
        int cO = scan.nextInt();
        if (cO==1){
            System.out.print("%s-> Enter guest's name to search: ".formatted(ANSI_PURPLE));
            String searchVal = scan.next();
            boolean found = false;

            for (int i = 0; i < guestHouse.length; i++) {
                for (int j = 0; j < guestHouse[i].length; j++) {

                    if (searchVal.equalsIgnoreCase(guestHouse[i][j])) {
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        System.out.println("=> Customers found".formatted(ANSI_BLUE));
                        System.out.println(ANSI_BLUE + "=> Name: %s%s".formatted(ANSI_YELLOW, searchVal));
                        System.out.println(ANSI_BLUE + "=> Floors: %s%s".formatted(ANSI_YELLOW, i+1));
                        System.out.println(ANSI_BLUE + "=> Rooms: %s%s".formatted(ANSI_YELLOW, j+1));
                        System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                        found = true;
                    }
                }
            }
            if (found == false){
                System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
                System.out.println("%s=> No customers found, %sPlease try again!".formatted(ANSI_RED, ANSI_BLUE));
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }
        }
        else if (cO==2){
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            System.out.print(ANSI_PURPLE + "Enter floor number: ");
            int sFloor = scan.nextInt();

            System.out.print(ANSI_PURPLE + "Enter room Number: ");
            int sRoom = scan.nextInt();

            if (guestHouse[sFloor - 1][sRoom - 1] != null) {
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                System.out.println("=> Customers found".formatted(ANSI_BLUE));
                System.out.println(ANSI_BLUE + "=> Name: %s%s".formatted(ANSI_YELLOW, guestHouse[sFloor - 1][sRoom - 1]));
                System.out.println(ANSI_BLUE + "=> Floors: %s%s".formatted(ANSI_YELLOW, sFloor));
                System.out.println(ANSI_BLUE + "=> Rooms: %s%s".formatted(ANSI_YELLOW, sRoom));
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }
            else {
                System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
                System.out.println("%s=> No customers found, %sPlease try again!".formatted(ANSI_RED, ANSI_BLUE));
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }
        }
        else {
            System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
            System.out.println("%s=> Incorrect options, %sPlease try again!".formatted(ANSI_RED, ANSI_BLUE));
            System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
        }
        menuGH();
    }

    public static void main(String[] args) {

        // ---------------------------------------------------------------------------------------------//
        // Login banner
        String loginBanner = ("""
        %s
        ------------------------------------------------------------------
                    █░░ █▀█ █▀▀ █ █▄░█ ▄▄ █▀█ ▄▀█ █▀▀ █▀▀
                    █▄▄ █▄█ █▄█ █ █░▀█ ░░ █▀▀ █▀█ █▄█ ██▄
        ------------------------------------------------------------------""").formatted(ANSI_PURPLE);
        System.out.println(loginBanner);

        // security
        // login.login();
        // ---------------------------------------------------------------------------------------------//

        String floor, room;
        // Set up floor and room
        while (true){

            // Set up banner
            String setupBanner = ("""
        %s
----------------------Setting up Guest’s House--------------------
█▀ █▀▀ ▀█▀ ▀█▀ █ █▄░█ █▀▀   █░█ █▀█   █░█ █▀█ ▀█▀ █▀▀ █░░
▄█ ██▄ ░█░ ░█░ █ █░▀█ █▄█   █▄█ █▀▀   █▀█ █▄█ ░█░ ██▄ █▄▄
------------------------------------------------------------------""").formatted(ANSI_PURPLE);
            System.out.println(setupBanner);
            System.out.print("-> Enter number of floors: ");
            floor = scan.next();

            System.out.print("-> Enter number of rooms: ");
            room = scan.next();
            Pattern pattern = Pattern.compile("^[0-9]*$");
            Matcher matchFloor = pattern.matcher(floor);
            Matcher matchRoom = pattern.matcher(room);

            if (matchFloor.matches() && matchRoom.matches()) {

                int cFloor = Integer.parseInt(floor);
                int cRoom = Integer.parseInt(room);

                if (cFloor > 0 && cRoom > 0){
                    guestHouse = new String[cFloor][cRoom];
                    int rooms = cFloor * cRoom;

                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.println(ANSI_BLUE + "=> Successfully set up the hotel");
                    System.out.println(ANSI_BLUE + "=> Floors: %s%s".formatted(ANSI_YELLOW, floor));
                    System.out.println(ANSI_BLUE + "=> Rooms: %s%s%s with %s%s%s rooms each floor".formatted(ANSI_YELLOW, rooms, ANSI_BLUE, ANSI_YELLOW, room, ANSI_BLUE));
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                    System.out.print("\n");
                    menuGH();
                }
                else {
                    System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
                    System.out.println("%s=> Incorrect set up, %sPlease choose atleast 1 floor and 1 room!".formatted(ANSI_RED, ANSI_BLUE));
                    System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
                }
            }
            else {
                System.out.println( ANSI_CYAN  + "-------------------------------------------------------------------");
                System.out.println("%s=> Incorrect set up, %sPlease choose only number!".formatted(ANSI_RED, ANSI_BLUE));
                System.out.println( ANSI_CYAN  + "------------------------------------------------------------------");
            }

        }
    }
}
