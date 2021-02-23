package com.Ryan.Blog.service.Impl;

import com.Ryan.Blog.mapper.BlogMapper;
import com.Ryan.Blog.mapper.TagMapper;
import com.Ryan.Blog.mapper.TypeMapper;
import com.Ryan.Blog.mapper.UserMapper;
import com.Ryan.Blog.pojo.Blog;
import com.Ryan.Blog.pojo.BlogAndTag;
import com.Ryan.Blog.pojo.Tag;
import com.Ryan.Blog.pojo.Type;
import com.Ryan.Blog.service.BlogService;
import com.Ryan.Blog.service.TagService;
import com.Ryan.Blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private TagMapper tagMapper;
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TagService tagService;


    @Override
    public List<Blog> findAll() {
        List<Blog> blogs = blogMapper.findAll();
        return blogs;
    }

    @Override
    public List<Blog> findByCondition(Blog blog) {
        List<Blog> byCondition = blogMapper.findByCondition(blog);
        return byCondition;
    }

    @Override
    public Blog findByTitle(String Title) {
        Blog blog = new Blog();
        blog.setTitle(Title);
        Blog blog1 = blogMapper.selectOne(blog);
        return blog1;
    }

    @Override

    public int saveBlog(Blog blog) {
        int i = blogMapper.saveBlog(blog);
        Long blogId = blog.getId();
        String tagIds = blog.getTagIds();
        List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (Integer tagId : tagIdList) {
            blogMapper.saveBlogAndTag(new BlogAndTag(tagId,blogId));
        }
        return i;
    }

    @Override
    public Blog findOneById(Long id) {
        Blog blog = blogMapper.findOneById(id);
        return blog;
    }

    @Override

    public int updateBlog(Blog blog) {
        int i = blogMapper.updateBlog(blog);
        Long blogId = blog.getId();
        blogMapper.removeBlogAndTag(new BlogAndTag(blogId));

        String tagIds = blog.getTagIds();
        List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        for (Integer tagId : tagIdList) {
            blogMapper.saveBlogAndTag(new BlogAndTag(tagId,blogId));
        }
        return i;
    }

    @Override

    public int deleteById(Long id) {
        Blog blog = new Blog();
        blog.setId(id);
        int i = blogMapper.delete(blog);
        return i;
    }

    @Override
    public List<Blog> findByTypeId(Integer id) {
        List<Blog> blogs = blogMapper.findByTypeId(id);
        return blogs;
    }

    @Override
    public List<Blog> findLikeTitle(String Title) {
        List<Blog> blogs = blogMapper.findLikeTitle("%"+Title+"%");
        blogs.removeIf(blog -> blog.getPublished() != 1);

        for (Blog blog : blogs) {
            String typeId = blog.getTypeId();
            Type type = new Type();
            type.setId(Integer.valueOf(typeId));
            Type type1 = typeMapper.selectOne(type);
            blog.setType(type1);
        }

        for (Blog blog : blogs) {
            ArrayList<Tag> tags = new ArrayList<>();
            String tagIds = blog.getTagIds();
            List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            for (Integer id : tagIdList) {
                Tag tag = new Tag();
                tag.setId(id);
                Tag tag1 = tagMapper.selectOne(tag);
                tags.add(tag1);
            }
            blog.setTags(tags);
        }

        return blogs;
    }

    @Override
    public List<Blog> showBlogs() {
        List<Blog> blogs = blogMapper.showBlogs();
        return blogs;
    }



    @Override

    public Blog showBlog(Long id){
        Blog blog = blogMapper.findOneById(id);
        blogMapper.updateViews(blog.getId(), (long) (blog.getViews() + 1));
        String tagIds = blog.getTagIds();
        List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        ArrayList<Tag> tags = new ArrayList<>();
        for (Integer tagId : tagIdList) {
            Tag tag = new Tag();
            tag.setId(tagId);
            Tag tag1 = tagMapper.selectOne(tag);
            tags.add(tag1);
        }
        blog.setTags(tags);
        return blog;
    }

    @Override
    public List<Blog> findBlogAndUserByTypeId(Integer id) {
        List<Blog> blogs = blogMapper.findBlogAndUserByTypeId(id);
        return blogs;
    }

    @Override
    public List<Blog> BlogAndTypeAndTag() {
        List<Blog> blogs = showBlogs();
        for (Blog blog : blogs) {
            List<Tag> tags = new ArrayList<>();
            String tagIds = blog.getTagIds();
            List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
            for (Integer tagId : tagIdList) {
                Tag tag = tagService.findById(tagId);
                 tags.add(tag);
            }
            blog.setTags(tags);
        }
        return blogs;
    }

    @Override
    public List<Blog> BlogAndTypeAndTagByTagId(Integer id) {
        Tag tag = blogMapper.blogAndTypeAndTagAndUser(id);
        if (tag != null){
            List<Blog> blogs = tag.getBlogs();
            for (Blog blog : blogs) {
                List<Tag> tags = new ArrayList<>();
                String tagIds = blog.getTagIds();
                List<Integer> tagIdList = Arrays.stream(tagIds.split(",")).map(Integer::parseInt).collect(Collectors.toList());
                for (Integer tagId : tagIdList) {
                    Tag ById = tagService.findById(tagId);
                    tags.add(ById);
                }
                blog.setTags(tags);
            }
            return blogs;
        }
        return new ArrayList<>();

    }

    @Override
    public List<Blog> recommendBlog() {
        List<Blog> blogs = blogMapper.recommendBlog();
        return blogs;
    }
}
