import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FirstJavaFxExample extends Application {

	Label l1;
	Button b1;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		l1 = new Label("Hello");
		b1 = new Button("Count Down");
		b1.setOnAction((event) -> {
			Task<Void> task = new Task<Void>() {
				@Override
				protected Void call() throws Exception {
					int x = 5;
					while (x > 0) {
						final int xf = x;
						Platform.runLater(() -> {
							System.out.println("x=" + xf);
							l1.setText(String.valueOf(xf));
						});
						x--;
						Thread.sleep(1000);
//						long millis = System.currentTimeMillis();
//						while (System.currentTimeMillis() - millis < 1000)
//							;
					}
					return null;
				}
			};
			Thread th = new Thread(task);
			th.start();
		});

		VBox root = new VBox();
		root.getChildren().add(l1);
		root.getChildren().add(b1);
		Scene scene = new Scene(root, 400, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Thread update UI");
		primaryStage.show();
	}
}
