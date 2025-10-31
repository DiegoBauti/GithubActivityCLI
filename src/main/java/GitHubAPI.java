import com.google.gson.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class GitHubAPI {

    public void fetchUserActivity(String username){
        try {
            HttpClient client=HttpClient.newHttpClient();
            HttpRequest request=HttpRequest.newBuilder()
                    .uri(new URI("https://api.github.com/users/"+username+"/events"))
                    .GET()
                    .header("accept","application/json")
                    .build();
            HttpResponse<String> response=client.send(request,HttpResponse.BodyHandlers.ofString());

            if (response.statusCode()==200){
                System.out.println("Request successful");
            } else if (response.statusCode()==404) {
                System.out.println("User not found. Please check the username");
            } else {
                System.out.println("Request failed with status code :"+response.statusCode());
            }

            display(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void display(String body) {
        JsonArray jsonArray = JsonParser.parseString(body).getAsJsonArray();
        StringBuilder output = new StringBuilder("Output:\n");
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (JsonElement jsonElement : jsonArray) {
            JsonObject jsonObject=jsonElement.getAsJsonObject();
            String type=jsonObject.get("type").getAsString();
            JsonObject payload=jsonObject.getAsJsonObject("payload");
            String repository=jsonObject.getAsJsonObject("repo").get("name").getAsString();
            String type_formated=type.replace("Event","");
            ZonedDateTime date=ZonedDateTime.parse(jsonObject.get("created_at").getAsString());

            String action,paylAction,ref_type;

            switch (type){
                case "IssuesEvent":
                case "PullRequestEvent":
                case "IssueCommentEvent":
                case "PullRequestReviewEvent":
                case "PullRequestReviewCommentEvent":
                    paylAction=payload.has("action")?payload.get("action").getAsString():"";
                    action=String.format("[%s] %s %s in %s",type,type_formated,paylAction,repository);
                    break;
                case "DeleteEvent":
                case "CreateEvent":
                    ref_type= payload.has("ref_type")?payload.get("ref_type").getAsString():"";
                    action=String.format("[%s] %s %s in %s",type,type_formated,ref_type,repository);
                    break;
                case "PushEvent":
                    action=String.format("[%s] Pushed commit to %s",type,repository);
                    break;
                case "ForkEvent":
                    action=String.format("[%s] Forked %s",type,repository);
                    break;
                default:
                    action=String.format("[%s] %s %s",type,type_formated,repository);
                    break;
            }
            output.append(action).append(" ("+date.format(formatter)+")").append("\n");
        }
        System.out.println(output);
    }

}
