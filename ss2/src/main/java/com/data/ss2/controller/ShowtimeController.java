package com.data.ss2.controller;

import com.data.ss2.entity.Showtime;
import com.data.ss2.entity.Movie;
import com.data.ss2.entity.ScreenRoom;
import com.data.ss2.service.MovieService;
import com.data.ss2.service.ScreenRoomService;
import com.data.ss2.service.ShowtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ShowtimeController {

    private final ShowtimeService showtimeService;
    private final MovieService movieService;
    private final ScreenRoomService screenRoomService;

    @Autowired
    public ShowtimeController(ShowtimeService showtimeService, MovieService movieService, ScreenRoomService screenRoomService) {
        this.showtimeService = showtimeService;
        this.movieService = movieService;
        this.screenRoomService = screenRoomService;
    }

    @GetMapping("/showtimes")
    public String showShowtimes(
            @RequestParam(value = "movieId", required = false) Long movieId,
            @RequestParam(value = "screenRoomId", required = false) Long screenRoomId,
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model) {

        List<Movie> movies = movieService.findAll();
        List<ScreenRoom> screenRooms = screenRoomService.findAll();
        List<Showtime> showtimes = showtimeService.filterShowtimes(movieId, screenRoomId, date);

        model.addAttribute("showtimes", showtimes);
        model.addAttribute("movies", movies);
        model.addAttribute("screenRooms", screenRooms);
        model.addAttribute("selectedMovieId", movieId);
        model.addAttribute("selectedScreenRoomId", screenRoomId);
        model.addAttribute("selectedDate", date);

        return "showtime-list";
    }


    @GetMapping("/showtimes/add")
    public String showAddForm(Model model) {
        model.addAttribute("showtime", new Showtime());
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-add";
    }

    @PostMapping("/showtimes/add")
    public String addShowtime(@ModelAttribute("showtime") Showtime showtime, RedirectAttributes redirectAttributes) {
        // Lấy lại entity Movie và ScreenRoom từ id
        Movie movie = movieService.findById(showtime.getMovie().getId());
        ScreenRoom screenRoom = screenRoomService.findById(showtime.getScreenRoom().getId());
        showtime.setMovie(movie);
        showtime.setScreenRoom(screenRoom);

        showtimeService.save(showtime);
        redirectAttributes.addFlashAttribute("success", "Thêm lịch chiếu thành công!");
        return "redirect:/showtimes";
    }

    @GetMapping("/showtimes/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Showtime showtime = showtimeService.findById(id);
        if (showtime == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy lịch chiếu!");
            return "redirect:/showtimes";
        }
        model.addAttribute("showtime", showtime);
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("screenRooms", screenRoomService.findAll());
        return "showtime-edit";
    }

    @PostMapping("/showtimes/edit/{id}")
    public String editShowtime(@PathVariable("id") Long id, @ModelAttribute("showtime") Showtime showtime, RedirectAttributes redirectAttributes) {
        Showtime existing = showtimeService.findById(id);
        if (existing == null) {
            redirectAttributes.addFlashAttribute("error", "Không tìm thấy lịch chiếu!");
            return "redirect:/showtimes";
        }
        // Lấy lại entity Movie và ScreenRoom từ id
        Movie movie = movieService.findById(showtime.getMovie().getId());
        ScreenRoom screenRoom = screenRoomService.findById(showtime.getScreenRoom().getId());
        showtime.setMovie(movie);
        showtime.setScreenRoom(screenRoom);

        showtime.setId(id);
        showtimeService.save(showtime);
        redirectAttributes.addFlashAttribute("success", "Cập nhật lịch chiếu thành công!");
        return "redirect:/showtimes";
    }

    @PostMapping("/showtimes/delete/{id}")
    public String deleteShowtime(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        showtimeService.deleteById(id);
        redirectAttributes.addFlashAttribute("success", "Xóa lịch chiếu thành công!");
        return "redirect:/showtimes";
    }
}
