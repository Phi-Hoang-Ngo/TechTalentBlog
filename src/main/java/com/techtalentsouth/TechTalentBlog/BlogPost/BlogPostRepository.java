package com.techtalentsouth.TechTalentBlog.BlogPost;

import org.springframework.data.repository.CrudRepository;
import java.math.BigInteger;

public interface BlogPostRepository extends CrudRepository<BlogPost, BigInteger>{
}

