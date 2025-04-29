package br.com.damatomos.currency_converter.infrastructure.gui.components;

public class Item<T> {

    private T concrect;
    private Object id;
    private String label;

    public Item(T concrect, Object id, String label)
    {
        this.concrect = concrect;
        this.id = id;
        this.label = label;
    }

    public T getConcrect() {
        return concrect;
    }

    public void setConcrect(T concrect) {
        this.concrect = concrect;
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
