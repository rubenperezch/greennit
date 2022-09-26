package com.greenfoxacademy.greennit.Controllers;

import com.greenfoxacademy.greennit.Models.Vote;
import com.greenfoxacademy.greennit.Repositories.VoteRepository;
import com.greenfoxacademy.greennit.Services.PostService;
import com.greenfoxacademy.greennit.Services.UserService;
import com.greenfoxacademy.greennit.Services.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    UserService userService;

    PostService postService;

    VoteService voteService;

    @Autowired
    public HomeController(UserService userService, PostService postService, VoteService voteService) {
        this.userService = userService;
        this.postService = postService;
        this.voteService = voteService;
    }

    @GetMapping("/")
    public String renderHomePage(Model model) {
        model.addAttribute("posts",postService.getAllPostsPagingSorted(0,5,"score"));
        return "index-no-user";
    }

    @GetMapping("/{id}/submitNewPost")
    public String renderSubmitNewPost(Model model, @PathVariable Long id) {
        model.addAttribute("user",userService.findFirstById(id));
        return "submit-new-post";
    }

    @PostMapping("/{id}/submitNewPost")
    public String returnSubmitNewPost(@PathVariable Long id, @RequestParam String title, @RequestParam String url) {
        postService.createPost(title, url, userService.findFirstById(id));
        return "redirect:/"+id+"/submit-new-post-success";
    }

    @GetMapping("/{id}/submit-new-post-success")
    public String renderSubmitNewPostSuccess(Model model, @PathVariable Long id) {
        model.addAttribute("user", userService.findFirstById(id));
        return "submit-new-post";
    }

    @GetMapping("/{userid}/{postid}/plusScore")
    public String returnPlusScore(@PathVariable Long userid, @PathVariable Long postid) {
        Vote vote = new Vote(true,userService.findFirstById(userid), postService.findPostById(postid));
        if(voteService.existVote(vote)) {
            return "redirect:/" + userid + "/user";
        } else {
            postService.plusScorePost(postid);
            voteService.saveVote(vote);
            return "redirect:/" + userid + "/user";
        }
    }

    @GetMapping("/{userid}/{postid}/minusScore")
    public String returnMinusScore(@PathVariable Long userid, @PathVariable Long postid) {
        Vote vote = new Vote(false,userService.findFirstById(userid),postService.findPostById(postid));
        if(voteService.existVote(vote)) {
            return "redirect:/" + userid + "/user";
        } else {
            postService.minusScorePost(postid);
            voteService.saveVote(vote);
            return "redirect:/" + userid + "/user";
        }
    }

    @GetMapping("/register")
    public String renderRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String returnRegister(@RequestParam String username, @RequestParam String password) {
        if(userService.existsUserByUsername(username)) {
            return "register-failed";
        } else {
            userService.registerUser(username, password);
            return "register-success";
        }
    }

    @GetMapping("/login")
    public String renderLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String returnLogin(@RequestParam String username, @RequestParam String password) {
        if (userService.loginVerification(username, password)) {
            return "redirect:/" + userService.findIdByUsername(username) + "/user";
        } else {
            return "login-failed";
        }
    }

    @GetMapping("/{id}/user")
    public String renderUserIndex(Model model, @PathVariable Long id, @RequestParam (defaultValue = "0") Integer pageNo, @RequestParam (defaultValue = "5") Integer pageSize) {
        model.addAttribute("user",userService.findFirstById(id));
        model.addAttribute("count",postService.countAllPosts());
        model.addAttribute("posts",postService.getAllPostsPagingSorted(pageNo,pageSize,"score"));
        return "index-user";
    }

    @PostMapping("/{id}/user")
    public String returnUserIndex(Model model, @PathVariable Long id, @RequestParam (defaultValue = "1") Integer pageNo, @RequestParam (defaultValue = "5") Integer pageSize) {
        model.addAttribute("user", userService.findFirstById(id));
        model.addAttribute("count",postService.countAllPosts());
        model.addAttribute("posts", postService.getAllPostsPagingSorted(pageNo-1, pageSize, "score"));
        return "index-user";
    }
}
