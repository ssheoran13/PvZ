package sample;

public class ProgressBar {
    private static ProgressBar pb;
    private int duration;
    private  boolean active;

    private ProgressBar(int duration) {
        if (pb==null)
        {
            pb=new ProgressBar(duration);
        }
        this.duration = duration;
        this.active=false;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
