package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

public class Coin implements Collidable, EntityBase {

    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private Vector3 pos = new Vector3();
    private Vector3 dir = new Vector3();
    private boolean isInit = false;
    private int renderLayer = 0;

    @Override
    public String GetType() {
        return "Coin";
    }

    @Override
    public float GetPosX() {
        return pos.x;
    }

    @Override
    public float GetPosY() {
        return pos.y;
    }

    @Override
    public float GetRadius() {
        return (float)(bmp.getHeight() * 0.5);
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Player")
        {
            GameData.Instance.coin++;
            SetIsDone(true);
        }

    }

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
        if (_view != null)
        {
            isInit = true;
            view = _view;
            bmp = ResourceManager.Instance.GetBitmap(R.drawable.pepsi_can);
            SetRenderLayer(RenderLayer.Instance.GroundLayerFront);
        }
    }

    @Override
    public void Update(float _dt) {
        if (GameData.Instance.isStarted)
            pos.y -= 5f;
        if (pos.z != renderLayer)
            pos.z = renderLayer;
    }

    @Override
    public void Render(Canvas _canvas) {
        Matrix transform = new Matrix();
        transform.postTranslate(-bmp.getWidth() * 0.5f, -bmp.getHeight() * 0.5f);
        transform.postScale(1.75f, 1.75f);
        transform.postTranslate(pos.x, pos.y);

        _canvas.drawBitmap(bmp, transform, null);
    }

    @Override
    public int GetRenderLayer() {
        return RenderLayer.Instance.GroundLayerFront;
    }

    @Override
    public void SetRenderLayer(int _renderLayer) {
        renderLayer = _renderLayer;
    }

    @Override
    public Vector3 GetPos() {
        return pos;
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }
}
