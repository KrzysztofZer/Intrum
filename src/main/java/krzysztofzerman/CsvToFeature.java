package krzysztofzerman;

import java.io.*;
import java.util.Scanner;

public class CsvToFeature {
    public static void importDataFromCsvToFeature(String featureFilePath) {
        try {

            File inFile = new File(featureFilePath);

            if (!inFile.isFile()) {
                System.out.println("Provided feature file does not exist");
                return;
            }

            //Construct the new file that will later be renamed to the original filename.
            File tempFile = new File(inFile.getAbsolutePath() + ".tmp");

            BufferedReader br = new BufferedReader(new FileReader(featureFilePath));
            PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

            String line = null;

            //Read from the original file and write to the new
            //unless content matches data to be removed.
            boolean deleteIfPipe = false;
            boolean changeNextExamples = false;
            String csvPath;
            String examples = "|There are no examples|";
            while ((line = br.readLine()) != null) {

//                Activated when line contains @csvFile and get data from path to string with examples
                if (line.contains("@csvFile")) {
                    pw.println(line);
                    pw.flush();
                    int startPathIndex = line.indexOf("=") + 1;
                    int endPathIndex = line.length();
                    csvPath = line.substring(startPathIndex, endPathIndex);
                    examples = inputDataFromCsv(csvPath);
                    changeNextExamples = true;
                }
//                If there is line with Examples prepare code to delete all next lines with pipes
                else if (line.contains("Examples:")) {
                    pw.println(line);
                    pw.flush();
                    if (changeNextExamples) {
                        changeNextExamples = false;
                        deleteIfPipe = true;
                    }
                }
//                Delete lines with pipe when change that was decelerate before
                else if (deleteIfPipe && line.contains("|")) {
                    System.out.println("Example row deleted");
//                    If line does not have pipe then stop deleting process and save line
                } else if (deleteIfPipe && !line.contains("|")) {
                    pw.println(examples);
                    pw.println(line);
                    pw.flush();
                    deleteIfPipe = false;
//                    Save line
                } else {
                    pw.println(line);
                    pw.flush();
                }
            }

//            In case of ending file on "Examples:" add csv examples at end of feature
            if (deleteIfPipe) {
                pw.println(examples);
            }
            pw.close();
            br.close();

            if (!inFile.delete()) {
                System.out.println("Original file was not delete");
                return;
            }

            if (!tempFile.renameTo(inFile))
                System.out.println("Temporary file cannot have changed name");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static String inputDataFromCsv(String pathToCsv) {
        File file = new File(pathToCsv);
        StringBuilder fileContents = new StringBuilder((int) file.length());

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                fileContents.append("|" + scanner.nextLine() + "|");
//                Used to not add new line at end of examples
                if (scanner.hasNextLine()) {
                    fileContents.append("\n");
                }
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileContents.toString();
    }
}
