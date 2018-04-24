package com.test.beans;

import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;

/**
 * Created by Pavel Gavrilov
 */
@Component
public class AuthorLoggerEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        switch (text) {
            case "SOUT": setValue(new SOUTAuthorLogger());
                return;
            case "SERR" : setValue(new SERRAuthorLogger());
        }
    }
}
