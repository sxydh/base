package fun.ehe.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import fun.ehe.bean.entity.User;
import fun.ehe.bean.request.LoginRequest;
import fun.ehe.bean.response.ResponseTemplate;
import fun.ehe.exception.UnAuthenticateException;
import fun.ehe.service.UserService;
import fun.ehe.utils.JWTUtils;

@RestController
public class TestController {

    static final Logger LOGGER = LogManager.getLogger(TestController.class);

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseTemplate login(@RequestBody LoginRequest rq_l) {
        User user = userService.getUser(rq_l.getUsername());
        if (user.getPassword().equals(rq_l.getPassword())) {
            return new ResponseTemplate(200, "Login success", JWTUtils.sign(rq_l.getUsername(), rq_l.getPassword()));
        } else {
            throw new UnAuthenticateException();
        }
    }

    @GetMapping("/autheInfo/get")
    public ResponseTemplate autheInfo() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseTemplate(200, "You have been authenticated", null);
        } else {
            return new ResponseTemplate(200, "You're not authenticated", null);
        }
    }

    @GetMapping("/protected_resource/get")
    @RequiresAuthentication
    public ResponseTemplate protectedResource() {
        return new ResponseTemplate(200, "You have been authenticated, and you can take the next step", null);
    }

    @GetMapping("/authoInfo/get")
    @RequiresRoles("admin")
    public ResponseTemplate authoInfo() {
        return new ResponseTemplate(200, "You are admin role", null);
    }

    @GetMapping("/permissionInfo/get")
    @RequiresPermissions(logical = Logical.AND, value = { "view", "edit" })
    public ResponseTemplate permissionInfo() {
        return new ResponseTemplate(200, "You have view and edit permission", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseTemplate handle401() {
        return new ResponseTemplate(401, "401", null);
    }
}
