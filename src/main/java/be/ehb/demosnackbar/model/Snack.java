package be.ehb.demosnackbar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Snack {
    @Id
    private String name;
    private int calorycount;
    private float price;

    @OneToMany(mappedBy = "snack")
    private List<Review> reviews;

    //!deze is wel sowieso nodig
    public Snack() {
    }

    public Snack(String name, int calorycount, float price) {
        this.name = name;
        this.calorycount = calorycount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCalorycount() {
        return calorycount;
    }

    public void setCalorycount(int calorycount) {
        this.calorycount = calorycount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
