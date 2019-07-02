package com.example.latte_core.net.download;

import android.os.AsyncTask;

import com.example.latte_core.net.RestCreator;
import com.example.latte_core.net.callback.IError;
import com.example.latte_core.net.callback.IFailure;
import com.example.latte_core.net.callback.IRequest;
import com.example.latte_core.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：贪欢
 * 时间：2019/5/8
 * 描述：
 */
public class DownloadHandler {


    private final String URL;
    private static final WeakHashMap<String,Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final String NAME;
    private final String EXTENSION;
    private final String DOWNLOAD_DIR;

    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String name,
                           String extension,
                           String download_dir) {
        URL = url;
        REQUEST = request;
        SUCCESS = success;
        FAILURE = failure;
        ERROR = error;
        NAME = name;
        EXTENSION = extension;
        DOWNLOAD_DIR = download_dir;
    }


    public final void handleDownload(){

        if (REQUEST!=null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                        if (response.isSuccessful()){
                            ResponseBody responseBody = response.body();

                            SavaFileTask task = new SavaFileTask(REQUEST,SUCCESS);

                            //以线程池的方式实现
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,DOWNLOAD_DIR,EXTENSION,responseBody,NAME);

                            //这里一定要注意判断，否则文件可能下载不全
                            if (task.isCancelled()){
                                if (REQUEST != null){
                                    REQUEST.onRequestEnd();
                                }
                            }
                        }else {
                            if (ERROR != null){
                                ERROR.onError(response.code(),response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        if (FAILURE != null){
                            FAILURE.onFailure(t.getMessage());
                        }
                    }
                });

    }
}
