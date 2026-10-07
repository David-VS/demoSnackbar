package be.ehb.demosnackbar.controller;

import be.ehb.demosnackbar.model.Review;
import be.ehb.demosnackbar.model.Snack;
import be.ehb.demosnackbar.repositories.ReviewRepository;
import be.ehb.demosnackbar.repositories.SnackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/snacks")
public class SnackController {

    private SnackRepository snackdatasource;
    private ReviewRepository reviewdatasource;

    @Autowired
    public SnackController(SnackRepository snackdatasource, ReviewRepository reviewdatasource) {
        this.snackdatasource = snackdatasource;
        this.reviewdatasource = reviewdatasource;
    }

    @GetMapping
    public Iterable<Snack> getAllSnacks(){
        return snackdatasource.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity<Snack> getSnackByID(@RequestParam String id){
        if(snackdatasource.existsById(id)) {
            Snack snack = snackdatasource.findById(id).get();
            ResponseEntity<Snack> response = new ResponseEntity<>(snack, HttpStatus.OK);
            return response;
        }else{
            ResponseEntity<Snack> response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return response;
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSnack(@RequestParam String id){
        if(snackdatasource.existsById(id)) {
            snackdatasource.deleteById(id);

            HttpHeaders headers = new HttpHeaders();
            headers.add("type", "text/json");
            return new ResponseEntity<String>("'t is weg", headers,  HttpStatus.OK);
        }else{
            HttpHeaders headers = new HttpHeaders();
            headers.add("type", "text/json");

            return new ResponseEntity<String>( "Het bestond zelf niet jong", headers, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search")
    public List<Snack> getAllSnacksByName(@RequestParam String searchQuery){
        return snackdatasource.findByNameContainsIgnoreCase(searchQuery);
    }

    @PostMapping
    public void addSnack(@RequestParam String name,
                         @RequestParam int calorycount,
                         @RequestParam float price){
        Snack toSave = new Snack(name, calorycount, price);
        snackdatasource.save(toSave);
    }

    @PostMapping("/review")
    public void postReview(@RequestParam int score,
                           @RequestParam String description,
                           @RequestParam String snackId){
        Review review = new Review();
        review.setScore(score);
        review.setDescription(description);

        Snack toeTeVoegen = snackdatasource.findById(snackId).get();
        review.setSnack(toeTeVoegen);

        reviewdatasource.save(review);
    }
}
