package project.Atcoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Submission {
    public static void submission(HttpClient session) throws IOException, InterruptedException {
        System.out.println("started submission...");

        // Check if the source code file exists
        Path sourceCodePath = Paths.get(Constants.SOURCE_CODE_PATH);
        if (!Files.exists(sourceCodePath)) {
            System.out.println("source code does not exist");
            System.exit(1);
        }

        // Read the source code from the file
        String sourceCode = Files.readString(sourceCodePath);

        String urlForSubmission = String.format("https://atcoder.jp/contests/%s/submit", Constants.CONTEST_NAME);

        // Send GET request to submission URL
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(urlForSubmission))
                .build();

        HttpResponse<String> getResponse = session.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(getResponse.body());

        // Extract CSRF token
        Element csrfTokenElement = doc.selectFirst("input[name=csrf_token]");
        if (csrfTokenElement == null) {
            throw new IllegalStateException("CSRF token not found");
        }
        String csrfToken = csrfTokenElement.attr("value");

        // Prepare POST request for submission
        String form = String.format(
                "data.TaskScreenName=%s&csrf_token=%s&data.LanguageId=%d&sourceCode=%s",
                Constants.TASK_NAME, csrfToken, Constants.LANGUAGE_ID, sourceCode
        );

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(urlForSubmission))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> postResponse = session.send(postRequest, HttpResponse.BodyHandlers.ofString());
        if (postResponse.statusCode() != 200) {
            throw new IllegalStateException("Submission failed");
        }

        System.out.println("finished submission!\n");
    }
}
