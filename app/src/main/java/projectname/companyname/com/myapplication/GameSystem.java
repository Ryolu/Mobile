package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.SurfaceView;

import static android.content.ContentValues.TAG;

public class GameSystem
{
    public final static GameSystem Instance = new GameSystem();
    private SurfaceView view = null;
    public Vibrator m_vibrator;

    private GameSystem()
    {
    }

    public void Init(SurfaceView _view){
        view = _view;
        m_vibrator = (Vibrator)_view.getContext().getSystemService(_view.getContext().VIBRATOR_SERVICE);
        ResourceManager.Instance.Init(_view);
        EntityManager.Instance.Init(_view);
        SampleBackground.Create();
        Player.Instance.Create();
        MapGenerator.Instance.Init(_view);
        TextEntity.Create();
    }

    public void Update(float _deltaTime){
        EntityManager.Instance.Update(_deltaTime);
        if (!MapGenerator.Instance.IsInit())
        {
            MapGenerator.Instance.Init(view);
            return;
        }
        MapGenerator.Instance.Update(_deltaTime);

        if (!GameData.Instance.isStarted && TouchManager.Instance.IsDown())
        {
            GameData.Instance.isStarted = true;
            startVibrate();
        }

        GameData.Instance.worldTime += _deltaTime;

        Log.d("Time: ", Float.toString(GameData.Instance.worldTime) + "s");
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }

    public void startVibrate() {
        long pattern[] = {0, 50, 0};

        if (Build.VERSION.SDK_INT >= 26) {
            m_vibrator.vibrate(VibrationEffect.createOneShot(150, 10));
        } else {
            m_vibrator.vibrate(pattern, 1);
            Log.v(TAG, "vibration test");
        }
    }

    public void stopVibrate() {
        m_vibrator.cancel();
    }
}

