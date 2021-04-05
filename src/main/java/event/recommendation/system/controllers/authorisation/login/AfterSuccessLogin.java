package event.recommendation.system.controllers.authorisation.login;

import event.recommendation.system.services.authorisation.AfterSuccessLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
public class AfterSuccessLogin {

    private final AfterSuccessLoginService afterSuccessLoginService;

    @GetMapping("/afterSuccessLogin")
    public String afterSuccessLogin() {
        return afterSuccessLoginService.redirectIfUserFirstLogin();
    }

}
