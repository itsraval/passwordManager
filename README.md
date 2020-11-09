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
* Show Accounts List with Credential
* Add Account
* Modify account
* Delete Account
* Change Login Username
* Change Login Password
* Infos

## Other Featueres
If you need to delete an account or change login credential the program will ask you to type the password to make sure it is you.
if the program has been open for more than 5 minutes and you want to select something from the menu, before you can access that area, it will ask you to type the password to make sure it is you.  

When you are adding or modifing an account you can choose to generate a strong password with the integrated "Password Generator".  
When you are adding or modifing an account, the new password will be copied into your clipboard.

## Password Generator
This will generate a random strong password made by 16 characters.   
* 8 of those will be alphabetic / symbol (ASCII 65:126)
* 8 of those will be numeric / symbol (ASCII 33:65)  

## File Location
The package of the java files is "src.passwordManager".
If you do not want to save the storage account files in "C:\passwordManager" you can change it at line 19 of the Main class. (and at line 69 of the Features class because you need to change the path for the README.txt file too, to prevent error).

## Imported Libraries
* java.io.File;
* java.io.FileWriter;
* java.io.IOException;
* java.time.LocalDateTime;
* java.util.ArrayList;
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
