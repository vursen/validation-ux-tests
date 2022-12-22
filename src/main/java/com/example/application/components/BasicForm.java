package com.example.application.components;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

import com.vaadin.flow.component.HasValidation;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.formlayout.FormLayout;

public class BasicForm extends FormLayout {
    protected ArrayList<HasValueAndElement<?, ?>> fields = new ArrayList<>();

    public Stream<HasValueAndElement<?, ?>> getFields() {
        return fields.stream();
    }

    public Stream<HasValueAndElement<?, ?>> getInvalidFields() {
        return getFields().filter((field) -> ((HasValidation) field).isInvalid());
    }

    public Stream<HasValueAndElement<?, ?>> getEmptyRequiredFields() {
        return getFields()
                .filter((field) -> field.isRequiredIndicatorVisible())
                .filter((field) -> Objects.equals(field.getValue(), field.getEmptyValue()));
    }
}
