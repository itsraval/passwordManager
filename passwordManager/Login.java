package src.passwordManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Login screen and access with password management
 * 
 * @author  Ravizzotti Alessandro
 * @version 1.0
 * @since   06-11-2020 
 */
public class Login {
    private String user;
    private String password;

    /**
     * Create a new Login with
     *  this.user = @param user
     *  this.password = hashed SHA256 @param password
     */
    private Login(String user, String password) {
        this.user = user;
        this.password = Cipher.SHA256(password);
        AES.setKey(password);
    }

    /**
     * Modify the login user.
     * @param scan to the input
     * @param accountFile account file
     * @param cText accounts list
     */
    public void changeLoginUser(Scanner scan, File accountFile, ArrayList<Account> cText) {
        System.out.println("Updating Login User: ");
        System.out.print("New Username: ");
        String user = scan.nextLine();
        this.user = user;
        String text = Cipher.SHA256(user) + "\n";
        text = text + this.password + "\n\n";
        for (Account account : cText) {
            text = text + account.printTextFormat();
        }
        try {
            FileWriter fWriter = new FileWriter(accountFile);
            fWriter.write(text);
            fWriter.close();
            System.out.println("Update successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Modify the login password.
     * @param scan to the input
     * @param accountFile account file
     * @param cText accounts list
     */
    public void changeLoginPassword(Scanner scan, File accountFile, ArrayList<Account> cText) {
        System.out.println("Updating Login Password: ");
        String password;
        String password2;
        do {
            System.out.print("New Password (better with 8 or more characters, use numbers and symbol): ");
            password = scan.nextLine();
            System.out.print("Retype your new password: ");
            password2 = scan.nextLine();
        } while (!(password.equals(password2)));
        this.password = Cipher.SHA256(password);

        String text = Cipher.SHA256(this.user) + "\n";
        text = text + this.password + "\n\n";
        for (Account account : cText) {
            text = text + account.printTextFormat();
        }
        try {
            FileWriter fWriter = new FileWriter(accountFile);
            fWriter.write(text);
            fWriter.close();
            System.out.println("Update successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * MODIFY an account not the login account
     * @param accountFile account file
     * @param cText accounts list
     */
    public void accountModify (File accountFile, ArrayList<Account> cText) {
        String text = Cipher.SHA256(this.user) + "\n";
        text = text + this.password + "\n\n";
        for (Account account : cText) {
            text = text + account.printTextFormat();
        }
        try {
            FileWriter fWriter = new FileWriter(accountFile);
            fWriter.write(text);
            fWriter.close();
            System.out.println("Update successful!");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }

    /**
     * Check if the password @param cPassword = this.passoword and @param cUser = this.users
     * @param cUser hashed user
     * @param cPassword hashed password
     * @return true if the passwords and users are equals
     * @return false if the passwords and users are not equals
     */
    private boolean check(String cUser, String cPassword){
        if ((Cipher.SHA256(this.user).equals(cUser)) && (this.password.equals(cPassword))) return true;
        else return false;
    }

    /**
     * Check if the password @param cPassword = this.passoword
     * @param password hashed password
     * @return true if the passwords are equals
     * @return false if the passwords are not equals
     */
    public boolean checkPassword(String password){
        return this.password.equals(Cipher.SHA256(password));
    }

    /**
     * First Login screen if there is no file this has to be called in main.
     * @param scan takes input
     * @param accountFile accounts file
     */
    public static void firstLogin(Scanner scan, File accountFile){
        System.out.println("Creating Login Credentials: ");
        System.out.print("Username: ");
        String user = scan.nextLine();
        String password;
        String password2;
        do {
            System.out.print("Password (better with 8 or more characters, use numbers and symbol): ");
            password = scan.nextLine();
            System.out.print("Retype your password: ");
            password2 = scan.nextLine();
        }while (!(password.equals(password2)));
        try {
            FileWriter fWriter = new FileWriter(accountFile);
            fWriter.write(Cipher.SHA256(user) + "\n" + Cipher.SHA256(password) + "\n");
            fWriter.close();
            System.out.println("Credentials created with success!\n\n");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Normal login screen
     * @param i used to check if i>4 terminate the program
     * @param scan takes input
     * @param cUser used to make new Login
     * @param cPassword used to make new Login
     * @return the new Login.
     */
    public static Login loginScreen(int i, Scanner scan, String cUser, String cPassword){
        if (i == 5) {
            scan.close();
            System.exit(0);
        }
        if (i != 0) System.out.println("\n\n" + (5-i) + " Attempts left...");
        System.out.println("---LOGIN---");
        System.out.print("Username: ");
        String user = scan.nextLine();
        System.out.print("Password: ");
        String password = scan.nextLine();
        System.out.println("\n");
        Login log = new Login(user, password);
        if (log.check(cUser, cPassword) == false) loginScreen(i+1, scan, cUser, cPassword);
        else   {
            System.out.println("Login Success.\n\n");
        }
        return log;
    }
}
