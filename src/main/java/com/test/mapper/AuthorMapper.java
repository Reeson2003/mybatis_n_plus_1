package com.test.mapper;

import com.test.model.Author;
import com.test.model.Post;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * Created by Pavel Gavrilov
 */
public interface AuthorMapper {
    @Select("SELECT * FROM public.author where author_name = #{name}")
    @Results({
            @Result(id = true, property = "id", column = "author_id"),
            @Result(property = "name", column = "author_name"),
            @Result(property = "posts", column = "author_id", javaType = List.class, many = @Many(select = "getPostsByAuthorIdPrepared"))
    })
    List<Author> getAuthorByNamePrepared(String name);

    @Select("SELECT * FROM public.author_post LEFT JOIN public.post ON (author_post.post_id = post.post_id) WHERE author_id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "post_id"),
            @Result(property = "text", column = "post_text")
    })
    List<Post> getPostsByAuthorIdPrepared(long id);

    @Select("SELECT * FROM public.author where author_name = '${name}'")
    @Results({
            @Result(id = true, property = "id", column = "author_id"),
            @Result(property = "name", column = "author_name"),
            @Result(property = "posts", column = "author_id", javaType = List.class, many = @Many(select = "getPostsByAuthorId"))
    })
    @Options(statementType = StatementType.STATEMENT)
    List<Author> getAuthorByName(@Param("name") String name);

    @Select("SELECT * FROM public.author_post LEFT JOIN public.post ON (author_post.post_id = post.post_id) WHERE author_id = ${_id}")
    @Results({
            @Result(id = true, property = "id", column = "post_id"),
            @Result(property = "text", column = "post_text")
    })
    @Options(statementType = StatementType.STATEMENT)
    List<Post> getPostsByAuthorId(@Param("_id") long id);
}
