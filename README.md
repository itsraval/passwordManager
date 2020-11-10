# passwordManager
Local Password Manager. Just command line interface... for now :)

You can save as many accounts as you want (based on your local storage).
An account is made by
* site name
* url
* user
* email
* password

or just by some of them.  
All data will only be saved on local store in "C:\passwordManager".

## Cryptography
This program uses SHA256 to store user and password to login in the app.
This program uses AES to encrypt every stored password.
* SHA256
* AES

## Menu
* Show Accounts List
* Show Accounts List and copy password to clipboard
* Show Accounts List with Credential
* Add Account
* Modify account
* Delete Account
* Change Login Username
* Change Login Password
* Infos

### Show Accounts List
This function shows all accounts credentials but password is replaced with ********.

### Show Accounts List and copy password to clipboard
This function shows all the accounts without the password.   
You can copy 1 password to clipboard.

### Show Accounts List with Credential
This function shows all the accounts with all the credentials

### Add Account
You can add a new account.

### Modify account
You can edit an account (just 1 at the time).
You can just modify user, email and password.

### Delete Account
You can delete an account (just 1 at the time).

### Change Login Username
You can change the login password.

### Change Login Password
You can change the login password.

### Infos
Shows creator infos.

## Other Featueres
If you need to delete an account or change login credential the program will ask you to type the password to make sure it is you.
if the program has been open for more than 5 minutes and you want to select something from the menu, before you can access that area, it will ask you to type the password to make sure it is you.  

When you are adding or modifing an account you can choose to generate a strong password with the integrated "Password Generator".  
When you are adding or modifing an account, the new password will be copied into your clipboard.

## Password Generator
This will generate a random strong password made by 16 characters.   
* 8 of those will be alphabetic / symbol (ASCII 65:126)
* 8 of those will be numeric / symbol (ASCII 33:65)  

## File

### File Location
The package of the java files is "src.passwordManager".
If you do not want to save the storage account files in "C:\passwordManager" you can change it at line 19 of the Main class. (and at line 69 of the Features class because you need to change the path for the README.txt file too, to prevent error).

### File Format
hash login user   
hash login password   

Site Url User Email EncryptedPassword

The accounts are sorted by the Site name.  
Any space present in 
* site name
* url
* user
* email  
will be replaced with ~ (online in the file, in the app it will shown correctly)

## Imported Libraries
* java.io.File;
* java.io.FileWriter;
* java.io.IOException;
* java.time.LocalDateTime;
* java.util.ArrayList;
* java.util.Collections;
* java.util.InputMismatchException;
* java.util.Scanner;
* java.math.BigInteger;
* java.nio.charset.StandardCharsets;
* java.security.MessageDigest;
* java.security.NoSuchAlgorithmException;
* java.io.UnsupportedEncodingException;
* java.security.NoSuchAlgorithmException;
* java.util.Arrays;
* java.util.Base64;
* javax.crypto.Cipher;
* javax.crypto.spec.SecretKeySpec;
* java.awt.datatransfer.StringSelection;
* java.util.Random;
* java.awt.Toolkit;
* java.awt.datatransfer.Clipboard;
