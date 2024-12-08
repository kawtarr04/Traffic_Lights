package Lights;

import javafx.application.Platform;


public class TrafficLightsController implements Runnable {
    private final TrafficLights northLight;
    private final TrafficLights southLight;
    private final TrafficLights eastLight;
    private final TrafficLights westLight;

    private final Object lock = new Object();

    public TrafficLightsController(TrafficLights northLight, TrafficLights southLight,
                                  TrafficLights eastLight, TrafficLights westLight) {
        this.northLight = northLight;
        this.southLight = southLight;
        this.eastLight = eastLight;
        this.westLight = westLight;
    }

    @Override
    public void run() {
        while (true) {
            try {
                synchronized (lock) {
                    updateLights(northLight, southLight, TrafficLights.State.GREEN, eastLight, westLight, TrafficLights.State.RED);
                    lock.wait(5000);

                    updateLights(northLight, southLight, TrafficLights.State.YELLOW, eastLight, westLight, TrafficLights.State.RED);
                    lock.wait(2000);

                    updateLights(northLight, southLight, TrafficLights.State.RED, eastLight, westLight, TrafficLights.State.GREEN);
                    lock.wait(5000);

                    updateLights(eastLight, westLight, TrafficLights.State.YELLOW, northLight, southLight, TrafficLights.State.RED);
                    lock.wait(2000);

                    updateLights(northLight, southLight, TrafficLights.State.RED, eastLight, westLight, TrafficLights.State.RED);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    private void updateLights(TrafficLights light1, TrafficLights light2, TrafficLights.State state1, TrafficLights light3, TrafficLights light4, TrafficLights.State state2) {
        Platform.runLater(() -> {
            light1.setState(state1);
            light2.setState(state1);
            light3.setState(state2);
            light4.setState(state2);
        });
    }
}
