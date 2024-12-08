package Lights;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrafficLights {
    public enum State {
        RED, YELLOW, GREEN
    }

    final Circle redLight;
    final Circle yellowLight;
    final Circle greenLight;

    public TrafficLights(Circle redLight, Circle yellowLight, Circle greenLight) {
        this.redLight = redLight;
        this.yellowLight = yellowLight;
        this.greenLight = greenLight;
        setState(State.RED);
    }

    public void setState(State state) {
        switch (state) {
            case RED -> {
                redLight.setFill(Color.RED);
                yellowLight.setFill(Color.GRAY);
                greenLight.setFill(Color.GRAY);
            }
            case YELLOW -> {
                redLight.setFill(Color.GRAY);
                yellowLight.setFill(Color.YELLOW);
                greenLight.setFill(Color.GRAY);
            }
            case GREEN -> {
                redLight.setFill(Color.GRAY);
                yellowLight.setFill(Color.GRAY);
                greenLight.setFill(Color.GREEN);
            }
        }
    }
}
