package src.passwordManager;

import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

/**
 * Includes 2 features to the main program.
 * 
 * @author  Ravizzotti Alessandro
 * @version 1.0
 * @since   06-11-2020 
 */
public class Features {
    
    /**
     * Copy the input string to the clipboar.
     * @param text will be copied to the clipboard.
     */
    public static void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     * Generate a random strong password made by 16 char.
     * 8 characters are alphabetic
     * 8 characters are numeric / simbols
     * @return the password generated
     */
    public static String passwordGenerator() {
        char num[] = new char[8];
        char alfa[] = new char[8];
        char pas[] = new char[16];
        Random rand = new Random();
        for (int i=0; i<8; i++){
            num[i] = (char)(rand.nextInt(32)+33);
            alfa[i] = (char)(rand.nextInt(61)+65);
        }
        int n=0;
        int a=0;
        for (int i=0; i<16; i++){
            int k = rand.nextInt(2);
            if (k==0 && n<8) {
                pas[i] = num[n];
                n++;
            }else {
                if (a<8){
                    pas[i] = alfa[a];
                    a++;
                }else {
                    pas[i] = num[n];
                    n++;
                }
            }
        }
        return new String(pas);
    }

    /**
     * Creates readme.txt file
     */
    public static void createReadme() {
        final String readmePath = "C:\\passwordManager\\README.txt";
        String text = "WARNING!!!\nDO NOT delete files in this folder.\nIf you do, you will reset the 'Password Manager'\n(so you will lose all your saved accounts and your login credentials).\n\n\nThis program is made by Alessandro Ravizzoti.\nCopyright © 2020.";
        try {
            File readmeFile = new File(readmePath); 
            if (!readmeFile.exists()){
                readmeFile.createNewFile();
                FileWriter fWriter = new FileWriter(readmeFile);
                fWriter.write(text);
                fWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
