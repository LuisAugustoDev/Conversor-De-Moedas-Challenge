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
        String[] baseCode = {"USD", "BRL", "JPY", "EUR", "ARS", "AUD"};
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
                    *   6) BRL -> USD              *
                    *   7) Sair                    *
                    ********************************
                    """);

            escolha = leia.nextInt();

            if (!(escolha < 1 || escolha > 7)) {
                if (!(escolha == 7)) {
                    URI endereco = URI.create("https://v6.exchangerate-api.com/v6/12d36bdbdea56dbc87200e92/latest/" + baseCode[escolha - 1]);
                    System.out.println("Digite o primeiro valor: ");
                    double primeiroValor = leia.nextDouble();
                    System.out.println("Digite o segundo valor: ");
                    double segundoValor = leia.nextDouble();
                    try {
                        HttpClient client = HttpClient.newHttpClient();
                        HttpRequest request = HttpRequest.newBuilder()
                                .uri(endereco)
                                .build();
                        HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());
                        Gson gson = new GsonBuilder().setPrettyPrinting().create();
                        MoedaConversor converter = gson.fromJson(response.body(), MoedaConversor.class);
                        double valorConvertido = converter.conversation_rates().get("BRL");
                        System.out.println("Valor convertido: " + valorConvertido);
                    } catch (IOException | InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            } else {
                System.out.println("Escolha uma opção válida!");
            }
        }

    }
}
