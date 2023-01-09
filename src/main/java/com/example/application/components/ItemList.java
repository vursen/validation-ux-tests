package com.example.application.components;

import com.example.application.data.Order;
import com.example.application.data.OrderItem;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Hr;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.theme.lumo.LumoUtility;

public class ItemList extends VerticalLayout {
    public ItemList() {
        // for (OrderItem item : binder.getBean().getItems()) {
        //     addItem(item);
        //     add(new Hr());
        // }

        // setAlignItems(Alignment.STRETCH);
        // setPadding(false);
    }

    private void addItem(OrderItem item) {
        HorizontalLayout layout = new HorizontalLayout();

        Image image = new Image(item.getImage(), item.getTitle());
        image.setMaxWidth("80px");
        image.setMaxHeight("auto");
        layout.add(image);
        layout.setFlexGrow(0, image);

        Div title = new Div(new Text(item.getTitle()));
        title.setWidth("50%");
        layout.add(title);
        layout.setFlexGrow(1, title);

        NumberField price = new NumberField("Price");
        price.setValue(item.getPrice());
        price.setMin(1);
        price.setPrefixComponent(new Div(new Text("€")));
        price.addClassNames(LumoUtility.Padding.Top.NONE);
        layout.add(price);

        IntegerField quantity = new IntegerField("Quantity");
        quantity.setValue(item.getQuantity());
        quantity.setStepButtonsVisible(true);
        quantity.setMin(1);
        quantity.setWidth("112px");
        quantity.addClassNames(LumoUtility.Padding.Top.NONE);
        layout.add(quantity);

        add(layout);
    }
}
