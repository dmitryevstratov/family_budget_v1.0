package com.gmail.shepard1992.familybudgetv1.service.model.api;

public abstract class AbstractDto {

    private final String index;

    private final String category;

    private final String type;

    public AbstractDto(String index, String category, String type) {
        this.index = index;
        this.category = category;
        this.type = type;
    }

    public String getIndex() {
        return index;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }
}
