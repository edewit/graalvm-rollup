import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        final ScriptEngine engine = new ScriptEngineManager().getEngineByName("Graal.js");

        Context cx = Context.newBuilder("js").allowIO(true)
                .allowExperimentalOptions(true).option("js.scripting", "true").build();

        String src = readFile("/main.mjs");
        cx.eval(Source.newBuilder("js", src, "main.mjs").build());
    }

    static String readFile(String name) throws FileNotFoundException {
        Scanner sc = new Scanner(Main.class.getResourceAsStream(name));
        sc.useDelimiter("\\Z");
        return sc.next();
    }
}
