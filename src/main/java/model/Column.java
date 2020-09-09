package model;

import java.util.List;
import java.util.stream.Collectors;

public class Column extends SquareGroup {
    public Column(List<Square> squares) {
        super(squares);
    }

    @Override
    public String toString() {
        return this.squares
                .stream()
                .map(Square::toString)
                .collect(Collectors.joining("\n"));
    }
}
