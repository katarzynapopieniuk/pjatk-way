package com.way.pjatk.models;

public enum SandwichSize {
    SMALL, NORMAL, BIG;

    public SandwichSize next()
    {
        return values()[(this.ordinal()+1) % values().length];
    }

    public SandwichSize previous() {
        return values()[(this.ordinal()-1)];
    }
}
