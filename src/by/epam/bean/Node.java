package by.epam.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Node implements Serializable {

    private String name;
    private List<Attribute> attr;

    private List<Node> children;
    private String content;

    public Node () {
        name = "";
        content = "";
        attr = null;
        children = null;
    }

    public Node (String name, String content, List<Attribute> attr, List<Node> children) {
        this.name = name;
        this.content = content;
        this.attr = attr;
        this.children = children;
    }

    public String getName () { return name; }
    public List<Attribute> getListAttr () { return attr; }
    public List<Node> getListChildren () { return children; }
    public String getContent () { return content; }
    public Node getChild (int number) { return this.children.get(number); }
    public int getChildrenQuantity () { return this.children.size(); }

    public void setName (String name) { this.name = name; }
    public void setListAttr (List<Attribute> list) { this.attr = list; }
    public void setListChildren (List<Node> children) { this.children = children; }
    public void setContent (String content) { this.content = content; }

    public void addAttribute (Attribute attr) { this.attr.add(attr); }
    public void addChild (Node node) { children.add(node); }

    public boolean hasChildren () {
        return !children.isEmpty();
    }
    public boolean hasContent () {
        return !content.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(name, node.name) && Objects.equals(attr, node.attr) 
                && Objects.equals(children, node.children) && Objects.equals(content, node.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attr, children, content);
    }

    @Override
    public String toString() {

        String attrStr = "";
        for (Attribute attribute: attr) {
            attrStr.concat(attribute.toString());
        }

        return "Node{" +
                "name='" + name + '\'' +
                ", attributes="  + attrStr +
                    ", content=" + content + '\'' +
                '}';
    }
}
