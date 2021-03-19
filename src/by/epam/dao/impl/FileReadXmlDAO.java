package by.epam.dao.impl;

import by.epam.dao.DAOException;
import by.epam.dao.ReadFileDAO;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FileReadXmlDAO implements ReadFileDAO {
    @Override
    public ArrayList<String> readFile() throws DAOException {

        try {

            File file = new File("food.xml");
            ArrayList<String> list = new ArrayList<>();
            Scanner sc = new Scanner(file);
            String info = "";

            if (sc.hasNext()) {
                info = sc.next();
            }

            if (info.contains("<?")) {
                while (sc.hasNext() && !info.contains("?>")) {
                    info = sc.next();
                }
            }

            while (sc.hasNext()) {
                info = sc.next();
                String[] subStr;
                subStr = info.split("(?=[<>])");

                list.addAll(Arrays.asList(subStr));
            }

            sc.close();

            return list;

        } catch (IOException e) {
            throw new DAOException(e);
        }
    }
}
