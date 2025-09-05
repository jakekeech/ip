import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import dume.Dume;

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Dume dume;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dumeImage = new Image(this.getClass().getResourceAsStream("/images/DaDume.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
            DialogBox.getDumeDialog("Hello! I'm DUM-E\nWhat can I do for you?", dumeImage)
        );
    }

    public void setDume(Dume d) {
        dume = d;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dume.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDumeDialog(response, dumeImage)
        );
        userInput.clear();
    }
}