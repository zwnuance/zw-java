package org.zw.net;

import io.reactivex.Observable;
import org.zw.net.domain.Contributor;
import org.zw.net.domain.Repo;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * Created by zw on 2017/4/2.
 */
public interface GithubService {

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);

    @GET("users/{user}/repos")
    Call<List<Repo>> queryRepos(@Path("user") String user, @Query("cate") String cate);


    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> contributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

}
