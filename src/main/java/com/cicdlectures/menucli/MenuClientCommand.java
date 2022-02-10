package com.cicdlectures.menucli;

import java.util.ArrayList;
import java.util.List;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "menucli")
public class MenuClientCommand implements Runnable {
    @Option(names = {"--server-url"})
    private String url = "localhost:8080";

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
    public void ListMenusCommand() {
        System.out.println("List all the menus");
    }

    @Command(name = "delete-menu")
    public void DeleteMenuCommand() {
        System.out.println("Delete one menu");
    }

}