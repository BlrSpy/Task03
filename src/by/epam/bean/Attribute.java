package by.epam.bean;

import java.io.Serializable;
import java.util.Objects;

public class Attribute implements Serializable {
    private String name;
    private int attr;

    public Attribute() {
        name = "";
        attr = -1;
    }

    public Attribute(String name, int attr) {
        this.name = name;
        this.attr = attr;
    }

    public void setAttr (int attr) { this.attr = attr; }
    public int getAttr () { return attr; }

    public void setName (String name) { this.name = name; }
    public String getName () { return name; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return attr == attribute.attr && Objects.equals(name, attribute.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attr);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", attr=" + attr +
                '}';
    }
}
