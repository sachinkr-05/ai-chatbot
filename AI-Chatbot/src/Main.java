import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.animation.*;
import javafx.util.Duration;

public class Main extends Application {

    VBox chatBox;
    TextField input;
    BotEngine bot = new BotEngine();

    @Override
    public void start(Stage stage) {

        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(15));

        ScrollPane scroll = new ScrollPane(chatBox);
        scroll.setFitToWidth(true);

        input = new TextField();
        input.setPromptText("Type a message...");

        Button send = new Button("Send");

        HBox bottom = new HBox(10, input, send);
        bottom.setPadding(new Insets(10));

        VBox root = new VBox(scroll, bottom);

        send.setOnAction(e -> sendMessage());
        input.setOnAction(e -> sendMessage());

        Scene scene = new Scene(root, 500, 600);

        // ✅ CSS YAHI HONA CHAHIYE (andar method ke)
        scene.getStylesheets().add("file:resources/styles.css");

        stage.setTitle("🤖 AI Chatbot");
        stage.setScene(scene);
        stage.show();
    }

    void sendMessage() {
        String text = input.getText();
        if (text.isEmpty()) return;

        addBubble(text, true);

        PauseTransition delay = new PauseTransition(Duration.seconds(0.7));
        delay.setOnFinished(e -> {
            String reply = bot.getResponse(text);
            addBubble(reply, false);
        });
        delay.play();

        input.clear();
    }

    void addBubble(String msg, boolean isUser) {

        Label label = new Label(msg);
        label.setWrapText(true);
        label.setMaxWidth(250);

        HBox box = new HBox(label);
        box.setPadding(new Insets(5));

        if (isUser) {
            box.setAlignment(Pos.CENTER_RIGHT);
            label.getStyleClass().add("user-bubble");
        } else {
            box.setAlignment(Pos.CENTER_LEFT);
            label.getStyleClass().add("bot-bubble");
        }

        chatBox.getChildren().add(box);

        FadeTransition ft = new FadeTransition(Duration.millis(300), box);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public static void main(String[] args) {
        launch();
    }
}
Scene scene = new Scene(root, 500, 600);
scene.getStylesheets().add("file:resources/styles.css");