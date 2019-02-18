import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, TemplateException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setDirectoryForTemplateLoading(new File("/Users/carl/workspace/freemarker/src/main/resources/templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);

        Template temp = cfg.getTemplate("hello.ftlh");

        DataModel root = new DataModel();

        root.setUser("小红");

        List<Animal> animals = new LinkedList<Animal>();
        animals.add(new Animal("a", "12"));
        animals.add(new Animal("b", "12"));

        root.setAnimals(animals);

        Writer out = new OutputStreamWriter(System.out);
        temp.process(root, out);
    }
}
