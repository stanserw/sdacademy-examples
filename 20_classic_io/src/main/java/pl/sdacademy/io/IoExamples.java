package pl.sdacademy.io;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class IoExamples {
	public static void main(String[] args) throws IOException {
		// ioUtilsReadAllLinesExample();
		// scannerExample();
		bufferedReaderExample();
		// bufferedWriterExample();
	}

	private static void ioUtilsReadAllLinesExample() throws IOException {
		List<String> allLines = IOUtils.readLines(source(), Charset.forName("UTF-8"));
		System.out.println(allLines);

		String entireFile = IOUtils.toString(source(), Charset.forName("UTF-8"));
		System.out.println(entireFile);
	}

	private static void bufferedWriterExample() throws IOException {
		try (BufferedWriter bufferedWriter =
			     new BufferedWriter(new OutputStreamWriter(new FileOutputStream(Paths.get("files",
				     "targetFile" + ".txt").toFile()), Charset.forName("UTF-8")))) {
			bufferedWriter.write("String testowy.");
			bufferedWriter.newLine();
			bufferedWriter.write("String testowy 2.");
			bufferedWriter.flush();
		}
	}

	private static void bufferedReaderExample() throws IOException {
		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(source(),
			Charset.forName("UTF-8")))) {
			String line = bufferedReader.readLine();
			while (line != null) {
				System.out.println(line);
				line = bufferedReader.readLine();
			}
		}
	}

	private static void scannerExample() throws IOException {
		Scanner scanner = new Scanner(source(), "UTF-8");
		scanner.useDelimiter("\n");
		while (scanner.hasNext()) {
			String line = scanner.next();
			System.out.println(line);
		}
	}

	private static InputStream source() throws IOException {
		return openFile();
		// return openConnection();
		// return inMemory();
	}

	private static InputStream inMemory() {
		return new ByteArrayInputStream("hello".getBytes(Charset.forName("UTF-8")));
	}

	private static InputStream openFile() throws FileNotFoundException {
		return new FileInputStream(Paths.get("files", "sourceFile.txt").toFile());
	}

	private static InputStream openConnection() throws IOException {
		URLConnection urlConnection = new URL("https://google.com").openConnection();
		return urlConnection.getInputStream();
	}
}
