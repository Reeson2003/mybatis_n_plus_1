package com.test.mapper;

import com.test.model.Author;
import com.test.model.Post;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public interface AuthorMapper {
    String SELECT_POSTS = "SELECT * FROM public.author LEFT JOIN author_post ON (author.author_id = author_post.author_id) JOIN post ON (author_post.post_id = post.post_id) WHERE author_name = #{name}";

    @Select("SELECT * FROM public.author where author_name = #{name}")
//    @Select(SELECT_POSTS)
    @Results({
            @Result(id = true, property = "id", column = "author_id"),
            @Result(property = "name", column = "author_name"),
            @Result(property = "posts", column = "author_id", javaType = List.class, many = @Many(select = "getPostsByAuthorName"))
    })
    List<Author> getAuthorByName(String name);

    @Select("SELECT * FROM public.post WHERE post_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "post_id"),
            @Result(property = "text", column = "post_text")
    })
    Post getPostByAuthorId(long id);

    @Select("SELECT * FROM public.author_post JOIN public.post ON (author_post.post_id = post.post_id) WHERE author_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "post_id"),
            @Result(property = "text", column = "post_text")
    })
    List<Post> getPostsByAuthorName(long id);
}
