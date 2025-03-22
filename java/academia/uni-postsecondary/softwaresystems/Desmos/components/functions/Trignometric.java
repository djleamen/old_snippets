package csci2020u.lab09.components.functions;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import csci2020u.lab09.GraphGUI;

public abstract class Trignometric extends Function {

    public double a, k;
    
    public Trignometric(GraphGUI gui, String function, String name) {
        super(gui);
        Pattern sortTrig = Pattern.compile("^([+\\-])?(\\d+(.\\d+)?)?" + name + "\\(([+\\-])?(\\d+(.\\d+)?)?x\\)$");
        Matcher matcher = sortTrig.matcher(function);

        if (matcher.find()) {
            a = (matcher.group(2) == null) ? (matcher.group(1) != null) ? (matcher.group(1).equals("-")) ? -1 : 1 : 1 : (matcher.group(1) != null) ? (matcher.group(1).equals("-")) ? -Double.parseDouble(matcher.group(2)) : Double.parseDouble(matcher.group(2)) : Double.parseDouble(matcher.group(2));
            k = (matcher.group(5) == null) ? (matcher.group(4) != null) ? (matcher.group(4).equals("-")) ? -1 : 1 : 1 : (matcher.group(4) != null) ? (matcher.group(4).equals("-")) ? -Double.parseDouble(matcher.group(5)) : Double.parseDouble(matcher.group(5)) : Double.parseDouble(matcher.group(5));
        }
    }
}
