package com.disney.jmetercustomtasks.util;


import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class JMeterCustomTaskUtilTest {
    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();
    private String validExtension = ".json";
    private String invalidExtension = ".dat";

    @Test
    public void testDeleteFile() throws IOException {
        temporaryFolder.newFile("test1.json");
        temporaryFolder.newFile("test2.json");
        temporaryFolder.newFile("test3.dat");
        String absolutePath = temporaryFolder.getRoot().getAbsolutePath();
        boolean isDeleted = JMeterCustomTaskUtil.deleteFiles(absolutePath, validExtension);
        assertTrue("Files should have been deleted", isDeleted);
        File[] filesByExtension = JMeterCustomTaskUtil.getFilesByExtension(temporaryFolder.getRoot().getAbsoluteFile(), validExtension);
        assertEquals("There should not be any files.", 0, filesByExtension.length);
        File[] files = temporaryFolder.getRoot().listFiles();
        assertEquals("There should be one file.", 1, files.length);
    }

    @Test
    public void testDeleteFile_UnavailableFiles() {
        boolean result = JMeterCustomTaskUtil.deleteFiles(temporaryFolder.getRoot().getAbsolutePath(), ".invalid");
        assertFalse("There is nothing to delete. It should be false", result);
    }

    @Test
    public void testNumberOfFiles() throws Exception {
        temporaryFolder.newFile("test.json");
        temporaryFolder.newFile("test.dat");
        int numberOfFiles = JMeterCustomTaskUtil.numberOfFilesWithThisExtensionInThisDirectory(temporaryFolder.getRoot().getAbsoluteFile(), validExtension);
        assertEquals("There is one file.", 1, numberOfFiles);
    }

    @Test
    public void testNumberOfFiles_thereAreMultipleFiles() throws Exception {
        temporaryFolder.newFile("test1.json");
        temporaryFolder.newFile("test2.json");
        temporaryFolder.newFile("test.dat");
        int numberOfFiles = JMeterCustomTaskUtil.numberOfFilesWithThisExtensionInThisDirectory(temporaryFolder.getRoot().getAbsoluteFile(), validExtension);
        assertEquals("There are two files.", 2, numberOfFiles);
    }

    @Test
    public void testNumberOfFiles_InvalidExtension() throws Exception {
        temporaryFolder.newFile("test1.json");
        int numberOfFiles = JMeterCustomTaskUtil.numberOfFilesWithThisExtensionInThisDirectory(temporaryFolder.getRoot().getAbsoluteFile(), invalidExtension);
        assertEquals("There are no files of this extension.", 0, numberOfFiles);
    }
}
