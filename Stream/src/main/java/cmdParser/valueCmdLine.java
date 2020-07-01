package cmdParser;

import org.apache.commons.cli.*;

public class valueCmdLine {
    private static int lengthPhrase = 2;
    private static int frequency = 2;
    private static final Options options = new Options();

    public static void parseCmdLine(String[] args){
        CommandLineParser parser = new DefaultParser();
        try {
            Option n = new Option("n", true, "setting the lenth of a phrase");
            Option m = new Option("m", true, "setting the frequency of a phrase");
            n.setArgs(1);
            m.setArgs(1);
            m.setArgName("m");
            options.addOption(n);
            options.addOption(m);
            CommandLine cmd = parser.parse(options, args);

            if(cmd.hasOption('n') && !(Integer.parseInt(cmd.getOptionValue("n")) < 1)){
                lengthPhrase = Integer.parseInt(cmd.getOptionValue("n"));
            }
            if(cmd.hasOption('m') && !(Integer.parseInt(cmd.getOptionValue("m")) < 1)){
                frequency = Integer.parseInt(cmd.getOptionValue("m"));
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static int getLegthPhrase(){
        return lengthPhrase;
    }

    public static int getfrequency(){
        return  frequency;
    }
}
