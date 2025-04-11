package model;
import java.util.Timer;
import java.util.TimerTask;

public class RecipeTimer {
    private Timer timer = new Timer();

    // Schedules a task to run after a specified delay (in milliseconds)
    public void startTimer(Runnable callback, long delayMillis) {
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                callback.run();
            }
        };
        timer.schedule(task, delayMillis);
    }

    public void cancel() {
        timer.cancel();
    }
}
