package model;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class SquareGroup {

    protected List<Square> squares;

    public SquareGroup(List<Square> squares) {
        this.squares = squares;
    }

    public List<Square> getSquares() {
        return this.squares;
    }

    public Set<Integer> getValues() {
        return this.squares
                .stream()
                .map(Square::getValue)
                .filter(value -> value != 0)
                .collect(Collectors.toSet());
    }


}
