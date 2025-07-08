package com.data.ss2.controller;

import com.data.ss2.entity.Movie;
import com.data.ss2.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String showMovies(Model model) {
        List<Movie> movies = movieService.findAll();
        model.addAttribute("movies", movies);
        return "movie-list";
    }

    @GetMapping("/movies/add")
    public String showAddForm(Model model) {
        model.addAttribute("movie", new Movie());
        return "movie-add";
    }

    @PostMapping("/movies/add")
    public String addMovie(@ModelAttribute("movie") Movie movie, RedirectAttributes redirectAttributes) {
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("success", "Thêm phim thành công!");
        return "redirect:/movies";
    }

    @GetMapping("/movies/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Movie movie = movieService.findById(id);
        if (movie == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy phim!");
            return "redirect:/movies";
        }
        model.addAttribute("movie", movie);
        return "movie-edit";
    }

    @PostMapping("/movies/edit/{id}")
    public String editMovie(@PathVariable("id") Long id, @ModelAttribute("movie") Movie movie, RedirectAttributes redirectAttributes) {
        Movie existingMovie = movieService.findById(id);
        if (existingMovie == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy phim!");
            return "redirect:/movies";
        }
        movie.setId(id);
        movieService.save(movie);
        redirectAttributes.addFlashAttribute("success", "Cập nhật phim thành công!");
        return "redirect:/movies";
    }

    @PostMapping("/movies/delete/{id}")
    public String deleteMovie(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        movieService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Xóa phim thành công!");
        return "redirect:/movies";
    }
}
