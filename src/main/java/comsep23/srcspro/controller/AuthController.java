package comsep23.srcspro.controller;

import comsep23.srcspro.dto.user_dto.AuthUserDTO;
import comsep23.srcspro.dto.user_dto.LoginDTO;
import comsep23.srcspro.dto.user_dto.RegisterDTO;
import comsep23.srcspro.entity.User;
import comsep23.srcspro.enums.Role;
import comsep23.srcspro.repositories.UserRepository;
import comsep23.srcspro.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // LOGIN PAGE
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("login", new LoginDTO());
        return "login";
    }

    // LOGIN
    @PostMapping("/auth/login")
    public String login(
            @ModelAttribute("login") LoginDTO dto,
            HttpSession session,
            Model model
    ) {
        Optional<AuthUserDTO> user = authService.login(dto);

        if (user.isEmpty()) {
            model.addAttribute("loginError", "Invalid email or password");
            return "login";
        }

        session.setAttribute("userId", user.get().id());
        session.setAttribute("role", user.get().role().name());

        return user.get().isStaff()
                ? "redirect:/staff/dashboard"
                : "redirect:/student/dashboard";
    }

    // REGISTER PAGE
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("register", new RegisterDTO());
        return "register";
    }

    // REGISTER
    @PostMapping("/register")
    public String register(
            @ModelAttribute("register") RegisterDTO dto,
            Model model
    ) {
        try {
            authService.register(dto);
            return "redirect:/login";
        } catch (IllegalStateException e) {
            model.addAttribute("registerError", e.getMessage());
            return "register";
        }
    }

    // LOGOUT
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
