package project.Atcoder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Login {
    public static void login(HttpClient session) throws IOException, InterruptedException {
        System.out.println("started login...");

        // Send GET request to login URL
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(Constants.LOGIN_URL))
                .build();

        HttpResponse<String> getResponse = session.send(getRequest, HttpResponse.BodyHandlers.ofString());
        Document doc = Jsoup.parse(getResponse.body());

        // Extract CSRF token
        Element csrfTokenElement = doc.selectFirst("input[name=csrf_token]");
        if (csrfTokenElement == null) {
            throw new IllegalStateException("CSRF token not found");
        }
        String csrfToken = csrfTokenElement.attr("value");

        // Prepare POST request for login
        String form = String.format("csrf_token=%s&username=%s&password=%s",
                csrfToken, Constants.USER_NAME, Constants.PASSWORD);

        HttpRequest postRequest = HttpRequest.newBuilder()
                .uri(URI.create(Constants.LOGIN_URL))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36")
                .POST(HttpRequest.BodyPublishers.ofString(form))
                .build();

        HttpResponse<String> postResponse = session.send(postRequest, HttpResponse.BodyHandlers.ofString());
        if (postResponse.statusCode() != 200) {
            throw new IllegalStateException("Login failed");
        }

        System.out.println("finished login!\n");
    }
}
