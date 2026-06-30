package com.todo.main;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Function{

    static Path filepath = Path.of("C:\\Users\\RYET\\IdeaProjects\\TASKAPP\\FileOutput\\Task_list.json");
    static List<String> fileLines;
    static List<String> modifiedLines = new ArrayList<>();
    static {
        try {
            fileLines = Files.readAllLines(filepath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static int value = 0;
    public Function() throws IOException {
    }

    public static void print_command()
    {
        System.out.println("Commands:");
        System.out.println("""
             { add: Add new task\s
             
               update: Update existing Task\s
             
               delete: Delete existing Task\s
             
               mark-in-progess: Marking a task as in progress\s
             
               mark-done: Marking a task as in done\s
             
               list: list all the task\s
             
               list done: list all the done task\s
             
               list todo: list all the task that are need to do\s
             
               list in-progress: list all the task that are need progress\s
             }""");
    }
    public static void idcheck()
    {
        int value = 0;
        for (String line : fileLines) {

            if(Integer.parseInt(line)>=0)
            {
                value+=Integer.parseInt(line);
                break;
            }
            System.out.println(value);
        }
    }
    public static void add(String args) {
        try {

            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy|HH:mm:ss");
            String date = "\"" + now.format(formatter) + "\"";
            idcheck();
            boolean inserted = false;
            for (String line : fileLines) {
                modifiedLines.add(line);
                // 2. DETECT THE START OF THE JSON ARRAY
                if (!inserted && line.trim().equals("[")  ) {
                 // Build a valid, single JSON object block
                 modifiedLines.add("  {");
                 modifiedLines.add("    \"ID\": " + value + ",");
                 modifiedLines.add("    \"Task\": \"" +args + "\",");
                 modifiedLines.add("    \"status\": \"todo\",");
                 modifiedLines.add("    \"created at\": " + date + ",");
                 modifiedLines.add("    \"updated at\": " + date);
                 modifiedLines.add("  }"); //

                 inserted = true; // Ensure we only inject this once
                }
            }
            // 3. WRITE LAST (Safely overwrite the file with fresh data)
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.toFile()))) {
                for (String write : modifiedLines) {
                    bw.write(write);
                    bw.newLine(); // Add newlines so it doesn't bunch up into one line
                }
            }
            System.out.println("Task added successfully.");
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }

    public static void update()
    {

    }
    public static void markinprogress()
    {

    }
    public static void markdone()
    {

    }
    public static void delete()
    {

    }
    public static void list(String arg)
    {

    }

}
