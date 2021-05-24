package org.scarc.data;

public class object {
    private String name;
    private String when;

    public object(String name, String when) {
        this.name = name;
        this.when = when;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWhen() {
        return when;
    }

    public void setWhen(String when) {
        this.when = when;
    }

    @Override
    public String toString() {
        return "object{" +
                "name='" + name + '\'' +
                ", when='" + when + '\'' +
                '}';
    }
}
