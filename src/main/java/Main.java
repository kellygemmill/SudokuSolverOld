import controller.SudokuController;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        SudokuController controller1 = new SudokuController(
                "inputSudokuBoard1.txt",
                "outputSudokuBoard1.txt");

        SudokuController controller2 = new SudokuController(
                "inputSudokuBoard2.txt",
                "outputSudokuBoard2.txt");

        System.out.println(controller1.solve());
        System.out.println(controller2.solve());

    }

}
