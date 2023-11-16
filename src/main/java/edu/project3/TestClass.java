package edu.project3;

public class TestClass {
    public static void main(String[] args) {
        String url = "https://raw.githubusercontent.com/" +
            "elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs";
        Console.inputConsole(url, "2015-05-17T08:05:00Z", "2015-05-20T08:05:05Z", FileType.ADOC);
    }
}
