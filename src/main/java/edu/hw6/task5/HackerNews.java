package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HackerNews {
    private HackerNews() {}

    public static final int CODE_SUCCESSFUL = 200;


    public static String news(long id) {
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = "https://hacker-news.firebaseio.com/v0/item/" + id + ".json";
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();
            var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != CODE_SUCCESSFUL) {
                return null;
            }

            String body = response.body();
            String regex = ".*\"title\":\"(.*)\",\"type.*";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(body);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return null;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return null;
        }
    }

    public static long[] hackerNewsTopStories() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
                .GET()
                .build();
            var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != CODE_SUCCESSFUL) {
                return new long[0];
            }

            String body = response.body().replaceAll("\\[", "").replaceAll("]", "");
            String[] data = body.split(",");
            long[] longData = new long[data.length];
            for (int i = 0; i < data.length; ++i) {
                longData[i] = Long.parseLong(data[i]);
            }
            return longData;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return new long[0];
        }
    }
}
