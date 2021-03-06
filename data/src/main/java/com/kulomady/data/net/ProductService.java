package com.kulomady.data.net;

import com.kulomady.data.config.DataConfig;
import com.kulomady.data.entity.response.DataProductEntity;
import com.kulomady.data.entity.response.ProductBean;
import com.kulomady.data.executor.LoggingInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;


/**
 *
 * Created by kulomady
 */
public class ProductService {

    private ProductApi productApi;

    public ProductService() {

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LoggingInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(DataConfig.API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        productApi = retrofit.create(ProductApi.class);
    }


    public ProductApi getApi() {
        return productApi;
    }

    public interface ProductApi {

//        @Headers("Content-Type: application/json; charset=utf-8")

        @GET("{id}")
        Observable<ProductBean> productEntityById(@Path("id") String userId);

        @GET("product")
        Observable<DataProductEntity> productEntityList1(
                @Query("q") String query,
                @Query("device") String device,
                @Query("start") int start,
                @Query("rows") int rows
        );
    }

}
