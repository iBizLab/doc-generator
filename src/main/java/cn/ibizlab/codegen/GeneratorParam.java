package cn.ibizlab.codegen;

import org.kohsuke.args4j.Option;


public class GeneratorParam {

    @Option(name = "-modelpath", aliases = {"-m"}, required = true, usage = "模型路径")
    private String modelPath;

    @Option(name = "-output", aliases = {"-o"}, required = true, usage = "代码生成路径")
    private String output;

    public String getModelPath() {
        return modelPath;
    }

    public void setModelPath(String modelPath) {
        this.modelPath = modelPath;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
