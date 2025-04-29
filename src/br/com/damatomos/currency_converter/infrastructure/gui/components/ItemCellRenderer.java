package br.com.damatomos.currency_converter.infrastructure.gui.components;

import javax.swing.*;
import java.awt.*;

public class ItemCellRenderer<T> extends JLabel implements ListCellRenderer<Item<T>> {

    public ItemCellRenderer()
    {
        setOpaque(true);
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends Item<T>> list, Item<T> value, int index, boolean isSelected, boolean cellHasFocus) {

        setText(value.getLabel());

        Color background;
        Color foreground;

        JList.DropLocation dropLocation = list.getDropLocation();

        if (
                dropLocation != null &&
                        !dropLocation.isInsert() &&
                        dropLocation.getIndex() == index
        )
        {
            background = Color.BLUE;
            foreground = Color.WHITE;
        } else if (isSelected) {
            background = Color.GRAY;
            foreground = Color.WHITE;
        } else {
            background = Color.WHITE;
            foreground = Color.BLACK;
        }

        setBackground(background);
        setForeground(foreground);

        return this;
    }
}
