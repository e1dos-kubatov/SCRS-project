package comsep23.srcspro.controller;

import comsep23.srcspro.entity.Complaint;
import comsep23.srcspro.enums.ComplaintStatus;
import comsep23.srcspro.repositories.ComplaintRepository;
import comsep23.srcspro.service.HomeService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {

        model.addAttribute(
                "recentResolved",
                homeService.getRecentResolvedComplaints()
        );

        model.addAttribute("currentUserId", session.getAttribute("userId"));
        model.addAttribute("currentUserRole", session.getAttribute("role"));

        return "home";
    }
}
