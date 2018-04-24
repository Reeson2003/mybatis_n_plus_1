package com.test.beans;

import com.test.model.Author;
import org.springframework.stereotype.Component;

/**
 * Created by Pavel Gavrilov
 */
@SERR
@Component
public class SERRAuthorLogger implements AuthorLogger {
    @Override
    public void log(Author author) {
        System.err.println(author);
    }
}
