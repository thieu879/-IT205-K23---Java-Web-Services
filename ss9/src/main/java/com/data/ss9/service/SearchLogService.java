package com.data.ss9.service;
import com.data.ss9.model.entity.Movie;
import com.data.ss9.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SearchLogService {

    private static final Logger logger = LoggerFactory.getLogger(SearchLogService.class);

    @Autowired
    private MovieRepository movieRepository;

    private static final String LOG_FILE_PATH = "logs/app.log";

    // Lấy danh sách từ khóa tìm kiếm và số lần tìm kiếm
    public Map<String, Integer> getSearchKeywords() {
        Map<String, Integer> searchKeywords = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILE_PATH))) {
            String line;
            Pattern pattern = Pattern.compile("Tìm kiếm: '([^']+)'");

            while ((line = reader.readLine()) != null) {
                if (line.contains("Tìm kiếm:")) {
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String keyword = matcher.group(1).trim().toLowerCase();
                        searchKeywords.put(keyword, searchKeywords.getOrDefault(keyword, 0) + 1);
                    }
                }
            }

            logger.info("Đã phân tích log và tìm thấy {} từ khóa tìm kiếm", searchKeywords.size());

        } catch (IOException e) {
            logger.error("Lỗi khi đọc file log: {}", e.getMessage());
        }

        // Sắp xếp theo số lần tìm kiếm giảm dần (Java 8 compatible)
        LinkedHashMap<String, Integer> sortedMap = new LinkedHashMap<>();
        searchKeywords.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        return sortedMap;
    }

    public List<Movie> getSuggestedMovies() {
        Map<String, Integer> searchKeywords = getSearchKeywords();
        Set<Movie> suggestedMovies = new HashSet<>();

        try {
            List<Movie> allMovies = movieRepository.findAll();

            for (String keyword : searchKeywords.keySet()) {
                List<Movie> matchingMovies = allMovies.stream()
                        .filter(movie -> movie.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                        .collect(Collectors.toList());

                suggestedMovies.addAll(matchingMovies);
            }

            logger.info("Tìm thấy {} phim gợi ý dựa trên {} từ khóa",
                    suggestedMovies.size(), searchKeywords.size());

        } catch (Exception e) {
            logger.error("Lỗi khi tạo danh sách gợi ý: {}", e.getMessage());
        }

        return new ArrayList<>(suggestedMovies);
    }

    public Map<String, Integer> getTopSearchKeywords(int limit) {
        Map<String, Integer> allKeywords = getSearchKeywords();

        // Java 8 compatible way
        LinkedHashMap<String, Integer> topKeywords = new LinkedHashMap<>();
        allKeywords.entrySet()
                .stream()
                .limit(limit)
                .forEach(entry -> topKeywords.put(entry.getKey(), entry.getValue()));

        return topKeywords;
    }
}
