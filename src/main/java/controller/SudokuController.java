package controller;

import model.SudokuBoard;
import model.Square;
import solver.SudokuSolver;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SudokuController {

    private SudokuBoard sudokuBoard;
    private SudokuSolver sudokuSolver;
    private String inputFile;
    private String outputFile;

    public SudokuController(List<Integer> boardInput) {
        List<Square> squares = createSquares(boardInput);
        this.initializeController(squares);
    }

    public SudokuController(String inputFile,String outputFile) throws IOException {
        this.inputFile = inputFile;
        this.outputFile = outputFile;

        String boardInput = this.loadSudokuBoard();
        List<Square> squares = createSquares(boardInput.toString());
        initializeController(squares);
    }

    public SudokuBoard getSudokuBoard() {
        return sudokuBoard;
    }

    private String loadSudokuBoard() throws IOException {
        Scanner boardInputFile = new Scanner(Paths.get(inputFile));
        StringBuilder boardInput = new StringBuilder();
        while (boardInputFile.hasNextLine()) {
            boardInput.append(boardInputFile.nextLine()).append(" ");
        }
        return boardInput.toString();
    }

    private void initializeController(List<Square> squares) {
        this.sudokuBoard = new SudokuBoard(squares);
        this.sudokuSolver = new SudokuSolver(sudokuBoard);
    }

    public String solve() throws FileNotFoundException {
        PrintWriter sudokuSolution = new PrintWriter(outputFile);
        String result;

        if (sudokuSolver.solve()) {                                     // this line runs the solver in sudokuSolver
            result = "Board solved.  Result written to " + outputFile + ".";
            sudokuSolution.println("Solution found:\n");
        } else {
            result = "No solution found.";
            sudokuSolution.println("No solution found.  Board input was:\n");
        }

        sudokuSolution.print(sudokuBoard.toString());
        sudokuSolution.close();
        return result;
    }

    private List<Square> createSquares(String boardInput) {
        List<Integer> boardAsInt = Arrays
                .stream(boardInput.split("\\s++"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return this.createSquares(boardAsInt);
    }

    private List<Square> createSquares(List<Integer> boardInput) {
        return boardInput
                .stream()
                .map(value -> new Square(value, value != 0))    // If value is not zero, it's a pre-provided value
                .collect(Collectors.toList());
    }

}
