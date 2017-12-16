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

public class SetLevelAsyncTask extends AsyncTask<Frame,Void,SendFrameResponse> {

    private OnSetLevelListener listener;
    private TreeApiClient client;

    public SetLevelAsyncTask(OnSetLevelListener listener,String http,String port) {
        this.listener = listener;
        client = new ApiClientFactory().create(http,port);

    }


    @Override
    protected SendFrameResponse doInBackground(Frame... frames) {
        try {
            SendFrameResponse response = setLevel(frames[0]);
            if(response!=null){
                return response;

            }
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;       }

    @Override
    protected void onPostExecute(SendFrameResponse response) {
        listener.onLevelSet(response);
    }

    private SendFrameResponse setLevel(Frame frame) throws IOException {
        Call<SendFrameResponse> call = client.sendFrame(frame);
        Response<SendFrameResponse> response = call.execute();
        if(response.isSuccessful()) { //http 200+
            SendFrameResponse sendFrameResponse = response.body();
            return sendFrameResponse;
        }
        return null;
    }

    public interface OnSetLevelListener{
        void onLevelSet(SendFrameResponse response);
    }

}
