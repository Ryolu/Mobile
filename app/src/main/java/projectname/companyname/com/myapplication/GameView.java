package projectname.companyname.com.myapplication;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by peebooty on 21/11/2017.
 */



class GameView extends SurfaceView {
    private SurfaceHolder holder = null;

    private UpdateThread updateThread = new UpdateThread(this);

    public GameView(Context context) {
        super(context);
        holder = getHolder();

        if (holder != null)
        {
            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    if (!updateThread.IsRunning())
                        updateThread.Initialize();

                    if (!updateThread.isAlive())
                        updateThread.start();
                }

                @Override
                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
                    //nothing to do here
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    updateThread.Terminate();
                }
            });
        }
    }
}

