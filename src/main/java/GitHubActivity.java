

public class GitHubActivity {
    public static void main(String[] args) {

        if(args.length!=1){
            System.out.println("GitHubActivity [username]");
            return;
        }
        GitHubAPI api=new GitHubAPI();
        api.fetchUserActivity(args[0]);
    }
}