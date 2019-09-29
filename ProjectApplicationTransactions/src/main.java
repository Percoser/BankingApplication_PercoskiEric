import java.util.Scanner;
import javax.swing.JOptionPane;
import java.io.*;

public class main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        Scanner inputone = new Scanner(System.in);
        Scanner inputtwo = new Scanner(System.in);

        char repeat = 'Y';
        String cont;
        char contChar;
        int choice;
        String userName;
        System.out.println("Welcome to CIS-2348 Banking System!");
        System.out.println("Enter your username: ");
        userName = inputtwo.nextLine();
        File mainFile = new File(userName + ".txt");
        int transNum = 1;
        String action;
        double currentBal = 1000.00;
        boolean check= checkExist(mainFile, userName);


        while (repeat!='N') {

            System.out.println("Enter your Option in a number:\n" + "1. Display balance\n" + "2. Withdraw amount\n" + "3. Deposit amount\n" + "4. Exit Application");
            choice = input.nextInt();

            switch(choice){
                case 1:
                    File checkfile = new File(userName + ".txt");
                    Scanner readfile = new Scanner(checkfile);
                    System.out.println("You have chosen to Display Balance... Is this correct? Y/N");
                    cont = inputone.nextLine();
                    contChar = cont.charAt(0);
                    if(contChar=='Y') {
                        System.out.println("Displaying Balance...");
                        while(readfile.hasNext()) {
                            String transLine = readfile.nextLine();
                            System.out.println(transLine);
                        }readfile.close();
                        break;
                    }else break;
                case 2:
                    System.out.println("You have chosen to Withdraw Money... Is this correct? Y/N");
                    cont = inputone.nextLine();
                    contChar = cont.charAt(0);
                    if(contChar=='Y') {
                        System.out.println("How much would you like to withdraw?...");
                        int subamount=inputtwo.nextInt(); //is there a .nextDouble() method? how can we get decimals input
                        if(subamount<=currentBal) {
                            double startBal = currentBal;
                            currentBal = (startBal - subamount);
                            //System.out.println(currentBal); //will be replaced with a write-file technique
                            FileWriter temp = new FileWriter (userName + ".txt", true);
                            PrintWriter writefile = new PrintWriter(temp);
                            transNum++;
                            action = "Withdrawl";
                            writefile.println(transNum + ". " + action + ": " + "-" +subamount);
                            writefile.println("Remaining Balance: " + currentBal);
                            writefile.close();
                        }
                        else {
                            System.out.println("Insufficient funds, please try again...");
                        }
                        break;
                    }else break;
                case 3:
                    System.out.println("You have chosen to Deposit Money... Is this correct? Y/N");
                    cont = inputone.nextLine();
                    contChar = cont.charAt(0);
                    if(contChar=='Y') {
                        System.out.println("How much would you like to Deposit?...");
                        int depositamount=inputtwo.nextInt(); //is there a .nextDouble() method? how can we get decimals input
                        if(depositamount>=0) {
                            double startBal = currentBal;
                            currentBal = (startBal + depositamount);
                            //System.out.println(currentBal); //will be replaced with a write-file technique
                            FileWriter temp = new FileWriter (userName + ".txt", true);
                            PrintWriter writefile = new PrintWriter(temp);
                            transNum++;
                            action = "Deposit";
                            writefile.println(transNum + ". " + action + ": " + "+" +depositamount);
                            writefile.println("Remaining Balance: " + currentBal);
                            writefile.close();
                        }
                        break;
                    }else break;
                case 4:
                    System.out.println("GoodBye!");
                    repeat = 'N';
                    break;
            }
        }
    }
    public static boolean checkExist(File mainFile, String user) throws IOException{
        if(!mainFile.exists()){
            System.out.println("The account has been created with a starting balance of $1,000.00...");
            PrintWriter accountFile = new PrintWriter(user + ".txt");
            int transNum=1;
            String action="Initial Balance";
            double currentBal=1000.00;
            accountFile.println(transNum + ". " + action + ": " + currentBal);
            accountFile.close();

            return false;
        }
        else {
            System.out.println("Your account was found successfully...");
            return true;
        }
    }
}
