package com.company.sample.web.screens.author;

import com.company.sample.entity.Author;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Author.edit")
@UiDescriptor("author-edit.xml")
@EditedEntityContainer("authorDc")
@LoadDataBeforeShow
public class AuthorEdit extends StandardEditor<Author> {
}