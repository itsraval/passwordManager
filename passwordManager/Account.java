package src.passwordManager;

public class Account {
    private final String site;
    private final String url;
    private String user;
    private String email;
    private String password;

    Account (String site, String url, String user, String email, String password){
        this.site = site;
        this.url = url;
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public String showSiteUrl (){
        return this.site + "\t" + this.url;
    } 

    public void edit (String user, String email, String password){
        this.user = user;
        this.email = email;
        this.password = password;
    }

    public String printTextFormat(){
        return site + " " + url + " " + user + " " + email + " " + password + "\n\n";
    }

    public String printCipher(){
        String s = site.replace('§', ' ');
        String ur = url.replace('§', ' ');
        String us = user.replace('§', ' ');
        String text = "|-- " + s + "\n";
        if (!ur.equals("!")) text = text + "url:\t\t" + ur + "\n";
        if (!us.equals("!")) text = text + "user:\t\t" + us + "\n";    
        if (!email.equals("!")) text = text + "email:\t\t" + email + "\n";
        text = text + "password:\t********\n";
        return text;
    }

    @Override
    public String toString() {
        String s = site.replace('§', ' ');
        String ur = url.replace('§', ' ');
        String us = user.replace('§', ' ');
        String text = "|-- " + s + "\n";
        String ps = AES.decrypt(password);
        
        if (!ur.equals("!")) text = text + "url:\t\t" + ur + "\n";
        if (!us.equals("!")) text = text + "user:\t\t" + us + "\n";    
        if (!email.equals("!")) text = text + "email:\t\t" + email + "\n";
        if (!ps.equals("!")) text = text + "password:\t" + ps + "\n";
        return text;
    }
    
}
