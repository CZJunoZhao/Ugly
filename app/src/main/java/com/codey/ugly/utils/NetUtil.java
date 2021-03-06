package com.codey.ugly.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mr.Codey on 2016/4/22.
 */
public class NetUtil
{
    private static final String baseUrl="http://192.168.1.110:8080/ugly/";

    private NetService netService;

    public NetService getNetService()
    {
        return netService;
    }
    private static  NetUtil netUtil=new NetUtil();
    public static NetUtil getInstance()
    {
        return netUtil;
    }
    private NetUtil()
    {
            setClient();
    }

    private static String cookies;

    public static String getCookies() {
        return cookies;
    }

    public static void setCookies(String cookies) {
        NetUtil.cookies = cookies;
    }
    private void setClient()
    {
        Interceptor interceptor=new Interceptor()
        {
            @Override
            public Response intercept(Chain chain) throws IOException
            {
                Request request=chain.request();
                if (null!=cookies&&cookies.length()>0)
                {
                    Request compressedRequest = request.newBuilder()
                            .addHeader("Cookie", cookies)
                            .build();
                    Response response = chain.proceed(compressedRequest);
                    return response;
                }

                return chain.proceed(request);
            }
        };
        OkHttpClient client=new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new StringConverterFactory())
                .client(client)
                .build();
        netService=retrofit.create(NetService.class);
    }
}
