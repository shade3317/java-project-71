package hexlet.code;


import picocli.CommandLine;
import picocli.CommandLine.Command;
import java.util.concurrent.Callable;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Option;



@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.0",
        description = "Compares two configuration files and shows a difference.")

public final class App implements Callable<Integer> {
    @Parameters(index = "0", description = "path to first file")
    private String filepath1;
    @Parameters(index = "1", description = "path to second file")
    private String filepath2;
    @Option(names = {"-f", "--format"},
            defaultValue = "stylish",
            description = "output format [default: ${DEFAULT-VALUE}]")
    private String format;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Show this help message and exit.")
    private boolean helpRequested;
    @Option(names = {"-V", "--version"}, description = "Print version information and exit.")
    private boolean version;



    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public Integer call() throws Exception {
        String differs = Differ.generate(filepath1, filepath2, format);
        System.out.println(differs);
        return 0;
    }
}
