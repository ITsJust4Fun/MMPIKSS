package rodimov.CGoLonSet;

import java.util.Random;
import java.util.Set;

public class SetFill {
    private Set<Cell> universe;
    private int height = 400;
    private int width = 400;


    SetFill(Set<Cell> universe) {
        this.universe = universe;
    }

    void setSize(int height, int width) {
        this.height = height;
        this.width = width;
    }

    public Set<Cell> fillRandom() {
        Random rnd = new Random();
        int ni = rnd.nextInt(height - 6);
        int ny = rnd.nextInt(width - 6);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (rnd.nextDouble() < 0.45) {
                    Cell cell = new Cell(j, i);
                    if ( (i >= ni && i <= (ni + 5)) &&
                            (j >= ny && j <= (ny + 5)) ) {
                        cell.onFire = true;
                    }
                    universe.add(cell);
                }
            }
        }
        return universe;
    }
}
