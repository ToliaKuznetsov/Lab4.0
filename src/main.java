import java.io.*;

public class main {
    public static void main(String[] args) throws IOException, AccountAlreadyExistsException, AccountBlockedException, WrongCredentialsException {
        FileAccountManager manage_Acc = new FileAccountManager();

        Account userRoman = new Account("Кузнецов", "Анатолий", "Дмитриевич", "22.05.2002", "a.kuznetsov14@g.nsu.ru", "tolia");
        Account userMark = new Account("Зубарева", "Татьяна", "Ивановна", "4.12.2001", "Zubareva@gmail.com", "Zubar");

        try {
            manage_Acc.register(userRoman);
            manage_Acc.register(userMark);
            manage_Acc.register(userRoman);

        } catch (AccountAlreadyExistsException e) {
            System.out.println(e.getMessage());

        }

        try {
            try {

                manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolik");

            } catch (WrongCredentialsException e) {
                System.out.println(e.getMessage());
            }
            try {

                manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolik");

            } catch (WrongCredentialsException e) {
                System.out.println(e.getMessage());
            }
            try {

                manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolik");

            } catch (WrongCredentialsException e) {
                System.out.println(e.getMessage());
            }
            try {

                manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolik");

            } catch (WrongCredentialsException e) {
                System.out.println(e.getMessage());
            }
            try {

                manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolia");

            } catch (WrongCredentialsException e) {
                System.out.println(e.getMessage());
            }
        } catch (AccountBlockedException e) {
            System.out.println(e.getMessage());
        }
        manage_Acc.login("a.kuznetsov14@g.nsu.ru", "tolia");

        try {
            manage_Acc.login("Zubareva@gmail.com", "0000");

        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }

        manage_Acc.login("Zubareva@gmail.com", "Zubar");

        try {
            manage_Acc.login("Zubareva@gmail.com", "548953");

        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }

        try {
            manage_Acc.login("Zubareva@gmail.com", "548953");

        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }

        try {
            manage_Acc.login("Zubareva@gmail.com", "548953");

        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }

        manage_Acc.login("Zubareva@gmail.com", "Zubar");

        manage_Acc.removeAccount("a.kuznetsov14@g.nsu.ru", "tolia");
        try {
            manage_Acc.removeAccount("Zubareva@gmail.com", "548953");

        } catch (WrongCredentialsException e) {
            System.out.println(e.getMessage());
        }
        manage_Acc.removeAccount("Zubareva@gmail.com", "Zubar");


    }

}
