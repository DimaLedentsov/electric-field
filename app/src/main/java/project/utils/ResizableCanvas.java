package project.utils;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class ResizableCanvas extends Canvas {

    private double k;
    private double firstWidth, firstHeight;
    private final static double maxK=1.5;
    void init(){
        k=1;
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    public ResizableCanvas(double w, double h){
        super(w, h);
        init();
        firstHeight = h;
        firstWidth = w;
    }
    public ResizableCanvas() {
        // Redraw canvas when size changes.
        widthProperty().addListener(evt -> draw());
        heightProperty().addListener(evt -> draw());
    }

    private void draw() {
        double width = getWidth();
        double height = getHeight();
        k = Math.sqrt((width/firstWidth)*(height/firstHeight));
        k = Math.min(k, maxK);
        GraphicsContext gc = getGraphicsContext2D();
        gc.clearRect(0, 0, width, height);
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getHeight();
    }

    public double getResizeK(){
        return k;
    }
}