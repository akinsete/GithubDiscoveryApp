package gda.com.githubdiscoveryapp.usersrepolist;

import gda.com.githubdiscoveryapp.data.models.Repo;
import gda.com.githubdiscoveryapp.searchuser.SearchActivityMVP;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public interface UserRepoListActivityMVP {


    interface View{

        void gotoDetailView(Repo repo);
    }

    interface Presenter{

        void setView(UserRepoListActivityMVP.View view);


        void navigateToDetailView(Repo repo);
    }

    interface Model{

    }
}
