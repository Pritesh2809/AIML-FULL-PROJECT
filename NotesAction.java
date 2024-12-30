import java.util.ArrayList;

public class NotesAction { // NotesAction class

    // Base Note class
    public static class Note {
        private int value; // Variable for note value

        public Note(int value) { // Constructor for Note class
            this.value = value; // Initializing note value
        }

        public int getValue() { // Getter method for note value
            return value; // Returning note value
        }
    }

    // Rs100Note class
    public static class Rs100Note extends Note { // Declaring Rs100Note class that extends Note
        public Rs100Note() { // Constructor for Rs100Note
            super(100); // Calling constructor of superclass Note with value 100
        }
    }

    // Rs200Note class
    public static class Rs200Note extends Note { // Declaring Rs200Note class that extends Note
        public Rs200Note() { // Constructor for Rs200Note
            super(200); // Calling constructor of superclass Note with value 200
        }
    }

    // Rs500Note class
    public static class Rs500Note extends Note { // Declaring Rs500Note class that extends Note
        public Rs500Note() { // Constructor for Rs500Note
            super(500); // Calling constructor of superclass Note with value 500
        }
    }

    // Rs2000Note class
    public static class Rs2000Note extends Note { // Declaring Rs2000Note class that extends Note
        public Rs2000Note() { // Constructor for Rs2000Note
            super(2000); // Calling constructor of superclass Note with value 2000
        }
    }

    // Method to add notes
    public static void addNotes(Notes notes, int num2000, int num500, int num200, int num100) {
        for (int i = 0; i < num2000; i++) {
            notes.getNotes().add(new Rs2000Note());
        }
        for (int i = 0; i < num500; i++) {
            notes.getNotes().add(new Rs500Note());
        }
        for (int i = 0; i < num200; i++) {
            notes.getNotes().add(new Rs200Note());
        }
        for (int i = 0; i < num100; i++) {
            notes.getNotes().add(new Rs100Note());
        }
    }

    // Method to withdraw notes
    public static boolean withdrawNotes(Notes notes, double amount) {
        ArrayList<Note> withdrawnNotes = new ArrayList<>();

        while (amount >= 2000 && containsNoteOfValue(notes, 2000)) {
            amount -= 2000;
            withdrawnNotes.add(removeNoteOfValue(notes, 2000));
        }
        while (amount >= 500 && containsNoteOfValue(notes, 500)) {
            amount -= 500;
            withdrawnNotes.add(removeNoteOfValue(notes, 500));
        }
        while (amount >= 200 && containsNoteOfValue(notes, 200)) {
            amount -= 200;
            withdrawnNotes.add(removeNoteOfValue(notes, 200));
        }
        while (amount >= 100 && containsNoteOfValue(notes, 100)) {
            amount -= 100;
            withdrawnNotes.add(removeNoteOfValue(notes, 100));
        }

        if (amount == 0) {
            System.out.println("Withdraw successful. Notes dispensed: ");
            displayWithdrawnNotes(withdrawnNotes);
            return true;
        } else {
            notes.getNotes().addAll(withdrawnNotes);
            return false;
        }
    }

    // Method to remove a note
    private static Note removeNoteOfValue(Notes notes, int value) {
        for (int i = 0; i < notes.getNotes().size(); i++) {
            if (notes.getNotes().get(i).getValue() == value) {
                return notes.getNotes().remove(i);
            }
        }
        return null; // This should never happen if called correctly
    }

    // Method to check if a note exists
    private static boolean containsNoteOfValue(Notes notes, int value) {
        for (Note note : notes.getNotes()) {
            if (note.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    // Method to display withdrawn notes
    private static void displayWithdrawnNotes(ArrayList<Note> withdrawnNotes) {
        int count2000 = 0, count500 = 0, count200 = 0, count100 = 0;
        for (Note note : withdrawnNotes) {
            switch (note.getValue()) {
                case 2000:
                    count2000++;
                    break;
                case 500:
                    count500++;
                    break;
                case 200:
                    count200++;
                    break;
                case 100:
                    count100++;
                    break;
            }
        }

        System.out.println(count2000 + " x 2000 Rs, " +
                count500 + " x 500 Rs, " +
                count200 + " x 200 Rs, " +
                count100 + " x 100 Rs");
    }

    // Method to convert Notes to String
    public static String notesToString(Notes notes) {
        int count2000 = 0, count500 = 0, count200 = 0, count100 = 0;
        for (Note note : notes.getNotes()) {
            switch (note.getValue()) {
                case 2000:
                    count2000++;
                    break;
                case 500:
                    count500++;
                    break;
                case 200:
                    count200++;
                    break;
                case 100:
                    count100++;
                    break;
            }
        }

        return "2000 Rs notes: " + count2000 + "\n" +
                "500 Rs notes: " + count500 + "\n" +
                "200 Rs notes: " + count200 + "\n" +
                "100 Rs notes: " + count100;
    }
}
