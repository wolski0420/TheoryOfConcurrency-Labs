package Ex1;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class MandelBrot extends JFrame {

    public static int MAX_ITER;
    public final static double ZOOM = 150;
    private BufferedImage I;
    private double zx, zy, cX, cY, tmp;

    private final int threadsNumber;
    private final int tasksNumber;
    private final int pixelsNumber;

    public MandelBrot(int threadsNumber, int tasksNumber, int width, int height) {
        super("Mandelbrot Set");

        this.threadsNumber = threadsNumber;
        this.tasksNumber = tasksNumber;
        this.pixelsNumber = width * height;

        setBounds(100, 100, width, height);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        I = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        long start = System.nanoTime();

        ExecutorService executorService = Executors.newFixedThreadPool(threadsNumber);
        Set<Future<ArrayList<Pixel>>> futures = new HashSet<>();
        ArrayList<Pixel> pixels = new ArrayList<>();

        int pixelsPerTask = pixelsNumber/tasksNumber;
        int counter = 0;

        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                pixels.add(new Pixel(x, y, MAX_ITER));

                if(counter == pixelsPerTask-1){
                    futures.add(executorService.submit(new Task(pixels)));
                    pixels = new ArrayList<>();
                }

                counter = (counter + 1) % pixelsPerTask;
            }
        }

        for(Future<ArrayList<Pixel>> future : futures){
            try{
                ArrayList<Pixel> processedPixels = future.get();
                for(Pixel pixel : processedPixels)
                    I.setRGB(pixel.x, pixel.y, pixel.iter | (pixel.iter << 8));
            } catch(ExecutionException | InterruptedException e){
                e.printStackTrace();
            }
        }

        long finish = System.nanoTime();

        try {
            File file = new File("results.txt");
            file.createNewFile();
            Files.write(file.toPath(), String.format("%d %d %d %f\n", threadsNumber, tasksNumber, MAX_ITER, ((finish-start)/10e6))
                    .getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(I, 0, 0, this);
    }

    public static void main(String[] args) throws Exception {
        if(args.length < 5) throw new Exception("Wrong number of args!");
        System.out.println(Arrays.toString(args));
        MAX_ITER = Integer.parseInt(args[2]);
        MandelBrot mandelBrot = new MandelBrot(Integer.parseInt(args[0]),Integer.parseInt(args[1]),Integer.parseInt(args[3]),Integer.parseInt(args[4]));
        mandelBrot.setVisible(true);
        mandelBrot.dispose();
        System.exit(0);
    }
}
