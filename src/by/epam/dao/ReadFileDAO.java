package by.epam.dao;

import java.util.ArrayList;

public interface ReadFileDAO {
    ArrayList<String> readFile() throws DAOException;
}