package com.funkyganesha.util;

import org.apache.commons.io.FileUtils;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class JMeterCustomTaskUtil {
    /**
     * This method will give you all the available parameters from the javaSamplerContext.
     *
     * @param javaSamplerContext
     * @return
     */
    public static Map<String, String> accessAllUserDefinedValues(JavaSamplerContext javaSamplerContext) {
        Map<String, String> userDefinedVariablesMap = new HashMap<String, String>();
        Iterator<String> parameterNamesIterator = javaSamplerContext.getParameterNamesIterator();
        while (parameterNamesIterator.hasNext()) {
            String key = parameterNamesIterator.next();
            String value = javaSamplerContext.getParameter(key);
            userDefinedVariablesMap.put(key, value);
        }
        return userDefinedVariablesMap;
    }

    public static boolean copyFile(String sourceFile, String destinationFile) {
        boolean isCopied;
        try {
            FileUtils.copyFile(new File(sourceFile), new File(destinationFile));
            isCopied = true;
        } catch (IOException ignore) {
            isCopied = false;
        }
        return isCopied;
    }

    public static boolean doesFileExist(String fileName) {
        boolean doesFileExist = false;
        File file = new File(fileName);
        if (file.exists()) {
            doesFileExist = true;
        }
        return doesFileExist;
    }

    public static int numberOfFilesWithThisExtensionInThisDirectory(File directory, String fileExtension) {
        int result = 0;
        if (directory.exists() && directory.canRead()) {
            File[] files = getFilesByExtension(directory, fileExtension);
            result = files.length;
        }
        return result;
    }

    public static File[] getFilesByExtension(File directory, final String fileExtension) {
        return directory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.endsWith(fileExtension);
            }
        });
    }

    public static boolean deleteFiles(String directoryName, String fileExtension) {
        boolean result = false;
        File directory = new File(directoryName);
        if (directory.exists()) {
            File[] files = getFilesByExtension(directory, fileExtension);
            for (File file : files) {
                result = FileUtils.deleteQuietly(file);
                if (!result) {
                    break;
                }
            }
        }
        return result;
    }
}
