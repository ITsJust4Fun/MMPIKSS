package rodimov.CGoLonSet;

public class Cell {
    int x;
    int y;
    double health = 100;
    boolean onFire = false;
    int fireTime = 0;
    Cell(int x, int y){
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        if (y != cell.y) return false;
        return onFire == cell.onFire;
    }

    @Override
    public int hashCode() {
        return 31 * y + x;
    }
}
