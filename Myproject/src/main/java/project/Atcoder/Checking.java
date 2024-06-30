package project.Atcoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Checking {
    public static void checking(HttpClient session) throws IOException, InterruptedException {
        System.out.println("started checking...\n");

        String problemUrl = "https://atcoder.jp/contests/" + Constants.CONTEST_NAME + "/tasks/" + Constants.TASK_NAME;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(problemUrl))
                .build();

        HttpResponse<String> response = session.send(request, HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(response.body());
        Elements langJa = doc.getElementsByClass("lang-ja");

        if (langJa.isEmpty()) {
            System.out.println("<class=\"lang-ja\"> is missing");
            System.exit(1);
        }

        Document sectionDoc = Jsoup.parse(langJa.get(0).html());
        Elements sections = sectionDoc.getElementsByTag("section");

        List<String> probIn = new ArrayList<>();
        List<String> probOut = new ArrayList<>();

        for (Element section : sections) {
            if (section.html().contains("入力例")) {
                probIn.add(section.getElementsByTag("pre").text());
            }
            if (section.html().contains("出力例")) {
                probOut.add(section.getElementsByTag("pre").text());
            }
        }

        if (probIn.size() != probOut.size()) {
            System.out.println("Different number of sample cases");
            System.exit(1);
        }

        if (probIn.isEmpty()) {
            System.out.println("nothing sample case");
            System.exit(1);
        }

        // Compile the source code
        ProcessBuilder compileProcess = new ProcessBuilder("g++", "-std=gnu++1y", "-O2", "-o", "./a.out", Constants.SOURCE_CODE_PATH);
        Process compile = compileProcess.start();
        compile.waitFor();

        for (int i = 0; i < probIn.size(); i++) {
            // Write input to in.txt
            try (FileWriter writer = new FileWriter("in.txt")) {
                writer.write(probIn.get(i));
            }

            // Execute the compiled program
            ProcessBuilder runProcess = new ProcessBuilder("./a.out");
            runProcess.redirectInput(new File("in.txt"));
            runProcess.redirectOutput(new File("out.txt"));
            Process run = runProcess.start();
            run.waitFor();

            // Read output from out.txt
            String yourOut;
            try (BufferedReader reader = Files.newBufferedReader(Paths.get("out.txt"))) {
                yourOut = reader.lines().collect(Collectors.joining(System.lineSeparator()));
            }

            Files.deleteIfExists(Paths.get("in.txt"));
            Files.deleteIfExists(Paths.get("out.txt"));

            if (!yourOut.trim().equals(probOut.get(i).trim())) {
                System.out.println("WA");
                System.out.println("-input-");
                System.out.println(probIn.get(i));
                System.out.println("-your output-");
                System.out.println(yourOut);
                System.out.println("-true output-");
                System.out.println(probOut.get(i));
                Files.deleteIfExists(Paths.get("a.out"));
                System.exit(1);
            }
        }

        Files.deleteIfExists(Paths.get("a.out"));

        System.out.println("AC そのまさかだよ");
        System.out.println("\nfinished checking\n");
    }
}

