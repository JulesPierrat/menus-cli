package com.cicdlectures.menucli;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "menucli")
public class MenuClientCommand implements Runnable {
    @Option(names = { "--server-url" })
    private String url = "http://localhost:8080";

    @Option(names = "-id")
    private Integer to_delete;

    public static void main(String[] args) {
        System.out.println("[SUCESS] menu-cli load");
        int exitCode = new CommandLine(new MenuClientCommand()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println("Manage Menus!");
    }

    @Command(name = "list-menus")
    public void ListMenusCommand() throws Exception{
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url + "/menus"))
                .GET()
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

    }

    @Command(name = "delete-menu")
    public void DeleteMenuCommand() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url+ "/menus/" + this.to_delete))
                .DELETE()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            System.out.println("Mauvaise requête ! Format de la requete expliquée dans le README.md ");
        } else {
            System.out.println("Menu Retiré !");
        }
    }

}