package Ex2;

public class Printer {
    private boolean isPrinting;

    public Printer() {
        isPrinting = false;
    }

    public void startPrinting(){
        isPrinting = true;
    }

    public void finishPrinting(){
        isPrinting = false;
    }

    public boolean isPrinting() {
        return isPrinting;
    }
}
