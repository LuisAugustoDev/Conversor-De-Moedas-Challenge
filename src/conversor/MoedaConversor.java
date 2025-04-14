package conversor;

import java.util.Map;

public record MoedaConversor(String base_code, Map<String, Double> conversation_rates) {
}
