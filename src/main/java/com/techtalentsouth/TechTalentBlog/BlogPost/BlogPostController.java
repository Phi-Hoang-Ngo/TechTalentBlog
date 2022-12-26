package com.techtalentsouth.TechTalentBlog.BlogPost;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BlogPostController {
	
    @Autowired
    private BlogPostRepository blogPostRepository;
    private static List<BlogPost> posts = new ArrayList<>();

    @GetMapping(value = "/")
    public String index(BlogPost blogPost, Model model) {
		posts.removeAll(posts);
		for (BlogPost post : blogPostRepository.findAll()) {
			posts.add(post);
		}
    	model.addAttribute("posts", posts);
//		model.addAttribute("id", blogPost.getId());
    	return "blogposts/index";
    }

    private BlogPost blogPost;
    @PostMapping(value = "/blogposts")
    public String addNewBlogPost(BlogPost blogPost, Model model) {
        blogPostRepository.save(blogPost);
        model.addAttribute("title", blogPost.getTitle());
        model.addAttribute("author", blogPost.getAuthor());
        model.addAttribute("blogEntry", blogPost.getBlogEntry());
        model.addAttribute("id", blogPost.getId());
        return "blogposts/result";
    }

    @GetMapping(value = "/blogposts/new")
    public String newBlog (BlogPost blogPost) {
        return "blogposts/new";
    }

    @DeleteMapping("/blogposts/{id}")
    public String deletePostWithId(@PathVariable BigInteger id, BlogPost blogPost) {

        blogPostRepository.deleteById(id);
        
        return "blogposts/index";

    }

    @PostMapping("/blogposts/update/{id}")
    public String updateExistingPost(@PathVariable BigInteger id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);

            BlogPost actualPost = post.get();
            actualPost.setTitle(blogPost.getTitle());
            actualPost.setAuthor(blogPost.getAuthor());
            actualPost.setBlogEntry(blogPost.getBlogEntry());
            blogPostRepository.save(actualPost);
            model.addAttribute("blogPost", actualPost);
            model.addAttribute("title", blogPost.getTitle());
            model.addAttribute("author", blogPost.getAuthor());
            model.addAttribute("blogEntry", blogPost.getBlogEntry());
            model.addAttribute("id", blogPost.getId());
            return "blogposts/result1";}
 
	@GetMapping("/blogposts/{id}")
    public String editPostWithId(@PathVariable BigInteger id, BlogPost blogPost, Model model) {
        Optional<BlogPost> post = blogPostRepository.findById(id);
        if (post.isPresent()) {
            BlogPost actualPost = post.get();
            model.addAttribute("blogPost", actualPost);
        }
        return "blogposts/edit";
    }		

	@GetMapping("/blogposts/delete/{id}")
    public String deletePostById(@PathVariable BigInteger id, BlogPost blogPost) {
        blogPostRepository.deleteById(id);
        return "blogposts/delete";
    }
    
}
	
