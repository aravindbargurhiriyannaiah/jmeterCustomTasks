package com.disney.jmetercustomtasks;

import com.disney.jmetercustomtasks.util.JMeterCustomTaskUtil;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.File;
import java.util.Map;

public class JavaFileFinder implements JavaSamplerClient {

    public static final String ABSOLUTE_PATH_OF_THE_DIRECTORY_TO_FIND_THE_A_FILE = "Absolute path of the directory to find the a file";
    public static final String FILE_EXTENSION_EG_JSON_TXT = "File extension. Eg. .json, .txt";

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        Map<String, String> stringMap = JMeterCustomTaskUtil.accessAllUserDefinedValues(javaSamplerContext);
        String directoryName = stringMap.get(ABSOLUTE_PATH_OF_THE_DIRECTORY_TO_FIND_THE_A_FILE).trim();
        final String fileExtension = stringMap.get(FILE_EXTENSION_EG_JSON_TXT).trim();
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        File file = new File(directoryName);
        int numberOfFiles = JMeterCustomTaskUtil.numberOfFilesWithThisExtensionInThisDirectory(file, fileExtension);
        String msg = "[" + directoryName + "] has [" + numberOfFiles + "] files with extension [" + fileExtension + "].";
        sr.setResponseMessage(msg);
        sr.sampleEnd();
        sr.setSuccessful(numberOfFiles == 1);
        return sr;
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(ABSOLUTE_PATH_OF_THE_DIRECTORY_TO_FIND_THE_A_FILE, "");
        arguments.addArgument(FILE_EXTENSION_EG_JSON_TXT, "");
        return arguments;
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {

    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }
}
