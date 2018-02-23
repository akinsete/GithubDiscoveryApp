package gda.com.githubdiscoveryapp.repodetails;

import dagger.Module;
import dagger.Provides;
import gda.com.githubdiscoveryapp.data.github.GithubService;

/**
 * Created by sundayakinsete on 23/02/2018.
 */


@Module
public class RepoDetailModule {

    @Provides
    public RepoDetailActivityMVP.Presenter provideRepoDetailPresenter(RepoDetailActivityMVP.Model model){
        return new RepoDetailActivityPresenter(model);
    }

    @Provides
    public RepoDetailActivityMVP.Model provideRepoDetailModel(GithubService githubService){
        return new RepoDetailModel(githubService);
    }




}
