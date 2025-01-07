package Notes;
import java.util.ArrayList;

public class Notes { // Notes class to manage a collection of currency notes
    private ArrayList<NotesAction.Note> notes; // List to store notes

    // No-argument constructor
    public Notes() {
        this.notes = new ArrayList<>(); // Initializing the notes list
    }

    // Getter method for notes
    public ArrayList<NotesAction.Note> getNotes() {
        return notes; // Returning notes
    }

    // Setter method for notes
    public void setNotes(ArrayList<NotesAction.Note> notes) {
        this.notes = notes; // Setting notes
    }
}
