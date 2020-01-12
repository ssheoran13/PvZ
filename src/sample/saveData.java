package sample;

import java.io.Serializable;

public class saveData implements Serializable {
    private double layoutx;
    private double layouty;
    private String objectid;
    private double width;
    private double height;
    private boolean visible;
    private boolean disable;

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isDisable() {
        return disable;
    }

    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    public double getLayoutx() {
        return layoutx;
    }

    public void setLayoutx(double layoutx) {
        this.layoutx = layoutx;
    }

    public double getLayouty() {
        return layouty;
    }

    public void setLayouty(double layouty) {
        this.layouty = layouty;
    }

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
