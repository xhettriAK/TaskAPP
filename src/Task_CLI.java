import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.todo.main.Function.*;

public class Task_CLI {
    public static void main(String[] args) {
        var path = Path.of("C:\\Users\\RYET\\IdeaProjects\\TASKAPP\\FileOutput\\Task_list.json");
        if (!Files.exists(path)) {
            try{
                Files.writeString(Path.of(path.toUri()), """
                        {
                        "Tasks":
                        [
                     
                        ]
                        }""");} catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if(args.length==0) {
            System.out.println(".......Welcome to do list App.......");
            System.out.println("Type -h or -help or -Help for usage command");
        }
        else {
            switch (args[0]) {
                case "-h", "-help", "-Help", "-H" -> print_command();
                case "add" -> add(args[1]);
                case "update" -> update();
                case "mark-in-progress"  ->markinprogress();
                case "list" -> list(args[0]);
                case "delete" -> delete();
                case "mark-done" -> markdone();
                default -> System.out.println("Type -h or -help or -Help to look for usage command");
            }
        }
    }
}