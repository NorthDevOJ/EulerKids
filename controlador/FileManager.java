package controlador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveData(String[] data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            for (String field : data) {
                writer.write(field);
                writer.newLine();
            }
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String[]> readData() {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            List<String> currentEntry = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty()) {
                    data.add(currentEntry.toArray(new String[0]));
                    currentEntry.clear();
                } else {
                    currentEntry.add(line);
                }
            }
            // Add the last entry if not already added
            if (!currentEntry.isEmpty()) {
                data.add(currentEntry.toArray(new String[0]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public void writeData(List<String[]> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String[] entry : data) {
                for (String field : entry) {
                    writer.write(field);
                    writer.newLine();
                }
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void modifyData(String oldValue, String newValue) {
        List<String[]> data = readData();
        for (String[] entry : data) {
            for (int i = 0; i < entry.length; i++) {
                if (entry[i].equals(oldValue)) {
                    entry[i] = newValue;
                }
            }
        }
        writeData(data);
    }
}