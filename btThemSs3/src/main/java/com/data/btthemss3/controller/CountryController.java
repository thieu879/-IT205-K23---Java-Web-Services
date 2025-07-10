package com.data.btthemss3.controller;

import com.data.btthemss3.entity.Country;
import com.data.btthemss3.service.CountryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/countries")
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    // Hiển thị danh sách quốc gia
    @GetMapping
    public String listCountries(Model model) {
        List<Country> countries = countryService.findAll();
        model.addAttribute("countries", countries);
        return "countries/list";
    }

    // Hiển thị form thêm quốc gia
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("country", new Country());
        return "countries/add";
    }

    // Xử lý thêm quốc gia
    @PostMapping("/add")
    public String addCountry(@ModelAttribute Country country, RedirectAttributes redirectAttributes) {
        Country savedCountry = countryService.save(country);
        if (savedCountry != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Thêm quốc gia thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi thêm quốc gia!");
        }
        return "redirect:/countries";
    }

    // Hiển thị form sửa quốc gia
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Country country = countryService.findById(id);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "countries/edit";
    }

    // Xử lý cập nhật quốc gia
    @PostMapping("/edit/{id}")
    public String updateCountry(@PathVariable Integer id, @ModelAttribute Country country,
                                RedirectAttributes redirectAttributes) {
        country.setCountryId(id);
        Country updatedCountry = countryService.update(country);
        if (updatedCountry != null) {
            redirectAttributes.addFlashAttribute("successMessage", "Cập nhật quốc gia thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi cập nhật quốc gia!");
        }
        return "redirect:/countries";
    }

    // Xóa quốc gia
    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        boolean deleted = countryService.deleteById(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "Xóa quốc gia thành công!");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Có lỗi xảy ra khi xóa quốc gia!");
        }
        return "redirect:/countries";
    }

    // Tìm kiếm quốc gia
    @GetMapping("/search")
    public String searchCountries(@RequestParam(required = false) String keyword, Model model) {
        List<Country> countries;
        if (keyword != null && !keyword.trim().isEmpty()) {
            countries = countryService.findByName(keyword);
            model.addAttribute("keyword", keyword);
        } else {
            countries = countryService.findAll();
        }
        model.addAttribute("countries", countries);
        return "countries/list";
    }

    // Xem chi tiết quốc gia
    @GetMapping("/detail/{id}")
    public String viewCountry(@PathVariable Integer id, Model model) {
        Country country = countryService.findById(id);
        if (country == null) {
            return "redirect:/countries";
        }
        model.addAttribute("country", country);
        return "countries/detail";
    }
}
