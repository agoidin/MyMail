package Java.Connections;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Account {
    private final String FILE_ADDRESS = "src/Resources/Account/UserDetails.txt";
    
    public String USERNAME;
    public String PASSWORD;
    public String EMAIL_ADDRESS;

    public Account() {
        String accountInfo = getFileLine();

        USERNAME = accountInfo.split("/")[0];
        PASSWORD = accountInfo.split("/")[1];
        EMAIL_ADDRESS = USERNAME + "@localhost";
    }

    public String getFileLine() {
        try {
            File file = new File(FILE_ADDRESS);
            Scanner scanner = new Scanner(file);
            
            String accInfo = scanner.nextLine();
            
            scanner.close();
            return accInfo;

        } catch (FileNotFoundException e) {
            System.out.println("NO FILE FOUND");
        }
        return null;
    }
}
