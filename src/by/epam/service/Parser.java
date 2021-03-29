package by.epam.service;

import by.epam.bean.Node;


public interface Parser {
    Node getParseXML() throws ServiceException;
}
