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
    private static HttpResponse<String> send;

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
                    *   6) AUD -> USD              *
                    *   7) Sair                    *
                    ********************************
                    """);
            System.out.println("Escolha uma opção: ");
            escolha = leia.nextInt();
            if (!(escolha < 1 || escolha > 7)) {
                if (!(escolha == 7)){
                    System.out.println("Digite o valor em " + baseCode[escolha - 1] + ":");
                    int valor = leia.nextInt();
                    URI endereco;
                    if(escolha == 6){
                        endereco = URI.create("https://v6.exchangerate-api.com/v6/12d36bdbdea56dbc87200e92/pair/" + baseCode[escolha - 1] + "/" + baseCode[0]);
                        converterValor(endereco, valor);
                    }else{
                        endereco = URI.create("https://v6.exchangerate-api.com/v6/12d36bdbdea56dbc87200e92/pair/" + baseCode[escolha - 1] + "/" + baseCode[escolha]);
                        converterValor(endereco, valor);
                    }
                }
            } else {
                System.out.println("Escolha uma opção válida!");
            }
        }

    }
    
    private static void converterValor(URI endereco, int primeiroValor) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(endereco)
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            MoedaConversor converter = gson.fromJson(response.body(), MoedaConversor.class);
            double resultado = primeiroValor * converter.conversion_rate();
            System.out.println("Valor convertido: " + resultado);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
