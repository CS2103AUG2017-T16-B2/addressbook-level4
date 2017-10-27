package view;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import seedu.address.model.Model;
import seedu.address.model.person.ReadOnlyPerson;

/**
 * Popups a window to interact with user to confirm a edit/deletion of a person
 */
public class PopupWindow {
    private static final String FXML = "PopupWindow.fxml";
    private static final String nothing = "-";

    private Model model;
    private TextField textField = new TextField();
    private String textFieldData = "";

    private boolean okClicked = false;

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

    @FXML
    private void initialize(){
    }

    public void setDialogStage(Stage dialogStage){
            this.stage = dialogStage;
    }

    /**
     * @param personToEdit
     * @param text
     * display edited persons old and new data
     */
    public void personEditedDetails(ReadOnlyPerson personToEdit, String text) {
        type.setText(String.valueOf("Edit"));
        oldName.setText(String.valueOf(personToEdit.getName()));
        oldAddress.setText(String.valueOf(personToEdit.getAddress()));
        oldEmail.setText(String.valueOf(personToEdit.getEmail()));
        oldPhone.setText(String.valueOf(personToEdit.getPhone()));
        oldTags.setText(String.valueOf(personToEdit.getTags()));

        String [] textBreakDown = text.split(" ");
        for (int i = 2; i < textBreakDown.length; i++) {
            switch (textBreakDown[i].substring(0, 0)) {
            case "n":
                newName.setText(textBreakDown[i].substring(2, textBreakDown[i].length()));
                break;
            case "p":
                newPhone.setText(textBreakDown[i].substring(2, textBreakDown[i].length()));
                break;
            case "e":
                newEmail.setText(textBreakDown[i].substring(2, textBreakDown[i].length()));
                break;
            case "a":
                newAddress.setText(textBreakDown[i].substring(2, textBreakDown[i].length()));
                break;
            case "t":
                newTags.setText(textBreakDown[i].substring(2, textBreakDown[i].length()));
                break;
            default:
                break;
            }
        }
    }

    /**
     * @param personToDelete displays deleted persons new and old data
     */
    public void personDeletedDetails(ReadOnlyPerson personToDelete) {
        type.setText(String.valueOf("Delete"));
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

    public ReadOnlyPerson getPerson(String txt){
        String [] breakDown = txt.split(" ");
        int index = Integer.parseInt(breakDown[1]);
        List<ReadOnlyPerson> lastShownList = model.getFilteredPersonList();
        ReadOnlyPerson person = lastShownList.get(index);
        return person;
    }

    public boolean isOkClicked(){
        return okClicked;
    }

    /**
     * returns true if clicked ok and closes the popup
     */
    @FXML
    public void handleOk() {
        okClicked = true;
        stage.close();
    }

    @FXML
    public void handleClose(){
        stage.close();
    }
}


