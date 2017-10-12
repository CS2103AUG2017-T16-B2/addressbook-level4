package seedu.address.ui;

import java.io.IOException;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Modality;
import javafx.stage.Stage;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Popups a window to interact with user to confirm a edit/deletion of a person
 */
public class PopupWindow extends UiPart<Region> implements EventHandler<ActionEvent> {
    private static final String FXML = "PopupWindow.fxml";
    private static final String nothing = "-";

    private Model model;

    private boolean handler = false;

    private Stage stage;

    @FXML
    private GridPane gridPane;
    @FXML
    private Label type;
    @FXML
    private Label oldName;
    @FXML
    private Label oldPhone;
    @FXML
    private Label oldAddress;
    @FXML
    private Label oldEmail;
    @FXML
    private Label oldTags;
    @FXML
    private Label newName;
    @FXML
    private Label newPhone;
    @FXML
    private Label newAddress;
    @FXML
    private Label newEmail;
    @FXML
    private Label newTags;
    @FXML
    private Button yes;
    @FXML
    private Button no;

    public PopupWindow(String inputText) throws IllegalValueException {
        super(FXML);
        if (inputText.contains("delete")) {
            String[] arrText = inputText.split(" ");
            int index = Integer.parseInt(arrText[1]);
            List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
            ReadOnlyPerson personToDelete = lastShownList.get(index);
            personDeletedDetails(personToDelete);

        } else if (inputText.contains("edit")) {
            String[] arrText = inputText.split(" ");
            int index = Integer.parseInt(arrText[1]);
            List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
            ReadOnlyPerson personToDelete = lastShownList.get(index);
            personEditedDetails(personToDelete, arrText);
        }

    }

    /**
     * @param personToDelete
     * @param text
     * display edited persons old and new data
     */
    private void personEditedDetails(ReadOnlyPerson personToDelete, String [] text) {
        oldName.setText(String.valueOf(personToDelete.getName()));
        oldAddress.setText(String.valueOf(personToDelete.getAddress()));
        oldEmail.setText(String.valueOf(personToDelete.getEmail()));
        oldPhone.setText(String.valueOf(personToDelete.getPhone()));
        oldTags.setText(String.valueOf(personToDelete.getTags()));

        for (int i = 2; i < text.length; i++) {
            switch (text[i].substring(0, 0)) {
            case "n":
                newName.setText(text[i].substring(2, text[i].length()));
                break;
            case "p":
                newPhone.setText(text[i].substring(2, text[i].length()));
                break;
            case "e":
                newEmail.setText(text[i].substring(2, text[i].length()));
                break;
            case "a":
                newAddress.setText(text[i].substring(2, text[i].length()));
                break;
            case "t":
                newTags.setText(text[i].substring(2, text[i].length()));
                break;
            default:
                break;
            }
        }
    }

    /**
     * @param personToDelete displays deleted persons new and old data
     */
    private void personDeletedDetails(ReadOnlyPerson personToDelete) {
        oldName.setText(String.valueOf(personToDelete.getName()));
        oldAddress.setText(String.valueOf(personToDelete.getAddress()));
        oldEmail.setText(String.valueOf(personToDelete.getEmail()));
        oldPhone.setText(String.valueOf(personToDelete.getPhone()));
        oldTags.setText(String.valueOf(personToDelete.getTags()));
        newAddress.setText(nothing);
        newEmail.setText(nothing);
        newName.setText(nothing);
        newPhone.setText(nothing);
        newTags.setText(nothing);
    }



    public boolean getAnswer() throws IOException {
        stage = new Stage();
        stage.setScene(new Scene(getRoot()));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirmation Page");
        yes.setOnAction(this);
        no.setOnAction(this);
        stage.showAndWait();
        return handler;
    }

    @Override
    public void handle(ActionEvent event) {
        if (event.getSource() == yes) {
            handler = true;
        }
    }
}


