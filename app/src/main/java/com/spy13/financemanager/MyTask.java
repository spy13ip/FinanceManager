package com.spy13.financemanager;

import android.os.AsyncTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public abstract class MyTask<Param, Progress, Result> {
    private TaskInternal taskInternal;

    protected MyTask() {
        taskInternal = new TaskInternal();
    }

    public final void run(Param param) {
        taskInternal.execute(param);
    }

    public final void run(Executor executor, Param param) {
        taskInternal.executeOnExecutor(executor, param);
    }

    public final boolean isCancelled() {
        return taskInternal.isCancelled();
    }

    public final void cancel(boolean mayInterruptIfRunning) {
        taskInternal.cancel(mayInterruptIfRunning);
    }

    public final AsyncTask.Status getStatus() {
        return taskInternal.getStatus();
    }

    public final Result get() throws ExecutionException, InterruptedException {
        return taskInternal.get();
    }

    public final Result get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return taskInternal.get(timeout, unit);
    }

    protected void onBefore() {
    }

    protected abstract Result onBody(Param param);

    protected void onAfter(Result result, boolean isCanceled) {
    }

    protected void onProgress(Progress progress) {
    }

    protected final void progress(Progress progress) {
        taskInternal.progressInternal(progress);
    }

    private class TaskInternal extends AsyncTask<Param, Progress, Result> {
        @Override
        protected void onPreExecute() {
            onBefore();
        }

        @Override
        protected Result doInBackground(Param... params) {
            return onBody(params[0]);
        }

        @Override
        protected void onProgressUpdate(Progress... values) {
            onProgress(values[0]);
        }

        @Override
        protected void onPostExecute(Result result) {
            onAfter(result, false);
        }

        @Override
        protected void onCancelled() {
            onAfter(null, true);
        }

        public void progressInternal(Progress progress) {
            progress(progress);
        }
    }
}
