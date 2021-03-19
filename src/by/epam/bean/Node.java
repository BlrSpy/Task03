package by.epam.bean;

import java.util.List;

public class Node {

    private String name;
    private List<Attribute> attr;

    private List<Node> children;
    private String content;

    public String getName () { return name; }
    public List<Attribute> getListAttr () { return attr; }
    public List<Node> getListChildren () { return children; }
    public String getContent () { return content; }

    public void setName (String name) { this.name = name; }
    public void setListAttr (List<Attribute> list) { this.attr = list; }
    public void setListChildren (List children) { this.children = children; }
    public void setContent (String content) { this.content = content; }

    public void addAttribute (Attribute attr) { this.attr.add(attr); }
    public void addChild (Node node) { children.add(node); }
}
