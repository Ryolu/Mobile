package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.SurfaceView;

public class TextEntity implements EntityBase{

    private boolean isDone = false;

    private int frameCount;
    private long lastTime = 0;
    private long lastFPSTime = 0;
    private float fps;

    private Typeface myfont;

    @Override
    public boolean IsDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        myfont = Typeface.createFromAsset(_view.getContext().getAssets(), "fonts/Gemcut.otf");
    }

    @Override
    public void Update(float _dt) {
        frameCount++;

        long currentTime = System.currentTimeMillis();

        lastTime = currentTime;

        if(currentTime - lastFPSTime > 1000)
        {
            fps = (frameCount * 1000.f) / (currentTime - lastFPSTime);
            lastFPSTime = currentTime;
            frameCount = 0;
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        Paint paint = new Paint();
        paint.setARGB(255, 0,0,0);
        paint.setStrokeWidth(200);
        paint.setTypeface(myfont);
        paint.setTextSize(70);
        _canvas.drawText("FPS: " + fps, 30, 80, paint);
    }

    @Override
    public int GetRenderLayer() {
        return RenderLayer.Instance.TextLayer;
    }

    @Override
    public void SetRenderLayer(int _renderLayer) {}

    @Override
    public boolean IsInit() {
        return true;
    }

    public static TextEntity Create()
    {
        TextEntity result = new TextEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }
}
