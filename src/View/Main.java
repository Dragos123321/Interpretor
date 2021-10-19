package View;
import Repo.Repo;
import Controller.Controller;


public class Main {

    static Repo myRepository = new Repo();
    static Controller myController = new Controller(myRepository);
    static Console console = new Console(myController);

    public static void main(String[] args) {
        console.run();
    }
}
