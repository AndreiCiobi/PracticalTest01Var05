package ro.pub.cs.systems.eim.practicaltest01var05;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.os.Process;

import java.util.Date;

public class ProcessingThread extends Thread {
    private boolean isRunning = true;

    private Context context;

    private String msg;

    public ProcessingThread(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    @Override
    public void run() {
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has started! PID: " + Process.myPid() + " TID: " + Process.myTid());
        while (isRunning) {
            sendMessage();
            sleep();
        }
        Log.d(Constants.PROCESSING_THREAD_TAG, "Thread has stopped!");
    }

    private void sendMessage() {
        Intent intent = new Intent();
        intent.setAction(Constants.ACTION_STRING);
        intent.putExtra(Constants.BROADCAST_RECEIVER_EXTRA,
                new Date(System.currentTimeMillis()) + " " + msg);
        context.sendBroadcast(intent);
    }

    private void sleep() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        }
    }

    public void stopThread() {
        isRunning = false;
    }
}
