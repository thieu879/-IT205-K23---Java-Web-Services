package com.data.btthemss3.controller;

import com.data.btthemss3.entity.City;
import com.data.btthemss3.entity.Country;
import com.data.btthemss3.service.CityService;
import com.data.btthemss3.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cities")
public class CityController {
    private final CityService cityService;
    private final CountryService countryService;

    public CityController(CityService cityService, CountryService countryService) {
        this.cityService = cityService;
        this.countryService = countryService;
    }

    // Hiển thị danh sách thành phố
    @GetMapping
    public String listCities(Model model) {
        List<City> cities = cityService.findAll();
        model.addAttribute("cities", cities);
        return "cities/list";
    }

    // Hiển thị form thêm thành phố
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryService.findAll());
        return "cities/add";
    }

    // Xử lý thêm thành phố
    @PostMapping("/add")
    public String addCity(@ModelAttribute City city, RedirectAttributes redirectAttributes) {
        City savedCity = cityService.save(city);
        if (savedCity != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm thành phố thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm thành phố!");
        }
        return "redirect:/cities";
    }

    // Hiển thị form sửa thành phố
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        City city = cityService.findById(id);
        if (city == null) {
            return "redirect:/cities";
        }
        model.addAttribute("city", city);
        model.addAttribute("countries", countryService.findAll());
        return "cities/edit";
    }

    // Xử lý cập nhật thành phố
    @PostMapping("/edit/{id}")
    public String updateCity(@PathVariable Integer id, @ModelAttribute City city,
                             RedirectAttributes redirectAttributes) {
        city.setCityId(id);
        City updatedCity = cityService.update(city);
        if (updatedCity != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật thành phố thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật thành phố!");
        }
        return "redirect:/cities";
    }

    // Xóa thành phố
    @GetMapping("/delete/{id}")
    public String deleteCity(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = cityService.deleteById(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa thành phố thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa thành phố!");
        }
        return "redirect:/cities";
    }

    // Tìm kiếm thành phố
    @GetMapping("/search")
    public String searchCities(@RequestParam(required = false) String keyword, Model model) {
        List<City> cities;
        if (keyword != null && !keyword.trim().isEmpty()) {
            cities = cityService.findByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            cities = cityService.findAll();
        }
        model.addAttribute("cities", cities);
        return "cities/list";
    }

    // Xem chi tiết thành phố
    @GetMapping("/detail/{id}")
    public String viewCity(@PathVariable Integer id, Model model) {
        City city = cityService.findById(id);
        if (city == null) {
            return "redirect:/cities";
        }
        model.addAttribute("city", city);
        return "cities/detail";
    }
}
