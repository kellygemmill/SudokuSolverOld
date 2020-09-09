package model;

import java.util.ArrayList;
import java.util.List;

public class SudokuBoard extends Box {

    private final int subBoxSize;
    private List<Column> columns;
    private List<Box> boxes;

    public SudokuBoard(List<Square> squares) {
        super(squares);

        if (isNotSquare(this.squares.subList(0, this.size))) {
            throw new IllegalArgumentException("Sub-boxes must sum to total grid dimension.");
        }

        this.subBoxSize = (int) Math.round(Math.sqrt(this.size));

        this.setRowSquares();
        this.populateColumns();
        this.populateBoxes();
    }

    private void setRowSquares() {
        for (Row row : rows) {
            List<Square> thisRowSquares = row.getSquares();
            thisRowSquares
                    .stream()
                    .forEach(square -> square.setRow(row));
        }
    }

    private void populateColumns() {
        this.columns = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            List<Square> thisColumnSquares = new ArrayList<>();

            for (Row row : rows) {               // Loop over each row, get element i, and add to column
                thisColumnSquares.add(row.getSquares().get(i));
            }
            Column thisColumn = new Column(thisColumnSquares);
            this.columns.add(thisColumn);

            thisColumnSquares
                    .stream()
                    .forEach(square -> square.setColumn(thisColumn));
        }
    }

    private void populateBoxes() {
        this.boxes = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {       // Loop over all boxes (9 for standard sudoku)
            int boxRow = i / this.subBoxSize;       // Find out which row and column this box is (0-2 for standard sudoku)
            int boxCol = i % this.subBoxSize;

            int startingRow = this.startingIdx(boxRow);
            int startingCol = this.startingIdx(boxCol);

            List<Square> thisBoxSquares = new ArrayList<Square>();
            for (int j = 0; j < this.subBoxSize; j++) {     // Add each element of the sub-box to a list
                int row = startingRow + j;
                thisBoxSquares.addAll(this.rows
                                .get(row)
                                .getSquares()
                                .subList(startingCol,startingCol+this.subBoxSize));
            }

            Box thisBox = new Box(thisBoxSquares);
            this.boxes.add(thisBox);

            thisBoxSquares
                    .stream()
                    .forEach(square -> square.setBox(thisBox));

        }
    }

    private int startingIdx(int boxIdx) {
        return boxIdx * this.subBoxSize;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public List<Box> getBoxes() {
        return boxes;
    }

}
