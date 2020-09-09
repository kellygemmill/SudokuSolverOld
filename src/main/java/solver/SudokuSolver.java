package solver;

import model.*;

import java.util.List;

public class SudokuSolver {

    private final SudokuBoard sudokuBoard;
    private final int boardSize;
    private final int boardLength;

    public SudokuSolver(SudokuBoard sudokuBoard) {
        this.sudokuBoard = sudokuBoard;
        this.boardSize = sudokuBoard.getSize();
        this.boardLength = boardSize*boardSize;
    }

    public boolean solve() {
        return solve(0);
    }

    private boolean solve(int startIdx) {
        if (startIdx >= boardLength) {
            return true;
        }

        Square square = sudokuBoard.getSquares().get(startIdx);
        if (square.isProvidedValue()) {
            return solve(startIdx+1);
        }

        for (int number = 1; number <= boardSize; number++) {
            if (okToAddNumber(square,number)) {
                square.setValue(number);
                if (solve(startIdx+1)) {
                    return true;          // don't try any more if this number solves it
                }
                clearValues(startIdx);
            }
        }
        return false;
    }

    private void clearValues(int startIdx) {
        List<Square> squares = sudokuBoard.getSquares().subList(startIdx,boardLength);
        squares
                .stream()
                .forEach(square -> square.setValue(0));
    }

    private boolean okToAddNumber(Square square, int number) {
        return okToAddNumber(square.getRow(),number)
                && okToAddNumber(square.getColumn(), number)
                && okToAddNumber(square.getBox(), number);

    }

    private boolean okToAddNumber(SquareGroup group, int number) {
        return !group.getValues().contains(number);
    }
}
