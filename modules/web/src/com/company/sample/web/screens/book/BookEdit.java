package com.company.sample.web.screens.book;

import com.company.sample.entity.Book;
import com.haulmont.cuba.gui.screen.*;

@UiController("sample_Book.edit")
@UiDescriptor("book-edit.xml")
@EditedEntityContainer("bookDc")
@LoadDataBeforeShow
public class BookEdit extends StandardEditor<Book> {
}