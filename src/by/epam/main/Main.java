package by.epam.main;

import by.epam.controller.ParserController;
import by.epam.controller.impl.ParserControllerImpl;

public class Main {
    public static void main(String[] args) {
        ParserController controller = new ParserControllerImpl();

        String response = controller.execute();

        System.out.println(response);
    }
}
