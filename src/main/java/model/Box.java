package model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Box extends SquareGroup {

    protected final int size;
    protected List<Row> rows;

    public Box(List<Square> squares) {
        super(squares);
        this.size = (int) Math.round(Math.sqrt(this.squares.size()));

        if (isNotSquare(this.squares)) {
            throw new IllegalArgumentException("Input values must define a perfect square.");
        }

        this.populateRows();
    }

    private void populateRows() {
        this.rows = new ArrayList<Row>();

        for (int i = 0; i < this.size; i++) {
            int startIdx = i*this.size;

            List<Square> thisRowSquares = this.squares.subList(startIdx,startIdx+this.size);
            Row thisRow = new Row(thisRowSquares);
            this.rows.add(thisRow);
        }
    }

    protected boolean isNotSquare(List<Square> squares) {
        double lengthSqrt = Math.sqrt(squares.size());
        return lengthSqrt - Math.floor(lengthSqrt) != 0;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public List<Row> getRows() {
        return rows;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return this.rows.stream()
                .map(Row::toString)
                .collect(Collectors.joining("\n"));
    }
}
