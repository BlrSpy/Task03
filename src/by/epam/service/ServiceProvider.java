package by.epam.service;

import by.epam.service.impl.NodeInfoImpl;
import by.epam.service.impl.ParserImpl;

public class ServiceProvider {
    private static final ServiceProvider instance = new ServiceProvider();

    private final Parser parser = new ParserImpl();

    private final NodeInfo nodeInfo = new NodeInfoImpl();

    private ServiceProvider() {}

    public static ServiceProvider getInstance() {
        return instance;
    }

    public Parser getParser() { return parser; }

    public NodeInfo getNodeInfo() { return nodeInfo; }
}
