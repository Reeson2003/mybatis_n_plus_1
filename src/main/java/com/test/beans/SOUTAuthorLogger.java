package com.test.beans;

import com.test.model.Author;
import org.springframework.stereotype.Component;

/**
 * Created by Pavel Gavrilov
 */
@Component
@SOUT
public class SOUTAuthorLogger implements AuthorLogger {
    @Override
    public void log(Author author) {
        System.out.println("author = " + author);
    }
}
