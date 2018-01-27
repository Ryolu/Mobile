package projectname.companyname.com.myapplication;


import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.SurfaceView;

public class Pause implements EntityBase{
    private boolean isDone = false;
    private Bitmap bmp = null;
    private int xPos, yPos;
    private boolean isInit = false;
    private int renderLayer = 0;

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
        bmp = ResourceManager.Instance.GetBitmap(R.mipmap.ic_launcher_round);
        xPos = 100;
        yPos = 100;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        if (TouchManager.Instance.IsDown())
        {
            // Check touch collision here
            float imgRadius = bmp.getHeight() * 0.5f;
            //if (Collision.SphereToSphere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos , imgRadius))
            //{
            //    // Button got clicked
            //    GameSystem.Instance.SetIsPaused(!GameSystem.Instance.GetIsPaused());
            //    //AudioManager.Instance.PlayAudio(R.raw.clicksound);
            //}
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _newLayer) {
        renderLayer = _newLayer;
    }

    public static Pause Create(int _layer)
    {
        Pause result = new Pause();
        EntityManager.Instance.AddEntity(result);
        result.SetRenderLayer(_layer);
        return result;
    }

}

