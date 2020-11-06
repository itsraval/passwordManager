package src.passwordManager;

import java.awt.datatransfer.StringSelection;
import java.util.Random;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;

public class Features {
    
    public static void copyToClipboard(String text) {
        StringSelection stringSelection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

    }

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
}
