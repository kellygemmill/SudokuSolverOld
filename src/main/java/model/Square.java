package model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Square {

    private Integer value;
    private boolean providedValue;
    private Row row;
    private Column column;
    private Box box;

    public Square(Integer value, boolean providedValue) {
        this.value = value;
        this.providedValue = providedValue;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        if (!this.providedValue) {      // protect against resetting original values
            this.value = value;
        }
    }

    public boolean isProvidedValue() {
        return this.providedValue;
    }

    public Row getRow() {
        return row;
    }

    protected void setRow(Row row) {
        this.row = row;
    }

    public Column getColumn() {
        return column;
    }

    protected void setColumn(Column column) {
        this.column = column;
    }

    public Box getBox() {
        return box;
    }

    protected void setBox(Box box) {
        this.box = box;
    }

    @Override
    public String toString() {
        return this.value != 0 ? String.valueOf(this.value) : "X";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Square square = (Square) o;
        return Objects.equals(value, square.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
