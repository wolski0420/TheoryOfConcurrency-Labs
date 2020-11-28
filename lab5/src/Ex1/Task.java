package Ex1;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class Task implements Callable<ArrayList<Pixel>> {
    private final ArrayList<Pixel> pixels;

    public Task(ArrayList<Pixel> pixels) {
        this.pixels = pixels;
    }

    @Override
    public ArrayList<Pixel> call(){
        double zx, zy, cX, cY, tmp;
        for(Pixel pixel : pixels){
            zx = zy = 0;
            cX = (pixel.x - 400) / MandelBrot.ZOOM;
            cY = (pixel.y - 300) / MandelBrot.ZOOM;
            while (zx * zx + zy * zy < 4 && pixel.iter > 0) {
                tmp = zx * zx - zy * zy + cX;
                zy = 2.0 * zx * zy + cY;
                zx = tmp;
                pixel.iter--;
            }
        }

        return pixels;
    }
}
