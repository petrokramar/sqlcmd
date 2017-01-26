package ua.com.juja.sqlcmd.controller.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.com.juja.sqlcmd.controller.form.UserForm;
import ua.com.juja.sqlcmd.model.Role;
import ua.com.juja.sqlcmd.model.User;
import ua.com.juja.sqlcmd.model.UserRole;
import ua.com.juja.sqlcmd.service.LogService;
import ua.com.juja.sqlcmd.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {
    @Autowired
    private LogService logService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView users() {
        List<User> users = userService.getUsers();
        ModelAndView model = new ModelAndView("users");
        model.addObject("users", users);
        logService.saveUserAction("get users");
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        UserForm userForm = new UserForm();
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("userForm", userForm);
        logService.saveUserAction("get add user");
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String creatingUser(User user) {



        userService.saveUser(user);
        logService.saveUserAction("add user " + user.getUsername());
        return "redirect:users";
    }

    @ModelAttribute("roleNames")
    //TODO Delete hardcode
    public List getAllRoles()
    {
        List<String> roleNames = new ArrayList<>();
        roleNames.add(Role.ROLE_USER.toString());
        roleNames.add(Role.ROLE_ADMIN.toString());
        return roleNames;
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.GET)
    //TODO validate email
    public ModelAndView updateUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        User user = userService.getUser(name);
        List<String> roleNames = new ArrayList<>();
        for (UserRole role: user.getUserRoles()) {
            roleNames.add(role.getRole().toString());
        }
        UserForm userForm = new UserForm();
        userForm.setUsername(user.getUsername());
        userForm.setPassword(user.getPassword());
        userForm.setConfirmPassword(user.getPassword());
        userForm.setEmail(user.getEmail());
        userForm.setEnabled(user.isEnabled());
        userForm.setUserRoles(user.getUserRoles());
        userForm.setRoleNames(roleNames);
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("userForm", userForm);
        logService.saveUserAction("get update user " + user.getUsername());
        return model;
    }

    @RequestMapping(value = "/updateuser", method = RequestMethod.POST)
    @Transactional
    public String updatingUser(UserForm userForm){
        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setEmail(userForm.getEmail());
        user.setEnabled(userForm.isEnabled());
        Set<UserRole> roles = new HashSet<>();

        userService.saveUser(user);
        for (String roleName: userForm.getRoleNames()) {
            UserRole role = new UserRole();
            role.setUser(user);
            role.setRole(Role.valueOf(roleName));
            roles.add(role);
        }
        user.setUserRoles(roles);
//        public String updatingUser(User user){

        userService.saveUser(user);

//        logService.saveUserAction("update user " + user.getUsername());
        return "redirect:users";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        User user = userService.getUser(name);
        ModelAndView model = new ModelAndView("deleteUser");
        model.addObject("user", user);
        logService.saveUserAction("get delete user " + user.getUsername());
        return model;
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public String deletingUser(User user) {
        userService.deleteUser(user);
        logService.saveUserAction("delete user " + user.getUsername());
        return "redirect:users";
    }
}
