package by.epam.service.impl.test;


import by.epam.bean.Attribute;
import by.epam.bean.Node;
import by.epam.dao.DAOException;
import by.epam.dao.DAOProvider;
import by.epam.dao.ReadFileDAO;
import by.epam.service.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

class ParserImplTest {

    @org.junit.jupiter.api.Test
    void getParseXML() throws ServiceException{

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

                System.out.println("1 - " + element);
                node.setListChildren(listChildren);
                node.setListAttr(listAttr);
                node.setName(element.replaceAll("<", ""));

                for (int i = 0; i < 2; i++) {
                    element = iter.next();
                }

                while (!element.contains(">")) {

                    System.out.println("2 - " + element);
                    Attribute attribute = new Attribute();
                    int attr = Integer.parseInt(element.split("=")[1].replaceAll("\"", ""));
                    attribute.setName(element.split("=",2)[0]);
                    attribute.setAttr(attr);
                    System.out.println("attr = " + attr);
                    System.out.println("attr name= " + attribute.getName());
                    node.addAttribute(attribute);
                    element = iter.next();
                }
                System.out.println(node.getListAttr().size());

                String content = "";

                if (!element.equals(">")) {
                    while (!element.contains("<")) {

                        System.out.println("3 - " + element);
                        node.setContent(content.concat(element.replaceAll(">", "")));
                        element = iter.next();
                        content = node.getContent();

                    }
                }

                stack.push(node);
            }

            while (iter.hasNext()) {

                System.out.println("element - " + element);
                node = new Node();
                node.setListChildren(listChildren);
                node.setListAttr(listAttr);

                if (element.contains("<") && !element.contains("</")) {
                    System.out.println("4 - " + element);
                    node.setName(element.replaceAll("<", ""));

                    element = iter.next();


                    while (!element.contains(">")) {

                        System.out.println("5 - " + element);
                        Attribute attribute = new Attribute();
                        int attr = Integer.parseInt(element.split("=")[1].replaceAll("\"", ""));
                        attribute.setName(element.split("=",2)[0]);
                        attribute.setAttr(attr);
                        System.out.println("attr = " + attr);
                        System.out.println("attr name= " + attribute.getName());

                        node.addAttribute(attribute);
                        element = iter.next();

                    }

                    System.out.println(element);
                    if (element.equals(">")) {
                        element = iter.next();
                    }
                    System.out.println("6 - " + element);
                    String content = "";

                    while (!element.contains("<")) {

                        System.out.println("7 - " + element);
                        node.setContent(content.concat(element.replaceAll(">", "")));
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
                    System.out.println("8 - " + element);
                    stack.pop();
                    element = iter.next();
                }
            }

            System.out.println(stack.firstElement().getName());
            System.out.println(stack.firstElement().getContent());
            System.out.println("size = " + stack.firstElement().getListAttr().size());
            System.out.println("size = " + stack.firstElement().getListChildren().size());
            for (int i = 0; i < stack.firstElement().getListAttr().size(); i++) {
                System.out.println(stack.firstElement().getListAttr().get(i).getAttr());
            }

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}