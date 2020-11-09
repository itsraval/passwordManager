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
All data will only be saved on local store in "C:\passwordManager"

# Cryptography
This program uses SHA256 to store user and password to login in the app.
This program uses AES to encrypt every stored password.
* SHA256
* AES

# Menu
* Show Accounts List
* Show Accounts List with Credential
* Add Account
* Modify account
* Delete Account
* Change Login Username
* Change Login Password
* Infos

# Other Featueres
Pasgen
copy to clipboard

# Imported Libraries
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
