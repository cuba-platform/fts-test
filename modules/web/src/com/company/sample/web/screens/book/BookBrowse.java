package com.company.sample.web.screens.book;

import com.company.sample.entity.Book;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Book.browse")
@UiDescriptor("book-browse.xml")
@LookupComponent("booksTable")
@LoadDataBeforeShow
public class BookBrowse extends StandardLookup<Book> {
}