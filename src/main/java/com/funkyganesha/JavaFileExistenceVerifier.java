package com.funkyganesha;

import com.funkyganesha.util.JMeterCustomTaskUtil;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.util.Map;

/**
 * Used from http://www.javacodegeeks.com/2012/05/apache-jmeter-load-test-whatever-you.html
 */
public class JavaFileExistenceVerifier implements JavaSamplerClient {

    public static final String ABSOLUTE_PATH_OF_THE_FILE_TO_BE_CHECKED_IF_IT_EXISTS = "Absolute path of the file to be checked if it exists";

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        Map<String, String> stringMap = JMeterCustomTaskUtil.accessAllUserDefinedValues(javaSamplerContext);
        String fileName = stringMap.get(ABSOLUTE_PATH_OF_THE_FILE_TO_BE_CHECKED_IF_IT_EXISTS).trim();
        SampleResult sr = new SampleResult();
        sr.sampleStart();
        boolean doesFileExist = JMeterCustomTaskUtil.doesFileExist(fileName);
        String msg;
        if (doesFileExist) {
            msg = "[" + fileName + "] exists.";
        } else {
            msg = "[" + fileName + "] does not exist.";
        }
        sr.setResponseMessage(msg);
        sr.sampleEnd();
        sr.setSuccessful(doesFileExist);
        return sr;
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument(ABSOLUTE_PATH_OF_THE_FILE_TO_BE_CHECKED_IF_IT_EXISTS, "");
        return arguments;
    }
}
