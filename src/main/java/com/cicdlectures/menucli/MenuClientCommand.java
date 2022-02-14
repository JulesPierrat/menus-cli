package com.cicdlectures.menucli;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "menucli")
public class MenuClientCommand implements Runnable {
    @Option(names = { "--server-url" })
    private String url = "http://localhost:8080";

    @Parameters
    private List<Integer> to_delete = new ArrayList<>();

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
    public void ListMenusCommand() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder(URI.create(this.url + "/menus"))
                .GET()
                .header("Content-type", "application/json")
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        JSONArray array = new JSONArray(response.body());

        System.out.println(array);

        for (int i = 0; i < array.length(); i++) {

            JSONObject plat = array.getJSONObject(i);
            System.out.println(plat.getInt("id") + " - " + plat.getString("name"));
            JSONArray dishes = plat.getJSONArray("dishes");

            for (int j = 0; j < dishes.length(); j++) {
                System.out.println(" - " + dishes.getJSONObject(j).getString("name"));
            }
            System.out.println("\n ------------------ \n ");
        }
    }

    @Command(name = "delete-menu")
    public void DeleteMenuCommand() {
        System.out.println(to_delete.get(0));
    }

}