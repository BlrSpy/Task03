package by.epam.service;

import by.epam.bean.Node;

import java.io.File;

public interface Parser {
    Node getParseXML(File file) throws ServiceException;
}
