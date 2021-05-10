package by.epam.controller.impl;

import by.epam.bean.Node;
import by.epam.controller.ParserController;
import by.epam.service.NodeInfo;
import by.epam.service.Parser;
import by.epam.service.ServiceException;
import by.epam.service.ServiceProvider;


public class ParserControllerImpl implements ParserController {

    @Override
    public String execute() {
        ServiceProvider provider = ServiceProvider.getInstance();
        Parser parser = provider.getParser();
        NodeInfo nodeInfo = provider.getNodeInfo();

        String response;

        try {
            Node node = parser.getParseXML();
            nodeInfo.getNodeInfo(node);

            response = "Job is done!";
        } catch (ServiceException e) {
            response = "Error. Something went wrong...";
        }

        return response;
    }
}
