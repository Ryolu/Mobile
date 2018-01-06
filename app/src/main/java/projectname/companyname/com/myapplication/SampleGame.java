package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

public class SampleGame
{
    public final static SampleGame Instance = new SampleGame();

    private SampleGame()
    {
    }

    public void Init(SurfaceView _view)
    {
        ResourceManager.Instance.Init(_view);
        EntityManager.Instance.Init(_view);
        SampleBackground.Create();
        Player.Instance.Create();
    }

    public void Update(float _deltaTime)
    {
        EntityManager.Instance.Update(_deltaTime);
    }

    public void Render(Canvas _canvas)
    {
        EntityManager.Instance.Render(_canvas);
    }
}

