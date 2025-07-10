package com.data.ss3.controller;

import com.data.ss3.dto.EmployeeDTO;
import com.data.ss3.entity.Employee;
import com.data.ss3.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/employees")
    public String getEmployees(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "2") int size,
                               @RequestParam(defaultValue = "id") String sortBy,
                               @RequestParam(defaultValue = "asc") String sortDir,
                               @RequestParam(required = false) String phoneNumber) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Employee> employeePage;

        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            employeePage = employeeRepository.findByPhoneNumberContaining(phoneNumber, pageable);
        } else {
            employeePage = employeeRepository.findAll(pageable);
        }

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("totalElements", employeePage.getTotalElements());
        model.addAttribute("size", size);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("phoneNumber", phoneNumber);

        return "employees";
    }

    // Hiển thị form thêm
    @GetMapping("/employees/add")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "employee-add";
    }

    // Xử lý thêm nhân viên
    @PostMapping("/employees/add")
    public String createEmployee(@ModelAttribute Employee employee,
                                 RedirectAttributes redirectAttributes) {
        try {
            employee.setCreatedAt(LocalDate.now());
            employeeRepository.save(employee);
            redirectAttributes.addFlashAttribute("success", "Thêm nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi thêm nhân viên!");
        }
        return "redirect:/employees";
    }

    // Hiển thị chi tiết
    @GetMapping("/employees/{id}")
    public String getEmployeeDetail(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee-detail";
        } else {
            return "redirect:/employees";
        }
    }

    // Hiển thị form chỉnh sửa
    @GetMapping("/employees/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            model.addAttribute("employee", employee.get());
            return "employee-edit";
        } else {
            return "redirect:/employees";
        }
    }

    // Xử lý cập nhật
    @PostMapping("/employees/{id}/edit")
    public String updateEmployee(@PathVariable Long id,
                                 @ModelAttribute Employee employee,
                                 RedirectAttributes redirectAttributes) {
        try {
            employee.setId(id);
            employeeRepository.save(employee);
            redirectAttributes.addFlashAttribute("success", "Cập nhật nhân viên thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi cập nhật nhân viên!");
        }
        return "redirect:/employees/" + id;
    }

    // Xóa nhân viên
    @GetMapping("/employees/{id}/delete")
    public String deleteEmployee(@PathVariable Long id,
                                 RedirectAttributes redirectAttributes) {
        try {
            if (employeeRepository.existsById(id)) {
                employeeRepository.deleteById(id);
                redirectAttributes.addFlashAttribute("success", "Xóa nhân viên thành công!");
            } else {
                redirectAttributes.addFlashAttribute("error", "Không tìm thấy nhân viên!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa nhân viên!");
        }
        return "redirect:/employees";
    }
}
