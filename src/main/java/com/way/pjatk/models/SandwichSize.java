package com.way.pjatk.models;

public enum SandwichSize {
    SMALL, NORMAL, BIG;

    public SandwichSize next()
    {
        return values()[(this.ordinal()+1) % values().length];
    }

    public SandwichSize previous() {
        if(this.ordinal()-1 >= 0) {
            return values()[(this.ordinal() - 1)];
        }
        return SandwichSize.SMALL;
    }
}
