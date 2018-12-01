package rodimov.CGoLonSet;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class SetNewGen {
    Set<Cell> universe2;
    double wind;
    double Twind = 0.5;
    double Fwind = 0.1;

    private double calcVer(Set<Cell> universe, Cell cell){
        double ver = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((i != 0) || (j != 0)) {
                    int cellnx = cell.x + j;
                    int cellny = cell.y + i;
                    Cell a = new Cell(cellnx, cellny);
                    a.onFire = true;
                    if (universe.contains(a)) {
                        if (i == -1 && (wind < 0.25)) {
                            ver += Fwind + Twind;
                        } else if (i == 1 && ( (wind >= 0.25) && (wind < 0.50))) {
                            ver += Fwind + Twind;
                        } else if (j == -1 && ( (wind >= 0.50) && (wind < 0.75))) {
                            ver += Fwind + Twind;
                        } else if (j == 1 && ( (wind >= 0.75) && (wind < 1.00))) {
                            ver += Fwind + Twind;
                        } else {
                            ver += Fwind;
                        }
                    }
                }
            }
        }
        return ver;
    }

    public Set<Cell> calcNewGen(Set<Cell> universe) {
        Random rnd = new Random();
        universe2 = new HashSet<>();
        double ver;
        for (Cell cell : universe) {
            ver = calcVer(universe, cell);
            if (rnd.nextDouble() < ver) {
                cell.onFire = true;
                cell.fireTime = 0;
            }
            if (cell.health > 0) {
                universe2.add(cell);
            }
        }
        return universe2;
    }
}