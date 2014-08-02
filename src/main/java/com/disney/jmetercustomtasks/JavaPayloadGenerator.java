package com.disney.jmetercustomtasks;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class JavaPayloadGenerator implements JavaSamplerClient {

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        arguments.addArgument("What is the size of the payload? (Default is 4KB)", "4096");
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setupTest(JavaSamplerContext javaSamplerContext) {
    }

    @Override
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
    }
}
