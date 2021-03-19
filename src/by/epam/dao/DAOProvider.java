package by.epam.dao;

import by.epam.dao.impl.FileReadXmlDAO;

public class DAOProvider {
    private static final DAOProvider instance = new DAOProvider();

    private final ReadFileDAO readFileDAO = new FileReadXmlDAO();

    private DAOProvider() {}

    public ReadFileDAO getReadFileDAO() {
        return readFileDAO;
    }


    public static DAOProvider getInstance() {
        return instance;
    }
}
