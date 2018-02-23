package gda.com.githubdiscoveryapp.data;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import gda.com.githubdiscoveryapp.BuildConfig;
import gda.com.githubdiscoveryapp.data.geocoder.GeocodeAPI;
import gda.com.githubdiscoveryapp.data.geocoder.GeocoderService;
import gda.com.githubdiscoveryapp.data.github.GithubAPI;
import gda.com.githubdiscoveryapp.data.github.GithubService;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by sundayakinsete on 22/02/2018.
 */


@Module
public class ApiModule {



    @Provides
    public OkHttpClient provideClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);

        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }


    @Provides
    public Retrofit provideRetrofit(String baseURL,OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public GeocoderService providesGeocoderService(GeocodeAPI geocodeAPI) {
        return new GeocoderService(geocodeAPI);
    }

    @Provides
    public GeocodeAPI provideGeocodeApiService(){
        return provideRetrofit(BuildConfig.GEOCODER_BASE_URL,provideClient()).create(GeocodeAPI.class);
    }


    @Provides
    @Singleton
    @SuppressWarnings("unused")
    public GithubService providesGithubService(GithubAPI githubApi) {
        return new GithubService(githubApi);
    }

    @Provides
    public GithubAPI provideGithubApiService(){
        return provideRetrofit(BuildConfig.GITHUB_BASE_URL,provideClient()).create(GithubAPI.class);
    }





}
