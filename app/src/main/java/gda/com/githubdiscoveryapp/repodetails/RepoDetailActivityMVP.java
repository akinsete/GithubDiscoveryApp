package gda.com.githubdiscoveryapp.repodetails;

/**
 * Created by sundayakinsete on 21/02/2018.
 */

public interface RepoDetailActivityMVP {


    interface View{

    }

    interface Presenter{

        void setView(RepoDetailActivityMVP.View view);
    }


    interface Model{

    }
    
}
