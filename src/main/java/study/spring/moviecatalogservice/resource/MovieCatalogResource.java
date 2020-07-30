package study.spring.moviecatalogservice.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import study.spring.moviecatalogservice.models.CatalogItem;
import study.spring.moviecatalogservice.models.Movie;
import study.spring.moviecatalogservice.models.Rating;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    RestTemplate restTemplate;

//    /**
//     * @param userId
//     * @return
//     */
//    @RequestMapping("/{userId}")
//    public List<CatalogItem> getCatalog(@PathVariable String userId) {
//
//        // get all rated movie Ids
//        List<Rating> ratings = Arrays.asList(
//                new Rating("1234", 4),
//                new Rating("5678", 3)
//        );
//
//        List<CatalogItem> list = new ArrayList<>();
//        for (Rating rating : ratings) {
//            Movie movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId(), Movie.class);
//            Object o = new CatalogItem(movie.getName(), "Test", rating.getRating());
//            list.add((CatalogItem) o);
//        }
//        return list;
//
//        // for each movie ID all movie info service and get details
//
//        // Put them all together
//    }

    @GetMapping("/movieRating")
    public CatalogItem getMovieRating(@RequestParam(name = "movieName") String movieName) {

        Movie movie = restTemplate.getForObject("http://localhost:8081/movies/movie/" + movieName, Movie.class);
        Rating rating = restTemplate.getForObject("http://localhost:8082/ratingsdata/movieId/" + movie.getMovieId(), Rating.class);
        return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
    }


}
