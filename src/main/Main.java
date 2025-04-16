package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import conversor.MoedaConversor;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner leia = new Scanner(System.in);
        String[][] pares = {
                {"USD", "BRL"},
                {"BRL", "JPY"},
                {"JPY", "EUR"},
                {"EUR", "ARS"},
                {"ARS", "AUD"},
                {"AUD", "USD"}
        };
        var escolha = 0;

        while (escolha != 7) {

            System.out.println("""
                    ********************************
                    *      Conversor de Moedas     *
                    *                              *
                    *   1) USD -> BRL              *
                    *   2) BRL -> JPY              *
                    *   3) JPY -> EUR              *
                    *   4) EUR -> ARS              *
                    *   5) ARS -> AUD              *
                    *   6) AUD -> USD              *
                    *   7) Sair                    *
                    ********************************
                    """);
            System.out.println("Escolha uma opção: ");
            escolha = leia.nextInt();
            if (escolha >= 1 && escolha <= 6) {
                String origem = pares[escolha - 1][0];
                String destino = pares[escolha - 1][1];

                System.out.print("Digite o valor em " + origem + ": ");
                double valor = leia.nextDouble();

                URI endereco = URI.create("https://v6.exchangerate-api.com/v6/12d36bdbdea56dbc87200e92/pair/" + origem + "/" + destino);
                converterValor(endereco, valor, origem, destino);
            } else if (escolha != 7) {
                System.out.println("Escolha uma opção válida!");
            }
        }

    }

    private static void converterValor(URI endereco, double valor, String origem, String destino) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            MoedaConversor conversor = gson.fromJson(response.body(), MoedaConversor.class);

            double resultado = valor * conversor.conversion_rate();
            System.out.printf("💱 %.2f %s = %.2f %s\n", valor, origem, resultado, destino);
        } catch (IOException | InterruptedException e) {
            System.out.println("Erro ao converter moeda: " + e.getMessage());
        }
    }

}
