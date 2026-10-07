package be.ehb.demosnackbar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Min(value = 0)
    @Max(value = 5)
    private int score;

    @Size(min = 3, max = 180)
    private String description;

    @JsonIgnore  //om circulaire verwijzingen te vermijden, probeer gerust zonder om te zien wat misloopt
    @ManyToOne()
    @JoinColumn(name = "snackId")
    private Snack snack;

    public Review() {
    }

    public Review(int id, int score, String description) {
        this.id = id;
        this.score = score;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JsonIgnore
    public Snack getSnack() {
        return snack;
    }

    public void setSnack(Snack snack) {
        this.snack = snack;
    }
}
