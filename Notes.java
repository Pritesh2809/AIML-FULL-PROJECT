import java.util.ArrayList;

public class Notes { // Notes class
    private ArrayList<NotesAction.Note> notes; // List to store notes

    public Notes() {
        this.notes = new ArrayList<>();
    }

    // Getter for notes
    public ArrayList<NotesAction.Note> getNotes() {
        return notes;
    }

}
