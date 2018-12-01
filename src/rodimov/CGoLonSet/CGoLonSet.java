package rodimov.CGoLonSet;


import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CGoLonSet {
    private int resw;
    private int resh;

    public CGoLonSet(int WIDTH, int HEIGTH){
        resh = HEIGTH;
        resw = WIDTH;
    }

    public void game(char q) throws IOException{
        Set<Cell> universe = new HashSet<>();
        SetStdDraw d = new SetStdDraw(resw, resh, q);


        Random rnd = new Random();
        double wind = rnd.nextDouble();

        SetNewGen n = new SetNewGen();
        n.wind = wind;
        n.Twind = rnd.nextDouble();
        if (wind < 0.25) {
            d.windDir = "↓";
        } else if ((wind >= 0.25) && (wind < 0.50)) {
            d.windDir = "↑";
        } else if ((wind >= 0.50) && (wind < 0.75)) {
            d.windDir = "→";
        } else {
            d.windDir = "←";
        }

        d.wind = (int)Math.floor(n.Twind * 100);

        SetFill f = new SetFill(universe);
        f.setSize(resh, resw);
        d.canvasStdDraw();
        universe = f.fillRandom();
        d.alive = universe.size();
        while (true) {
            long tStart  = System.currentTimeMillis();
            universe = n.calcNewGen(universe);
            d.dead += d.alive - universe.size();
            d.alive = universe.size();
            d.winStdDraw(universe);
            d.fpsStdDraw(tStart);
        }
    }
}
