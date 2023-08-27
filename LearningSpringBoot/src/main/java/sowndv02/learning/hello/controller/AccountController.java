package sowndv02.learning.hello.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import sowndv02.learning.hello.services.CookieService;
import sowndv02.learning.hello.services.ParamService;
import sowndv02.learning.hello.services.SessionService;

@Controller
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    CookieService cookieService;

    @Autowired
    ParamService paramService;

    @Autowired
    SessionService sessionService;

    @GetMapping("/login")
    public String login1() {
        return "accounts/login";
    }

    @PostMapping("/login")
    public String login2(Model model) {
        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");

        boolean remember = paramService.getBoolean("remember", false);

        if (username.equals("sown02") && password.equals("sown02")) {
            sessionService.set("username", username);

            if (remember) {
                cookieService.add("username", username, 1);
                cookieService.add("password", password, 1);
            } else {
                cookieService.remove("username");
                cookieService.remove("password");
            }
            model.addAttribute("message", "Login successfully");
        } else {
            model.addAttribute("message", "Invalid username or password");
        }

        return "accounts/login";
    }

    @GetMapping("/register")
    public String register1() {
        return "accounts/register";
    }

    @PostMapping("/register")
    public String register2(@ModelAttribute("img") MultipartFile img, Model model) {

        String username = paramService.getString("username", "");
        String password = paramService.getString("password", "");

        try {
            paramService.saveFile(img, "/images"); // Khai báo chi tiết đường dẫn
            model.addAttribute("message", "save!");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            model.addAttribute("message", "Error: " + e.getMessage());
        }

        return "accounts/register";
    }
}
