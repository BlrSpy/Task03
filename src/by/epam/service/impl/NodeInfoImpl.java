package by.epam.service.impl;

import by.epam.bean.Node;
import by.epam.service.NodeInfo;

import java.util.ArrayList;


public class NodeInfoImpl implements NodeInfo {
    @Override
    public ArrayList<String> getNodeInfo(Node node){

        ArrayList<String> list = new ArrayList<>();

        if (node.hasContent()) {
            list.add(node.getContent());
        }

        for (Node n: node.getListChildren()) {
            if (n.hasContent()) {
                list.add(n.getContent());
            }
        }
    return list;
    }
}

