package model;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Board {
    private final List<List<Space>> spaces;

    public Board(final List<List<Space>> spaces) {
        this.spaces = spaces;
    }

    public List<List<Space>> getSpaces() {
        return spaces;
    }

    public GamesStatusEnum getGameStatus() {
        if (spaces.stream().flatMap(Collection::stream).noneMatch(s -> !s.isFixed() && Objects.nonNull(s.getActual()))) {
            return GamesStatusEnum.NON_STARTED;
        }

        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> Objects.isNull(s.getActual())) ? GamesStatusEnum.INCOMPLETE : GamesStatusEnum.COMPLETE;
    }

    public boolean hasError() {
        if(getGameStatus() == GamesStatusEnum.NON_STARTED) {
            return false;
        }
        return spaces.stream().flatMap(Collection::stream).anyMatch(s -> Objects.nonNull(s.getActual()) && !s.getActual().equals(s.getExpected()));
    }

    public boolean changeValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.setActual(null);
        return true;
    }

    public boolean clearValue(final int col, final int row, final int value) {
        var space = spaces.get(col).get(row);
        if (space.isFixed()) {
            return false;
        }

        space.clearSpace();
        return true;
    }

    public void reset() {
        spaces.forEach(c -> c.forEach(Space::clearSpace));
    }

    public boolean gameIsFinished() {
        return !hasError() && getGameStatus().equals(GamesStatusEnum.COMPLETE);
    }
}
