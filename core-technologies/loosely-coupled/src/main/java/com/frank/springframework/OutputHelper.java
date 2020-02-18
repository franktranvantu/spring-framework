package com.frank.springframework;

public class OutputHelper {

    private IOutputGenerator outputGenerator;

//    public OutputHelper() {
//        this.outputGenerator = new CsvOutputGenerator();
//    }

    public void setOutputGenerator(IOutputGenerator outputGenerator) {
        this.outputGenerator = outputGenerator;
    }

    public void generateOutput() {
        outputGenerator.generateOutput();
    }
}
