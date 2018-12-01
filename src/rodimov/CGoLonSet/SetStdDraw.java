package rodimov.CGoLonSet;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.util.Set;

public class SetStdDraw {
    private double zoom = 1;
    private int resw;
    private int resh;
    private double x;
    private double y;
    private double xc;
    private double yc;
    int alive = 0;
    int dead = 0;
    String windDir = "";
    int wind = 0;

    SetStdDraw(int WIDTH, int HEIGTH, char q){
        resw = WIDTH;
        resh = HEIGTH;
        xc = (resw - 1) / 2.;
        yc = (resh - 1) / 2.;
        if (q == 'c') {
            zoom = 0.01;
            yc -= 250 / zoom;
        }
    }

    public void canvasStdDraw(){
        StdDraw.setCanvasSize(resw, resh);
        StdDraw.setXscale(0, (resw - 1));
        StdDraw.setYscale(0, (resh - 1));
        StdDraw.enableDoubleBuffering();
    }

    public void winStdDraw(Set<Cell> universe){
        StdDraw.clear(Color.WHITE);
        if(StdDraw.hasNextKeyTyped()) {
            char key = StdDraw.nextKeyTyped();
            if (key == '+') {
                zoom *= 1.3;
            }
            if (key == '-') {
                zoom /= 1.3;
            }
            if (key == 'w') {
                yc += 20 / zoom;
            }
            if (key == 's') {
                yc -= 20 / zoom;
            }
            if (key == 'd') {
                xc += 20 / zoom;
            }
            if (key == 'a') {
                xc -= 20 / zoom;
            }
        }
        for (Cell cell : universe){
            x = ((resw - 1) / 2) + (cell.x - xc) * zoom;
            y = ((resh - 1) / 2) + (((resh - 1) - cell.y) - yc) * zoom;
            if ((Math.abs(x) <= resw) || (Math.abs(y) <= resh)) {
                if (cell.health >= 75) {
                    StdDraw.setPenColor(Color.GREEN);
                } else if (cell.health >= 50) {
                    StdDraw.setPenColor(Color.YELLOW);
                } else if (cell.health >= 35) {
                    StdDraw.setPenColor(Color.ORANGE);
                } else {
                    StdDraw.setPenColor(Color.RED);
                }
                StdDraw.filledCircle(x, y, 0.5 * zoom);
            }
            if (cell.onFire) {
                cell.health -= 0.25;
                cell.fireTime++;
                if (cell.fireTime > 50){
                    cell.onFire = false;
                    cell.fireTime = 0;
                }
            }
        }
    }

    public void fpsStdDraw(long tStart){
        StdDraw.setPenColor(Color.BLACK);
        long tFrame = System.currentTimeMillis() - tStart;
        String time = "frame: " + tFrame + "ms";
        String fps = "fps: " + Math.round(1000.0 / tFrame);
        String aliveCells = "Alive trees: " + alive;
        String deadCells = "Dead trees: " + dead;
        String windSpeed = "Wind speed: " + wind;
        StdDraw.textLeft(20, 20, time);
        StdDraw.textLeft(20, 40, fps);
        StdDraw.textLeft(20, 60, aliveCells);
        StdDraw.textLeft(20, 80, deadCells);
        StdDraw.textLeft(20, 100, "Wind direction: " + windDir);
        StdDraw.textLeft(20, 120, windSpeed);
        StdDraw.show();
    }

}
