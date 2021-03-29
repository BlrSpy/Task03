package by.epam.service.impl;

import by.epam.bean.Attribute;
import by.epam.bean.Node;
import by.epam.dao.DAOException;
import by.epam.dao.DAOProvider;
import by.epam.dao.ReadFileDAO;
import by.epam.service.Parser;
import by.epam.service.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class ParserImpl implements Parser {

    @Override
    public Node getParseXML() throws ServiceException {

        DAOProvider provider = DAOProvider.getInstance();
        ReadFileDAO readFileDAO = provider.getReadFileDAO();

        try {
            ArrayList<String> list;
            list = readFileDAO.readFile();


            ArrayList<Node> listChildren = new ArrayList<>();
            ArrayList<Attribute> listAttr = new ArrayList<>();


            Stack<Node> stack = new Stack<>();
            String element = list.iterator().next();

            Iterator<String> iter = list.iterator();
            Node node = new Node();

            if (element.contains("<") && !element.contains("</")) {

                node.setListChildren(listChildren);
                node.setListAttr(listAttr);
                node.setName(element.replaceAll("<", ""));

                for (int i = 0; i < 2; i++) {
                    element = iter.next();
                }

                while (!element.contains(">")) {

                    Attribute attribute = new Attribute();
                    int attr = Integer.parseInt(element.split("=")[1].replaceAll("\"", ""));
                    attribute.setName(element.split("=",2)[0]);
                    attribute.setAttr(attr);
                    node.addAttribute(attribute);
                    element = iter.next();
                }

                String content = "";

                if (!element.equals(">")) {
                    while (!element.contains("<")) {

                        node.setContent(content.concat(element.replaceAll(">", "") + " "));
                        element = iter.next();
                        content = node.getContent();

                    }
                }

                stack.push(node);
            }

            while (iter.hasNext()) {

                node = new Node();
                node.setListChildren(listChildren);
                node.setListAttr(listAttr);

                if (element.contains("<") && !element.contains("</")) {

                    node.setName(element.replaceAll("<", ""));

                    element = iter.next();


                    while (!element.contains(">")) {

                        Attribute attribute = new Attribute();
                        int attr = Integer.parseInt(element.split("=")[1].replaceAll("\"", ""));
                        attribute.setName(element.split("=",2)[0]);
                        attribute.setAttr(attr);

                        node.addAttribute(attribute);
                        element = iter.next();

                    }

                    if (element.equals(">")) {
                        element = iter.next();
                    }

                    String content = "";

                    while (!element.contains("<")) {

                        node.setContent(content.concat(element.replaceAll(">", "") + " "));
                        element = iter.next();
                        content = node.getContent();

                    }

                    stack.firstElement().addChild(node);
                    stack.push(node);
                }
                else {
                    element = iter.next();
                }

                if (element.contains("</") && stack.size() > 1) {
                    stack.pop();
                    element = iter.next();
                }
            }

            return stack.firstElement();

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
