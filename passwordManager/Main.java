package src.passwordManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This is the main class
 * 
 * @author  Ravizzotti Alessandro
 * @version 1.0
 * @since   06-11-2020 
 */
public class Main {
    final static public String filePath = "C:\\passwordManager\\accountsPasswordManger.txt";
    final static Scanner scan = new Scanner(System.in);
    static LocalDateTime maxTime;
    static ArrayList<Account> cText = new ArrayList<Account>();

    /**
     * Print the title
     */
    public static void title() {
        System.out.println("\n\n");
        System.out.println("========================================================================================");
        System.out.println("========================================================================================");
        System.out.println("    PPPPPPP  AAAAAAA  SSSSSSS  SSSSSSS  WW     WW     WW  OOOOOOO  RRRRRRR  DDDDD       ");
        System.out.println("    PP   PP  AA   AA  SSS      SSS      WW   WW  WW   WW  OO   OO  RR   RR  DD   DD     ");
        System.out.println("    PPPPPPP  AAAAAAA  SSSSSSS  SSSSSSS  WW   WW  WW   WW  OO   OO  RR   RR  DD   DD     ");
        System.out.println("    PP       AA   AA  SSSSSSS  SSSSSSS  WW   WW  WW   WW  OO   OO  RRRRRRR  DD   DD     ");
        System.out.println("    PP       AA   AA      SSS      SSS   WW WW    WW WW   OO   OO  RR RR    DD   DD     ");
        System.out.println("    PP       AA   AA  SSSSSSS  SSSSSSS    WWW      WWW    OOOOOOO  RR   RR  DDDDD       \n");

        System.out.println("             MM   MM  AAAAAAA  NN   NN  AAAAAAA  GGGGGGG  EEEEEEE  RRRRRRR              ");
        System.out.println("             MMM MMM  AA   AA  NNN  NN  AA   AA  GG       EE       RR   RR              ");
        System.out.println("             MM M MM  AAAAAAA  NNNN NN  AAAAAAA  GG       EEEEEE   RR   RR              ");
        System.out.println("             MM   MM  AA   AA  NN NNNN  AA   AA  GG  GGG  EEEEEE   RRRRRRR              ");
        System.out.println("             MM   MM  AA   AA  NN  NNN  AA   AA  GG   GG  EE       RR RR                ");
        System.out.println("             MM   MM  AA   AA  NN   NN  AA   AA  GGGGGGG  EEEEEEE  RR   RR              ");
        System.out.println("========================================================================================");
        System.out.println("========================================================================================\n\n");
    }

    /**
     * It asks to insert the password because admin required
     * @param log used to check the password
     */
    public static void mustCheckPassword(Login log) {
        int j = 0;
        String password = "";
        while (!log.checkPassword(password)){
            System.out.print("Password required: ");
            password = scan.nextLine();
            System.out.println("\n");
            if (j > 4) {
                scan.close();
                System.exit(0);
            }
            j++;
        }
    }

    /**
     * It asks to insert the password because 5 minutes have passed
     * @param log used to check the password
     */
    public static void inTime(Login log) {
        LocalDateTime nowTime = LocalDateTime.now();
        int i = nowTime.compareTo(maxTime);
        if (i < 0) return;
        else {
            int j = 0;
            String password = "";
            while (!log.checkPassword(password)) {
                System.out.print("Insert Login Password (Time expired): ");
                password = scan.nextLine();
                System.out.println("\n");
                if (j > 4) {
                    scan.close();
                    System.exit(0);
                }
                j++;
            }
            maxTime = LocalDateTime.now().plusMinutes(5);
        }
    }

    /**
     * Shows the account list without the password
     */
    public static void showAccountList() {
        for (Account account : cText) {
            System.out.println(account.printCipher());  
        }
    }

    /**
     * Shows the account list without the password and it will be copy to your clipboard
     */
    public static void showAccountListClipboard() {
        for (Account account : cText) {
            System.out.println(cText.indexOf(account) + " " + account.showSiteUrlUserEmail());  
        }
        System.out.print("Account's password to copy: ");
        int i = scan.nextInt();
        scan.nextLine();
        cText.get(i).passwordClipBoard();
        System.out.println("The password is now copied in your clipboard!");
    }

    /**
     * Shows the account list
     */
    public static void showAccountCredential() {
        for (Account account : cText) {
            System.out.println(account);  
        }
    }

    /**
     * You can add an account to the @param accountFile
     */
    public static void addAccount(File accountFile) {
        System.out.println("--Add New Account-- (just press enter if you do not want to compile the line)");
        System.out.print("Site: ");
        String site = scan.nextLine();
        System.out.print("Url: ");
        String url = scan.nextLine();
        System.out.print("User: ");
        String user = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.println("Do you want to use a strong generated password?");
        System.out.println("Type Y if you want to use the password generator.");
        System.out.println("Type anything if you DO NOT want to use the password generator.");
        String gYN = scan.nextLine();
        String password;
        if (gYN.equals("Y") || gYN.equals("y")){
            password = Features.passwordGenerator();
            System.out.println("Your new password is: " + password);
            Features.copyToClipboard(password);
            System.out.println("The password is now copied in your clipboard!");
        }else{
            System.out.print("Password: ");
            password= scan.nextLine();
        }    
        if (site.equals("")) site = "!";
        if (url.equals("")) url = "!";
        if (user.equals("")) user = "!";
        if (email.equals("")) email = "!";
        if (password.equals("")) password = "!";
        site = site.replace(' ', '§');
        url = url.replace(' ', '§');
        user = user.replace(' ', '§');
        password = AES.encrypt(password);
        Account a = new Account(site, url, user, email, password);
        cText.add(a);
        try {
            FileWriter fWriter = new FileWriter(accountFile, true);
            fWriter.write(a.printTextFormat());
            fWriter.close();
            System.out.println("Account added successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * You can modify an account on the @param accountFile
     * @param log required to complete the action
     */
    public static void modifyAccount(File accountFile, Login log) {
        for (Account account : cText) {
            System.out.println(cText.indexOf(account) + " " + account);  
        }
        System.out.print("Account number to edit: ");
        int i = scan.nextInt();
        scan.nextLine();
        System.out.println(cText.get(i).showSiteUrl());
        System.out.print("User: ");
        String user = scan.nextLine();
        System.out.print("Email: ");
        String email = scan.nextLine();
        System.out.println("Do you want to use a strong generated password?");
        System.out.println("Type Y if you want to use the password generator.");
        System.out.println("Type anything if you DO NOT want to use the password generator.");
        String gYN = scan.nextLine();
        String password;
        if (gYN.equals("Y") || gYN.equals("y")){
            password = Features.passwordGenerator();
            System.out.println("Your new password is: " + password);
            Features.copyToClipboard(password);
            System.out.println("The password is now copied in your clipboard!");
        }else{
            System.out.print("Password: ");
            password= scan.nextLine();
        }    
        if (user.equals("")) user = "!";
        if (email.equals("")) email = "!";
        if (password.equals("")) password = "!";
        user = user.replace(' ', '§');
        password = AES.encrypt(password);
        cText.get(i).edit(user, email, password);
        log.accountModify(accountFile, cText);
    }

    /**
     * Delete an account from the @param accountFile
     * @param log required to complete the action
     */
    public static void deleteAccount(File accountFile, Login log) {
        for (Account account : cText) {
            System.out.println(cText.indexOf(account) + " " + account);  
        }
        System.out.print("Account number to delete: ");
        int i = scan.nextInt();
        scan.nextLine();
        cText.remove(i);
        log.accountModify(accountFile, cText);
    }

    /**
     * PRINTS about infos
     */
    public static void infos() {
        System.out.println("\nThis program is made by Alessandro Ravizzoti.");
        System.out.println("\nwww.alessandro.ravizzotti.tk");
        System.out.println("Copyright © 2020.");
    }

    /**
     * Used in menu
     * You can go back to the menu or exit the program correctly
     */
    private static void exitOrMenu() {
        System.out.print("\n\nPress 'b' to go back to the menu or press 'e' to exit... ");
        char c = 'a';
        while (c != 'b' && c != 'e') {
            c = scan.next().charAt(0);
            if (c == 'b')
                return;
            else if (c == 'e') {
                scan.close();
                System.exit(0);
            }
        }
    }

    /**
     * Main menu
     * @param log required to complete the action
     * @param accountFile required to complete the action
     */
    public static void menu(Login log, File accountFile) {
        System.out.println("0 - Show Accounts List");
        System.out.println("1 - Show Accounts List and copy password to clipboard");
        System.out.println("2 - Show Accounts List with Credential");
        System.out.println("3 - Add Account");
        System.out.println("4 - Modify account");
        System.out.println("5 - Delete Account");
        System.out.println("6 - Change Login Username");
        System.out.println("7 - Change Login Password");
        System.out.println("8 - Infos");
        System.out.println("9 - Exit\n");
        System.out.print("Insert number: ");
        try {
            int i = scan.nextInt();
            while (i < 1 || i > 9)
                i = scan.nextInt();
            scan.nextLine();
            System.out.print("\n\n");
            switch (i) {
                case 0:
                    inTime(log);
                    showAccountList();
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 1:
                    inTime(log);
                    showAccountListClipboard();
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 2:
                    inTime(log);
                    showAccountCredential();
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 3:
                    inTime(log);
                    addAccount(accountFile);
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 4:
                    inTime(log);
                    modifyAccount(accountFile, log);
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 5:
                    mustCheckPassword(log);
                    deleteAccount(accountFile, log);
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 6:
                    mustCheckPassword(log);
                    log.changeLoginUser(scan, accountFile, cText);
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 7:
                    mustCheckPassword(log);
                    log.changeLoginPassword(scan, accountFile, cText);
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 8:
                    infos();
                    exitOrMenu();
                    menu(log, accountFile);
                    break;
                case 9:
                    scan.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
            
        } catch (InputMismatchException e) {
            System.out.println("An error occurred. Input not a number.");
            e.printStackTrace();
            System.exit(0);
        }

    }

    public static void main(String[] args) {
        title();
        try {
            File accountFile = new File(filePath);  
            if (!accountFile.exists()) {
                if (!accountFile.getParentFile().exists()) accountFile.getParentFile().mkdir();
                accountFile.createNewFile();
                Features.createReadme();
                System.out.println("File created: " + accountFile.getName() + " successfully!");
                Login.firstLogin(scan, accountFile);
            }
            Scanner fileScan = new Scanner(accountFile);
            String cUser = fileScan.nextLine();
            String cPassword = fileScan.nextLine();
            while (fileScan.hasNextLine()) {
                String line = fileScan.nextLine();
                if (!line.equals("")) {
                    String accountFields[] = line.split("\\s+");
                    cText.add(new Account(accountFields[0], accountFields[1], accountFields[2], accountFields[3], accountFields[4]));
                }              
            }
            fileScan.close();

            Login log = Login.loginScreen(0, scan, cUser, cPassword);
            maxTime = LocalDateTime.now().plusMinutes(5);
            menu(log, accountFile);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
