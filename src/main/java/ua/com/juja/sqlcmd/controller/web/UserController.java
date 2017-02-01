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

    @ModelAttribute("roleNames")
    public List getAllRoles()
    {
        List<String> roleNames = new ArrayList<>();
        for (Role role : Role.values()) {
            roleNames.add(role.toString());
        }
        return roleNames;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.GET)
    public ModelAndView createUser() {
        UserForm userForm = new UserForm();
        userForm.setActionType("add");
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("userForm", userForm);
        return model;
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String creatingUser(User user) {
        userService.saveUser(user);
        logService.saveUserAction("add user " + user.getUsername());
        return "redirect:users";
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
        userForm.setOldPassword(user.getPassword());
//        userForm.setPassword(user.getPassword());
//        userForm.setConfirmPassword(user.getPassword());
        userForm.setEmail(user.getEmail());
        userForm.setEnabled(user.isEnabled());
        userForm.setUserRoles(user.getUserRoles());
        userForm.setRoleNames(roleNames);
        userForm.setActionType("update");
        ModelAndView model = new ModelAndView("updateUser");
        model.addObject("userForm", userForm);
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
        //TODO how to delete this line???
        userService.saveUser(user);
        if ("".equals(userForm.getPassword())) {
            user.setPassword(userForm.getOldPassword());
        } else {
            user.setPassword(userForm.getPassword());
        }
        for (String roleName: userForm.getRoleNames()) {
            UserRole role = new UserRole();
            role.setUser(user);
            role.setRole(Role.valueOf(roleName));
            roles.add(role);
        }
        user.setUserRoles(roles);
        userService.saveUser(user);
        logService.saveUserAction(userForm.getActionType() + " user " + user.getUsername());
        return "redirect:users";
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.GET)
    public ModelAndView deleteUser(HttpServletRequest req) {
        String name = req.getParameter("name");
        User user = userService.getUser(name);
        ModelAndView model = new ModelAndView("deleteUser");
        model.addObject("user", user);
        return model;
    }

    @RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
    public String deletingUser(User user) {
        userService.deleteUser(user);
        logService.saveUserAction("delete user " + user.getUsername());
        return "redirect:users";
    }
}
