public class FailedLoginCounter {

    private static FailedLoginCounter instance;
    private FailedLoginCounter() {
    }

    public static FailedLoginCounter getInstance() {
        if (instance == null) {
            instance = new FailedLoginCounter();
        }
        return instance;
    }

    public void good(Account account) { account.logTrue(0); }

    public void bad(Account account) { account.logCount(); }

}