package comsep23.srcspro.controller;

import comsep23.srcspro.dto.complaint.ComplaintListDTO;
import comsep23.srcspro.dto.department.PageResponseDTO;
import comsep23.srcspro.entity.Complaint;
import comsep23.srcspro.enums.ComplaintCategory;
import comsep23.srcspro.enums.ComplaintStatus;
import comsep23.srcspro.enums.Role;
import comsep23.srcspro.repositories.ComplaintRepository;
import comsep23.srcspro.service.ComplaintService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/staff")
@RequiredArgsConstructor
public class StaffComplaintController {

    private final ComplaintService complaintService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        if (!isStaff(session)) return "redirect:/login";

        model.addAttribute(
                "complaints",
                complaintService.getNewComplaintsForStaff()
        );

        return "staff/dashboard";
    }

    @GetMapping("/complaints")
    public String list(
            @RequestParam(required = false) ComplaintStatus status,
            @RequestParam(required = false) ComplaintCategory category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model,
            HttpSession session
    ) {
        if (!isStaff(session)) return "redirect:/login";

        PageResponseDTO<ComplaintListDTO> response =
                complaintService.getComplaintsForStaffPaged(
                        status, category, page, size
                );

        model.addAttribute("page", response);
        model.addAttribute("statuses", ComplaintStatus.values());
        model.addAttribute("categories", ComplaintCategory.values());

        return "staff/complaints-list";
    }

    @GetMapping("/complaints/{id}")
    public String detail(
            @PathVariable Long id,
            Model model,
            HttpSession session
    ) {
        if (!isStaff(session)) return "redirect:/login";

        model.addAttribute(
                "complaint",
                complaintService.getComplaintForStaff(id)
        );

        model.addAttribute("statuses", ComplaintStatus.values());

        return "staff/complaint-detail";
    }

    @PostMapping("/complaints/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam ComplaintStatus status,
            @RequestParam(required = false) String note,
            HttpSession session
    ) {
        Long staffId = (Long) session.getAttribute("userId");
        if (!isStaff(session)) return "redirect:/login";

        complaintService.updateStatus(id, status, staffId, note);
        return "redirect:/staff/complaints/" + id;
    }

    private boolean isStaff(HttpSession session) {
        Object role = session.getAttribute("role");
        return role != null &&
                (role.equals(Role.STAFF.name()) || role.equals(Role.ADMIN.name()));
    }
}