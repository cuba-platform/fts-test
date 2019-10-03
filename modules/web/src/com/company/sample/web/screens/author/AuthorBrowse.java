package com.company.sample.web.screens.author;

import com.company.sample.entity.Author;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Author.browse")
@UiDescriptor("author-browse.xml")
@LookupComponent("authorsTable")
@LoadDataBeforeShow
public class AuthorBrowse extends StandardLookup<Author> {
}