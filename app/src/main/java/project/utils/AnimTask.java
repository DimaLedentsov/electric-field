package project.utils;

import javafx.application.Platform;

public class AnimTask extends Thread{
    volatile boolean running;
    final double step = 0.1;
    final int TIME_K = 1000;
    final Runnable task;
    public AnimTask(Runnable t){
        task = t;
        this.running = true;
    }
    public void run(){
        while (isRunning()) { 
            try {
                Platform.runLater(task);
                Thread.sleep((int) (step * TIME_K));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void kill(){
        running = false;
    }

    public void proceed(){
        running = true;
    }

    public boolean isRunning(){
        return running;
    }
}
