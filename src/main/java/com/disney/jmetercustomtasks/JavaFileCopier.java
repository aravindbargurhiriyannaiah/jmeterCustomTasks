package com.disney.jmetercustomtasks;

import com.disney.jmetercustomtasks.util.JMeterCustomTaskUtil;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.Map;

public class JavaFileCopier implements JavaSamplerClient {

    public static final String ABSOLUTE_PATH_OF_SOURCE_FILE = "Absolute path of source file";
    public static final String ABSOLUTE_PATH_OF_DESTINATION_FILE = "Absolute path of destination file";

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        Map<String, String> map = JMeterCustomTaskUtil.accessAllUserDefinedValues(javaSamplerContext);
        String sourceFile = map.get(ABSOLUTE_PATH_OF_SOURCE_FILE).trim();
        String destinationFile = map.get(ABSOLUTE_PATH_OF_DESTINATION_FILE).trim();
        boolean isCopied = JMeterCustomTaskUtil.copyFile(sourceFile, destinationFile);
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        String msg;
        if (isCopied) {
            msg = "Copied [" + sourceFile + "] to  [" + destinationFile + "]";
        } else {
            msg = "Could not copy [" + sourceFile + "] to [" + destinationFile + "]";
        }
        sr.setResponseMessage(msg);
        sr.sampleEnd();
        sr.setSuccessful(isCopied);
        return sr;
    }


    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments args = new Arguments();
        args.addArgument(ABSOLUTE_PATH_OF_SOURCE_FILE, "");
        args.addArgument(ABSOLUTE_PATH_OF_DESTINATION_FILE, "");
        return args;
    }
}
