package com.data.ss9.controller;

import com.data.ss9.model.entity.Movie;
import com.data.ss9.repository.MovieRepository;
import com.data.ss9.service.SearchLogService;
import com.data.ss9.utils.ColorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private SearchLogService searchLogService;
    @PostMapping
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        logger.info("Received request to add movie: {}", movie.getTitle());
        if (movieRepository.existsByTitle(movie.getTitle())) {
            logger.warn("Movie with title '{}' already exists", movie.getTitle());
            return ResponseEntity.badRequest().body(null);
        }
        Movie savedMovie = movieRepository.save(movie);
        logger.info("Movie added successfully: {}", savedMovie.getTitle());
        return ResponseEntity.ok(savedMovie);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movieUpdate) {
        try {
            Optional<Movie> existingOpt = movieRepository.findById(id);

            if (!existingOpt.isPresent()) {
                logger.error("Không tìm thấy phim với ID: {}", ColorUtil.red(id.toString()));
                throw new RuntimeException("Không tìm thấy phim với ID: " + id);
            }

            Movie existing = existingOpt.get();

            String oldInfo = String.format("ID: %d, Tên: %s, Mô tả: %s",
                    existing.getId(), existing.getTitle(), existing.getDescription());
            logger.info("Thông tin cũ: {}", ColorUtil.yellow(oldInfo));

            existing.setTitle(movieUpdate.getTitle());
            existing.setDescription(movieUpdate.getDescription());
            existing.setReleaseDate(movieUpdate.getReleaseDate());
            existing.setPoster(movieUpdate.getPoster());

            Movie updated = movieRepository.save(existing);

            String newInfo = String.format("ID: %d, Tên: %s, Mô tả: %s",
                    updated.getId(), updated.getTitle(), updated.getDescription());
            logger.info("Thông tin mới: {}", ColorUtil.blue(newInfo));

            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            logger.error("Lỗi khi cập nhật phim: {}", ColorUtil.red(e.getMessage()));
            throw new RuntimeException("Lỗi cập nhật phim: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        logger.info("Received request to delete movie with ID: {}", id);
        if (!movieRepository.existsById(id)) {
            logger.error("Movie with ID {} not found", ColorUtil.red(id.toString()));
            return ResponseEntity.notFound().build();
        }
        movieRepository.deleteById(id);
        logger.info("Movie with ID {} deleted successfully", ColorUtil.green(id.toString()));
        return ResponseEntity.ok("Movie deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String searchMovie) {
        long startTime = System.currentTimeMillis();

        try {
            List<Movie> movies;

            if (searchMovie != null && !searchMovie.trim().isEmpty()) {
                movies = movieRepository.findByTitleContaining(searchMovie.trim());

                long duration = System.currentTimeMillis() - startTime;
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String searchInfo = String.format("Tìm kiếm: '%s', Kết quả: %d phim, Thời gian thực hiện: %dms, Lúc: %s",
                        searchMovie, movies.size(), duration, currentTime);

                logger.info(ColorUtil.blue(searchInfo));

            } else {
                movies = movieRepository.findAll();

                long duration = System.currentTimeMillis() - startTime;
                String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                String allMoviesInfo = String.format("Lấy tất cả phim, Số lượng: %d phim, Thời gian thực hiện: %dms, Lúc: %s",
                        movies.size(), duration, currentTime);

                logger.info(ColorUtil.blue(allMoviesInfo));
            }

            return ResponseEntity.ok(movies);

        } catch (Exception e) {
            long duration = System.currentTimeMillis() - startTime;
            logger.error("Lỗi khi lấy danh sách phim: {}, Thời gian: {}ms",
                    ColorUtil.red(e.getMessage()), duration);
            throw new RuntimeException("Lỗi lấy danh sách phim: " + e.getMessage());
        }
    }
    @GetMapping("/search-logs")
    public ResponseEntity<Map<String, Object>> getSearchLogs(@RequestParam(defaultValue = "10") int limit) {
        try {
            logger.info("Bắt đầu phân tích log tìm kiếm");

            Map<String, Integer> allKeywords = searchLogService.getSearchKeywords();
            Map<String, Integer> topKeywords = searchLogService.getTopSearchKeywords(limit);

            int totalSearches = 0;
            for (Integer count : allKeywords.values()) {
                totalSearches += count;
            }

            Map<String, Object> response = new HashMap<>();
            response.put("totalKeywords", allKeywords.size());
            response.put("totalSearches", totalSearches);
            response.put("topKeywords", topKeywords);
            response.put("allKeywords", allKeywords);

            logger.info("Phân tích log hoàn thành: {} từ khóa, {} lần tìm kiếm",
                    allKeywords.size(), totalSearches);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Lỗi khi phân tích log tìm kiếm: {}", e.getMessage());
            throw new RuntimeException("Lỗi phân tích log: " + e.getMessage());
        }
    }

    @GetMapping("/suggestions")
    public ResponseEntity<Map<String, Object>> getSuggestions() {
        try {
            logger.info("Bắt đầu tạo danh sách phim gợi ý");

            List<Movie> suggestedMovies = searchLogService.getSuggestedMovies();
            Map<String, Integer> topKeywords = searchLogService.getTopSearchKeywords(5);

            Map<String, Object> response = new HashMap<>();
            response.put("suggestedMovies", suggestedMovies);
            response.put("totalSuggestions", suggestedMovies.size());
            response.put("basedOnKeywords", new ArrayList<>(topKeywords.keySet()));
            response.put("message", "Phim gợi ý dựa trên lịch sử tìm kiếm");

            logger.info("Tạo gợi ý hoàn thành: {} phim dựa trên {} từ khóa phổ biến",
                    suggestedMovies.size(), topKeywords.size());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("Lỗi khi tạo gợi ý phim: {}", e.getMessage());
            throw new RuntimeException("Lỗi tạo gợi ý: " + e.getMessage());
        }
    }
}
