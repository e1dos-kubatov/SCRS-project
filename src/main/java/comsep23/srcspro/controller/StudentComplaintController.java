package comsep23.srcspro.controller;


import comsep23.srcspro.dto.complaint.ComplaintCreateDTO;
import comsep23.srcspro.dto.complaint.ComplaintListDTO;
import comsep23.srcspro.dto.department.PageResponseDTO;
import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.service.ComplaintService;
import comsep23.srcspro.service.DepartmentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentComplaintController {

    private final ComplaintService complaintService;
    private final DepartmentService departmentService;

    @GetMapping("/dashboard")
    public String dashboard(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model,
            HttpSession session
    ) {
        Long studentId = (Long) session.getAttribute("userId");
        if (studentId == null) return "redirect:/login";

        PageResponseDTO<ComplaintListDTO> response =
                complaintService.getStudentComplaintsPaged(studentId, page, size);

        model.addAttribute("page", response);
        return "student/dashboard";
    }

    @GetMapping("/submit-complaint")
    public String submitForm(Model model, HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("complaint", new ComplaintCreateDTO());
        model.addAttribute("departments", departmentService.getAllSummaries());
        model.addAttribute("categories", ComplaintCategory.values());

        return "student/submit-complaint";
    }

    @PostMapping("/submit-complaint")
    public String submit(
            @Valid @ModelAttribute("complaint") ComplaintCreateDTO dto,
            BindingResult result,
            @RequestParam(required = false) MultipartFile photo,
            HttpSession session
    ) {
        if (result.hasErrors()) {
            return "student/submit-complaint";
        }

        Long studentId = (Long) session.getAttribute("userId");
        if (studentId == null) return "redirect:/login";

        complaintService.createComplaint(dto, studentId, photo);
        return "redirect:/student/dashboard?page=0&size=5";
    }

    @GetMapping("/complaints/{id}")
    public String details(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {
        Long studentId = (Long) session.getAttribute("userId");
        if (studentId == null) return "redirect:/login";

        model.addAttribute(
                "complaint",
                complaintService.getStudentComplaint(id, studentId)
        );

        return "student/complaint-detail";
    }

    @PostMapping("/complaints/{id}/confirm")
    public String confirm(@PathVariable Long id, HttpSession session) {
        Long studentId = (Long) session.getAttribute("userId");
        if (studentId == null) return "redirect:/login";

        complaintService.confirmResolution(id, studentId);
        return "redirect:/student/dashboard?page=0&size=5";
    }
}
