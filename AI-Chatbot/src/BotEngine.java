import java.io.*;
import java.util.*;

public class BotEngine {

    Map<String, String> faq = new HashMap<>();

    public BotEngine() {
        loadFAQ();
    }

    void loadFAQ() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/faq.txt"));
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split("=");

                if (parts.length == 2) {
                    faq.put(parts[0].toLowerCase(), parts[1]);
                }
            }

            br.close();

        } catch (Exception e) {
            System.out.println("FAQ not loaded ❌");
        }
    }

    public String getResponse(String input) {
        input = input.toLowerCase();

        for (String key : faq.keySet()) {
            if (input.contains(key)) {
                return faq.get(key);
            }
        }

        if (input.contains("hi") || input.contains("hello"))
            return "Hey there! 😊";

        if (input.contains("how are you"))
            return "I'm running smoothly 🚀";

        return "🤔 I didn't understand that.";
    }
}