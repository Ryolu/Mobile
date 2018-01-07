package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread
{
    static final long targetFPS = 60;

    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();

        GameSystem.Instance.Init(view);
    }

    public boolean IsRunning()
    {
        // to make sure of stuff here
        return isRunning;
    }

    public void Initialize()
    {
        isRunning = true;
    }

    public void Terminate()
    {
        isRunning = false;
    }

    @Override
    public void run()
    {
        // This is for the game loop logic outside of main loop,
        // this is done to prevent an endless while loop that will crash the app

        // when there are 2 or more thread running at the same time,
        // there will be this thing called a race condition

        // init required variables here
        long framePerSecond = 1000/ targetFPS; // 1000 milliseconds is 1 second
        long startTime = 0; // this is for FRC (frame rate controller)

        long prevTime = System.nanoTime(); // this is for delta time

        while(IsRunning())
        {
            // Update
            long currTime = System.nanoTime();
            float deltaTime = (currTime - prevTime) / 1000000000.0f;
            prevTime = currTime;
            //end deltatime

            GameSystem.Instance.Update(deltaTime);

            // Render
            Canvas canvas = holder.lockCanvas();
            if (canvas != null)
            {
                synchronized (holder)
                {
                    // start doing stuff here
                    canvas.drawColor(Color.BLACK);
                    GameSystem.Instance.Render(canvas);
                }
                holder.unlockCanvasAndPost(canvas);
            }

            // Post update
            try {
                // if frames are getting too fast,
                // let the system sleep til the time for the next frame
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);

                if (sleepTime > 0)
                    sleep(sleepTime);
            }
            catch (InterruptedException e) {
                Terminate();
            }
        }
    }
}
