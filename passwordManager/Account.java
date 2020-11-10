package src.passwordManager;

/**
 * Account
 * 
 * @author  Ravizzotti Alessandro
 * @version 1.0
 * @since   06-11-2020 
 */
public class Account implements Comparable<Account>{
    private final String site;
    private final String url;
    private String user;
    private String email;
    private String password;

    /**
     * Set the accounts
     * @param site = site name
     * @param url = url of the web site
     * @param user = username used in the web site
     * @param email = email used in the web site
     * @param password = password used in the web site
     */
    Account (String site, String url, String user, String email, String password){
        this.site = site;
        this.url = url;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    @Override
    public int compareTo(Account a) {
        return this.site.compareTo(a.site);
        //Collections.sort(ar);
    }

    /**
     * @return Formatted string showing this site and this url.
     */
    public String showSiteUrl (){
        String s = site.replace('~', ' ');
        String ur = url.replace('~', ' ');
        String text = s + "\t";
        if (!ur.equals("!")) text = text + ur;
        return text;
    } 

    /**
     * @return Formatted string showing this site and this url.
     */
    public String showSiteUrlUserEmail (){
        String s = site.replace('~', ' ');
        String ur = url.replace('~', ' ');
        String us = user.replace('~', ' ');
        String text = "|-- " + s + "\n";
        if (!ur.equals("!")) text = text + "url:\t\t" + ur + "\n";
        if (!us.equals("!")) text = text + "user:\t\t" + us + "\n";    
        if (!email.equals("!")) text = text + "email:\t\t" + email + "\n";
        return text;
    } 

    /**
     * MODIFY this with the inputs.
     * @param user change this user 
     * @param email change this email
     * @param password change this password
     * 
     */
    public void edit (String user, String email, String password){
        this.user = user;
        this.email = email;
        this.password = password;
    }

    /**
     * @return Formatted string showing all the account parameters
     */
    public String printTextFormat(){
        return site + " " + url + " " + user + " " + email + " " + password + "\n\n";
    }

    /**
     * @return Formatted string showing all the account parameters without password (instead of password shows ********)
     */
    public String printCipher(){
        String s = site.replace('~', ' ');
        String ur = url.replace('~', ' ');
        String us = user.replace('~', ' ');
        String text = "|-- " + s + "\n";
        if (!ur.equals("!")) text = text + "url:\t\t" + ur + "\n";
        if (!us.equals("!")) text = text + "user:\t\t" + us + "\n";    
        if (!email.equals("!")) text = text + "email:\t\t" + email + "\n";
        text = text + "password:\t********\n";
        return text;
    }

    /**
     * Copy this.password decrypted to clipboard
     */
    public void passwordClipBoard(){
        Features.copyToClipboard(AES.decrypt(this.password));
    }
    
    @Override
    public String toString() {
        String s = site.replace('~', ' ');
        String ur = url.replace('~', ' ');
        String us = user.replace('~', ' ');
        String text = "|-- " + s + "\n";
        String ps = AES.decrypt(password);
        
        if (!ur.equals("!")) text = text + "url:\t\t" + ur + "\n";
        if (!us.equals("!")) text = text + "user:\t\t" + us + "\n";    
        if (!email.equals("!")) text = text + "email:\t\t" + email + "\n";
        if (!ps.equals("!")) text = text + "password:\t" + ps + "\n";
        return text;
    }
}
