package com.funkyganesha;

import com.funkyganesha.util.JMeterCustomTaskUtil;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.Map;

public class JavaFileDelete implements JavaSamplerClient {

    public static final String ABSOLUTE_PATH_OF_THE_DIRECTORY_WHERE_FILES_SHOULD_BE_DELETED = "Absolute path of the directory where files should be " +
            "deleted from";
    public static final String EXTENSION_OF_FILES_THAT_SHOULD_BE_DELETED_EG_JSON_TXT = "Extension of files that should be deleted. Eg, .json, .txt";

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        Map<String,String> map = JMeterCustomTaskUtil.accessAllUserDefinedValues(javaSamplerContext);
        String directory = map.get(ABSOLUTE_PATH_OF_THE_DIRECTORY_WHERE_FILES_SHOULD_BE_DELETED).trim();
        String fileExtension = map.get(EXTENSION_OF_FILES_THAT_SHOULD_BE_DELETED_EG_JSON_TXT).trim();
        boolean isDeleted = JMeterCustomTaskUtil.deleteFiles(directory, fileExtension);
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        String msg;
        if (isDeleted) {
            msg = "Deleted all files with extension [" + fileExtension + "] from  [" + directory + "]";
        } else {
            msg = "Could not delete all files with with extension [" + fileExtension + "] from  [" + directory + "]";
        }
        sr.setResponseMessage(msg);
        sr.sampleEnd();
        sr.setSuccessful(isDeleted);
        return sr;
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(ABSOLUTE_PATH_OF_THE_DIRECTORY_WHERE_FILES_SHOULD_BE_DELETED, "");
        arguments.addArgument(EXTENSION_OF_FILES_THAT_SHOULD_BE_DELETED_EG_JSON_TXT, "");
        return arguments;
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {

    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {

    }
}
