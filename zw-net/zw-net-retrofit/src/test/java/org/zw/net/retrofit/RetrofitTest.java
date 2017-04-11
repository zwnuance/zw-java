package org.zw.net.retrofit;

import io.reactivex.Observable;
import org.junit.Before;
import org.junit.Test;
import org.zw.net.GithubService;
import org.zw.net.domain.Contributor;
import org.zw.net.domain.Repo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zw on 2017/4/2.
 */
public class RetrofitTest {

    public static final String API_URL = "https://api.github.com";


    private GithubService githubServiceJson;

    private GithubService githubServiceRxJava;

    @Before
    public void init() {
        // Create a very simple REST adapter which points the GitHub API.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of our GitHub API interface.
        githubServiceJson = retrofit.create(GithubService.class);
        assertNotNull(githubServiceJson);

        Retrofit retrofitRxJava = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        githubServiceRxJava = retrofitRxJava.create(GithubService.class);
        assertNotNull(githubServiceRxJava);
    }


    @Test
    public void testListRepos() throws IOException {

        // Create a call instance for looking up Retrofit contributors.
        Call<List<Repo>> call = githubServiceJson.listRepos("zwnuance");

        // Fetch and print a list of the contributors to the library.
        List<Repo> repos = call.execute().body();
        for (Repo repo : repos) {
            System.out.println(repo);
        }
    }

    @Test
    public void testQueryRepo() throws IOException {
        Call<List<Repo>> call = githubServiceJson.queryRepos("square", "stars:>10000");
        List<Repo> repos = call.execute().body();
        for (Repo repo : repos) {
            System.out.println(repo);
        }
    }

    @Test
    public void testRxJava() {
        Observable<List<Contributor>> observable = githubServiceRxJava.contributors("square", "retrofit");
    }
}
