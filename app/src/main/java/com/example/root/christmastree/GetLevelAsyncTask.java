package com.example.root.christmastree;

import android.os.AsyncTask;

import com.example.root.christmastree.retrofit.ApiClientFactory;
import com.example.root.christmastree.retrofit.TreeApiClient;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by root on 12/15/17.
 */

public class GetLevelAsyncTask extends AsyncTask<Void,Void,Integer> {

    private OnFrameReceivedListener listener;
    private TreeApiClient client;

    public GetLevelAsyncTask(OnFrameReceivedListener listener,String http,String port) {
        this.listener = listener;
        client = new ApiClientFactory().create(http,port);

    }

    @Override
    protected Integer doInBackground(Void... voids) {
        try {
            Frame response = getFrame();
            if(response!=null){
                return response.getLedLevel();

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;    }

    @Override
    protected void onPostExecute(Integer level) {
        listener.onFrameReceived(level);
    }

    private Frame getFrame() throws IOException {
        Call<Frame> call = client.getFrame();
        Response<Frame> response = call.execute();
        if(response.isSuccessful()) { //http 200+
            Frame getFrameResponse = response.body();
            return getFrameResponse;
        }
        return null;

    }

    public interface OnFrameReceivedListener{
        void onFrameReceived(Integer level);

    }

}

