import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.IOException;

public class FileAccountManager implements AccountManager {

    List<Account> users = new ArrayList<Account>();
    FileService csvFile = FileService.getInstance();
    FailedLoginCounter counter = FailedLoginCounter.getInstance();

    public void register(Account account) throws IOException, AccountAlreadyExistsException {
        /** Метод проверяет по полю email, что данный аккаунт в базе
         * отсутствует, и создает новую запись, в противном случае
         * выбрасывает ошибку AccountAlreadyExistsException*/

        users = csvFile.readerCSV();
        int to_Change = -1;

        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getMail(), account.getMail())) { // если найден аккаунт с таким же email
                to_Change = i;
            }
        }
        if (to_Change == -1) {
            users.add(account);
            csvFile.writeCSV(users);
            System.out.println(account.getMail() + " зарегистрирован");
        } else {
            throw new AccountAlreadyExistsException(account.getMail() + " уже зарегистрирован");
        }

    }

    public Account login(String email, String password) throws IOException, AccountBlockedException, WrongCredentialsException {
        /**Метод возвращает Account, если для email+пароль есть
         * подходящая запись в базе и аккаунт не заблокирован.
         * Если неверно введены email и/или пароль - выбрасывается
         * исключение WrongCredentialsException.
         * Если email и пароль верны, но аккаунт заблокирован -
         * выбрасывается исключение AccountBlockedException.
         * Если для конкретного пользователя больше 5 неудачных
         * попыток авторизоваться, то аккаунт блокируется.*/

        users = csvFile.readerCSV();
        int elem = -1;
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getMail(), email)) {
                elem = i;

            }
        }

        if (elem == -1) {
            throw new WrongCredentialsException("Неверно введены данные");
        } else {

            if (!Objects.equals(users.get(elem).getPassword(), password)) {
                counter.bad(users.get(elem));

                if (users.get(elem).getCount() > 4) {
                    users.get(elem).setBlocked();
                    csvFile.writeCSV(users);
                    throw new AccountBlockedException("Аккаунт " + users.get(elem).getMail() + " заблокирован");
                }else{
                    csvFile.writeCSV(users);
                    throw new WrongCredentialsException("Неверный пароль для " + users.get(elem).getMail());
                }

            }else if (Objects.equals(users.get(elem).getPassword(), password) && !users.get(elem).getBlocked() &&
                    users.get(elem).getCount() < 4) { // если верно введен пароль и данный аккаунт не заблокирован
                counter.good(users.get(elem));
                System.out.println("Вы вошли в аккаунт " + users.get(elem).getMail());
                csvFile.writeCSV(users);
                return users.get(elem);

            } else if (users.get(elem).getCount() >= 4 && Objects.equals(users.get(elem).getPassword(), password)) { // если верно введен пароль,но данный аккаунт не заблокирован
                users.get(elem).setBlocked();
                counter.good(users.get(elem));
                csvFile.writeCSV(users);
                System.out.println("Аккаунт " + users.get(elem).getMail() + " разблокирован");
                return users.get(elem);
            }
        }


        return users.get(elem);
    }

    public void removeAccount(String email, String password) throws IOException, WrongCredentialsException {
        /**Метод удаляет пользователя из базы, если логин и пароль
         * введены верно. В противном случае выбрасывает
         * ошибку WrongCredentialsException*/
        users = csvFile.readerCSV();
        int elem = -1;
        for (int i = 0; i < users.size(); i++) {
            if (Objects.equals(users.get(i).getMail(), email)) {
                elem = i;

            }
        }
        if (elem == -1) {
            throw new WrongCredentialsException("Неверные данные");

        } else {
            if (Objects.equals(users.get(elem).getPassword(), password)) {
                System.out.println("Аккаунт " + users.get(elem).getMail() + "  удален");
                users.remove(elem);
                csvFile.writeCSV(users);

            } else {
                throw new WrongCredentialsException("Неверные данные");
            }

        }
    }
}