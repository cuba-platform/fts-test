package com.company.sample.jmx;

import com.company.sample.entity.Author;
import com.company.sample.entity.Book;
import com.company.sample.entity.Contract;
import com.company.sample.entity.ContractAttachment;
import com.haulmont.cuba.core.app.FileStorageService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.app.Authenticated;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("sample_TestDataGenerator")
public class TestDataGenerator implements TestDataGeneratorMBean {

    @Inject
    private Metadata metadata;

    @Inject
    private DataManager dataManager;

    @Inject
    private FileStorageService fileStorageService;

    @Inject
    private Logger log;

    @Inject
    private TimeSource timeSource;

    private List<String> authorNames = Arrays.asList(
            "Leo Tolstoy",
            "Jane Austen",
            "Oscar Wilde",
            "Mark Twain",
            "Arthur Conan Doyle"
    );

    private List<String> ebookFileNames = Arrays.asList(
            "The Adventures of Sherlock Holmes.txt",
            "The War of the Worlds.txt",
            "Alice's Adventures in Wonderland.txt"
    );

    private List<String> attachmentFileNames = Arrays.asList(
            "Drakula.txt",
            "Iliad.pdf",
            "Pride and Prejudice.txt"
    );


    @Override
    @Authenticated
    public String generateAuthors() {
        List<Author> authors = authorNames.stream()
                .map(name -> {
                    Author author = metadata.create(Author.class);
                    author.setName(name);
                    return author;
                })
                .collect(Collectors.toList());
        dataManager.commit(new CommitContext(authors));
        return "Authors created successfully";
    }

    @Override
    @Authenticated
    public String generateBooks(int numberOfBooks) {
        List<Author> authors = loadAuthors();
        if (authors.isEmpty()) {
            generateAuthors();
            authors = loadAuthors();
        }
        List<FileInfo> bookFiles = null;
        try {
            bookFiles = loadBookFiles();
        } catch (IOException e) {
            log.error("Cannot load book files", e);
            return "Cannot load book files";
        }

        for (int i = 0; i < numberOfBooks; i++) {
            Book book = metadata.create(Book.class);
            String randomPart = RandomStringUtils.randomAlphabetic(5);
            book.setTitle("Book-" + randomPart);
            book.setDescription("Description-" + randomPart);
            Author randomAuthor = authors.get(RandomUtils.nextInt(0, authors.size()));
            book.setAuthor(randomAuthor);

            FileInfo randomBookFileInfo = bookFiles.get(RandomUtils.nextInt(0, bookFiles.size()));
            try {
                FileDescriptor fd;
                fd = createFileDescriptor(randomBookFileInfo.getName(), randomBookFileInfo.getBytes());
                book.setEbook(fd);
                dataManager.commit(book, fd);
            } catch (FileStorageException e) {
                log.error("Cannot create file descriptor", e);
            }
        }
        return numberOfBooks + " books created successfully";
    }

    @Override
    @Authenticated
    public String generateContracts(int numberOfContracts, int numberOfAttachments) {
        List<FileInfo> attachmentFiles = null;
        try {
            attachmentFiles = loadAttachmentFiles();
        } catch (IOException e) {
            log.error("Cannot load attachment files", e);
            return "Cannot load attachment files";
        }

        for (int i = 0; i < numberOfContracts; i++) {
            List<Entity> toCommit = new ArrayList<>();
            Contract contract = metadata.create(Contract.class);
            String randomPart = RandomStringUtils.randomAlphabetic(5);
            contract.setNumber("Contract " + randomPart);

            toCommit.add(contract);
            for (int j = 0; j < numberOfAttachments; j++) {
                FileInfo randomAttachmentFileInfo = attachmentFiles.get(RandomUtils.nextInt(0, attachmentFiles.size()));
                try {
                    FileDescriptor fd;
                    fd = createFileDescriptor(randomAttachmentFileInfo.getName(), randomAttachmentFileInfo.getBytes());
                    ContractAttachment contractAttachment = metadata.create(ContractAttachment.class);
                    contractAttachment.setFile(fd);
                    contractAttachment.setContract(contract);
                    contractAttachment.setComment("Comment " + RandomStringUtils.randomAlphabetic(5));
                    toCommit.add(contractAttachment);
                    toCommit.add(fd);
                } catch (FileStorageException e) {
                    log.error("Cannot create file descriptor", e);
                }
            }

            dataManager.commit(new CommitContext(toCommit));
        }

        return numberOfContracts + " contracts created successfully";
    }

    private FileDescriptor createFileDescriptor(String fileName, byte[] bytes) throws FileStorageException {
        FileDescriptor fd = metadata.create(FileDescriptor.class);
        fd.setName(fileName);
        fd.setExtension(FilenameUtils.getExtension(fileName));
        fd.setSize((long) bytes.length);
        fd.setCreateDate(timeSource.currentTimestamp());
        fileStorageService.saveFile(fd, bytes);
        return fd;
    }

    private List<Author> loadAuthors() {
        return dataManager.load(Author.class)
                .query("select a from sample_Author a")
                .list();
    }

    private List<FileInfo> loadBookFiles() throws IOException {
        List<FileInfo> result = new ArrayList<>();
        for (String fileName : ebookFileNames) {
            InputStream resourceAsStream = getClass().getResourceAsStream("data/books/" + fileName);
            byte[] bytes = IOUtils.toByteArray(resourceAsStream);
            result.add(new FileInfo(fileName, bytes));
        }
        return result;
    }

    private List<FileInfo> loadAttachmentFiles() throws IOException {
        List<FileInfo> result = new ArrayList<>();
        for (String fileName : attachmentFileNames) {
            InputStream resourceAsStream = getClass().getResourceAsStream("data/attachments/" + fileName);
            byte[] bytes = IOUtils.toByteArray(resourceAsStream);
            result.add(new FileInfo(fileName, bytes));
        }
        return result;
    }

    private class FileInfo {
        private String name;
        private byte[] bytes;

        public FileInfo(String name, byte[] bytes) {
            this.name = name;
            this.bytes = bytes;
        }

        public String getName() {
            return name;
        }

        public byte[] getBytes() {
            return bytes;
        }
    }


}