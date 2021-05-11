package maldonado.gregory.modeller.GUI.P3;
import peasy.PeasyCam;
import processing.core.*;

public class Environment extends PApplet {

    private PeasyCam cam;
    private float angle = 0;
    private boolean rotate = true;

    public void settings() { size(600, 600); }
    public void draw() {
        background(0);
        drawAxes();
    }

    public void drawAxes() {
        strokeWeight(5);

        stroke(0, 0, 255);
        line(-500, 0, 500, 0); // Line 1
        stroke(0, 255, 0);
        line(0, -500, 0, 500); // Line 2
        stroke(255);
        line(0, 0, 0, 0); // Line 3

    }

}
