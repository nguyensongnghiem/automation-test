package org.example.github;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
//import org.json.JSONArray;
//import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GithubApiHelper {
    private static final String GITHUB_API_BASE = "https://api.github.com";
    private static final String ORGANIZATION = "SeleniumHQ";
    private static final String AUTH_TOKEN = ""; // Thay bằng token GitHub
    private static final Gson gson = new Gson();
    public CloseableHttpResponse getApiResponse() throws IOException {
        String url = GITHUB_API_BASE + "/orgs/" + ORGANIZATION + "/repos";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);
//            request.setHeader("Authorization", "token " + AUTH_TOKEN);
        request.setHeader("Accept", "application/vnd.github.v3+json");
        return httpClient.execute(request);
    }

    public static List<Repository> getRepositories(CloseableHttpResponse response) throws IOException, ParseException {
        List<Repository> repos = new ArrayList<>();
            String json = EntityUtils.toString(response.getEntity());
//            JSONArray jsonArray = new JSONArray(json);
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject repoJson = jsonArray.getJSONObject(i);
//                repos.add(new Repository(
//                        repoJson.getString("name"),
//                        repoJson.getString("updated_at"),
//                        repoJson.getInt("watchers_count"),
//                        repoJson.getInt("open_issues_count")
//                ));
//            }

        try {
            // Sử dụng Gson để parse JSON Array thành List<Repository>
            // TypeToken dùng để giữ lại thông tin kiểu Generic (List<Repository>) khi compile time
            Type repositoryListType = new TypeToken<ArrayList<Repository>>() {}.getType();
            repos = gson.fromJson(json, repositoryListType);

            // GitHub API dùng "stargazers_count", nhưng yêu cầu là "watchers_count".
            // Class Repository của bạn đã ánh xạ stargazers_count vào watchers.
            // Nếu bạn muốn lấy html_url, đảm bảo nó có trong class Repository.

        } catch (JsonSyntaxException e) {
            // Lỗi này sẽ xảy ra nếu chuỗi JSON không hợp lệ hoặc không khớp với cấu trúc List<Repository>
            // Ví dụ: khi API trả về một JSONObject lỗi thay vì một JSONArray của repositories
            System.err.println("Lỗi JSON parsing. Phản hồi không phải là JSON hợp lệ hoặc định dạng không mong muốn:");
            System.err.println(json); // In ra phản hồi để debug
            throw new IOException("JSON parsing error: " + e.getMessage(), e);
        }
        return repos;
    }

    public static int calculateTotalOpenIssues(List<Repository> repositories) {
        return repositories.stream().mapToInt(Repository::getOpen_issues_count).sum();
    }

    public static Repository findMostWatchedRepository(List<Repository> repositories) {
        return repositories.stream().max(Comparator.comparingInt(Repository::getWatchers)).orElse(null);
    }
}
