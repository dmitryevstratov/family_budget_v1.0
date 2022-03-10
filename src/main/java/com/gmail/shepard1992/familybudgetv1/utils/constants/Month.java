package com.gmail.shepard1992.familybudgetv1.utils.constants;

public enum Month {

    JAR(1, "Янв"),
    FEB(2, "Фер"),
    MAR(3, "Мар"),
    APR(4, "Апр"),
    MAY(5, "Май"),
    JUN(6, "Июн"),
    JUL(7, "Июл"),
    AUG(8, "Авг"),
    SEP(9, "Сен"),
    OKT(10, "Окт"),
    NOV(11, "Ноя"),
    DEC(12, "Дек");

    private final Integer num;

    private final String name;

    Month(Integer num, String name) {
        this.num = num;
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

}
