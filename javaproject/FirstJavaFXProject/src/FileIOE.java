import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class FileIOE{

    public static void main(String[] args) {
        String fileName = "example.txt";
        String directoryName = "java";
       
        createFile(fileName);
           Scanner scanner = new Scanner(System.in);
        System.out.println("input:");
        String data = scanner.nextLine();
        writeToFile(fileName, data);

        String content = readFromFile(fileName);
        System.out.println("read from file: " + content);

        String filePath = getFilePath(fileName);
        System.out.println("link of file: " + filePath);

        deleteFile(fileName);

        createDirectory(directoryName);

        scanner.close();
    }

    public static void createFile(String fileName) {
        try {
            File file = new File(fileName);
            if (file.createNewFile()) {
                System.out.println("file created: " + fileName);
            } else {
                System.out.println("file has existed.");
            }
        } catch (IOException e) {
            System.out.println("error.");
            e.printStackTrace();
        }
    }

    public static void writeToFile(String fileName, String data) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write(data);
            writer.close();
            System.out.println("The data has been written to the file.");
        } catch (IOException e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }

    public static String readFromFile(String fileName) {
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("error.");
            e.printStackTrace();
        }
        return content.toString();
    }

    public static String getFilePath(String fileName) {
        File file = new File(fileName);
        return file.getAbsolutePath();
    }

    public static void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.delete()) {
            System.out.println("delete succesfully .");
        } else {
            System.out.println("can not delete.");
        }
    }

    public static void createDirectory(String directoryName) {
        File directory = new File(directoryName);
        if (directory.mkdir()) {
            System.out.println("file has created: " + directoryName);
        } else {
            System.out.println("can not create.");
        }
    }
}
