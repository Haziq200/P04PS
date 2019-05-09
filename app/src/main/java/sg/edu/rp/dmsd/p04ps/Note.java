package sg.edu.rp.dmsd.p04ps;

public class Note {
    private int id;
    private  String noteContent;
    private String stars;


    public Note(int id, String noteContent, String stars) {
        this.id = id;
        this.noteContent = noteContent;
        this.stars = stars;
    }

    public int getId() {
        return id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public String getStars() {
        return stars;
    }
}
