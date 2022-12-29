import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import static java.lang.Integer.valueOf;

public class FileService {
    private static FileService instance;

    private FileService() {}

    public static FileService getInstance() {
        if (instance == null) {
            instance = new FileService();
        }
        return instance;
    }

    public List<Account> readerCSV() throws IOException {
        List<Account> users = new ArrayList<>();
        try (BufferedReader inpt = new BufferedReader(new FileReader("info.csv"))) {
            int num = 0;
            String line = null;
            Scanner input = null;

            while ((line = inpt.readLine()) != null) {
                Account user = new Account();
                input = new Scanner(line);
                input.useDelimiter(",");

                while (input.hasNext()) {
                    String info = input.next();
                    if (num == 0) { user.setLastName(info); }
                    else if (num == 1) { user.setFirstName(info); }
                    else if (num == 2) { user.setMiddleName(info); }
                    else if (num == 3) { user.setBirthday(info); }
                    else if (num == 4) { user.setMail(info); }
                    else if (num == 5) { user.setPassword(info); }
                    else if (num == 6) { user.setBlocked(); }
                    else if (num == 7) { user.setCount(valueOf(info)); }
                    else { System.out.println("Некорректные данные"); }
                    num++;
                }
                users.add(user);
                num = 0;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return users;
    }

    public void writeCSV(List<Account> accounts) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("info.csv"));

        for (Account account : accounts) {
            writer.write(String.valueOf(account));
        }
        writer.close();
    }


}