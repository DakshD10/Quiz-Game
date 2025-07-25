 import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class QuizService {

    public ArrayList<Question> fetchQuestions() {
        ArrayList<Question> allQuestions = new ArrayList<>();

        try {
            URL url = new URL("https://opentdb.com/api.php?amount=5&category=18&difficulty=easy&type=multiple");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseText.append(line);
            }
            reader.close();

            
            JSONArray results = extractResults(responseText.toString());

            for (int i = 0; i < results.size(); i++) {
                JSONObject qObj = (JSONObject) results.get(i);

                String questionText = htmlDecode((String) qObj.get("question"));
                String correctAnswer = htmlDecode((String) qObj.get("correct_answer"));

                JSONArray incorrectAnswers = (JSONArray) qObj.get("incorrect_answers");
                ArrayList<String> options = new ArrayList<>();

                for (int j = 0; j < incorrectAnswers.size(); j++) {
                    options.add(htmlDecode((String) incorrectAnswers.get(j)));
                }

                options.add(correctAnswer);
                Collections.shuffle(options);

                Question question = new Question(questionText, correctAnswer, options);
                allQuestions.add(question);
            }

        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        return allQuestions;
    }

    //  Extracted method
     private JSONArray extractResults(String jsonResponse) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(jsonResponse);
            return (JSONArray) json.get("results");
        } catch (ParseException e) { 
            System.out.println("Error parsing JSON: " + e.getMessage());
            return new JSONArray();
        }
    }

    private String htmlDecode(String text) {
        return text.replace("&quot;", "\"")
                .replace("&#039;", "'")
                .replace("&amp;", "&")
                .replace("&eacute;", "é");
    }
}
