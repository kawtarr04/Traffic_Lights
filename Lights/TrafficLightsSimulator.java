package Lights;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class TrafficLightsSimulator extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        TrafficLights northLight = createTrafficLight();
        TrafficLights southLight = createTrafficLight();
        TrafficLights eastLight = createTrafficLight();
        TrafficLights westLight = createTrafficLight();

        grid.add(createVBoxForLight(southLight), 100, 0);
        grid.add(createVBoxForLight(eastLight), 200, 100);
        grid.add(createVBoxForLight(northLight), 100, 200);
        grid.add(createVBoxForLight(westLight), 0, 100);

        TrafficLightsController controller = new TrafficLightsController(northLight, southLight, eastLight, westLight);
        Thread thread = new Thread(controller);
        thread.setDaemon(true);
        thread.start();

        Scene scene = new Scene(grid, 200, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Traffic Lights Simulator");
        primaryStage.show();
    }

    private TrafficLights createTrafficLight() {
        Circle red = new Circle(15, Color.GRAY);
        Circle yellow = new Circle(15, Color.GRAY);
        Circle green = new Circle(15, Color.GRAY);
        return new TrafficLights(red, yellow, green);
    }

    private VBox createVBoxForLight(TrafficLights light) {
        VBox box = new VBox();
        box.getChildren().addAll(light.redLight, light.yellowLight, light.greenLight);
        box.setSpacing(5);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
