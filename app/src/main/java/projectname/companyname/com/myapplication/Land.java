package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.view.SurfaceView;

public class Land implements EntityBase{
    private Vector3 pos;
    private Bitmap bmp = null;
    private boolean isDone = false;
    private boolean isInit = false;
    private boolean isFront = false;
    private int renderLayer = RenderLayer.Instance.GroundLayerBack;
    private SurfaceView view = null;

    Land() {
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.gametile);
    }

    public void SetPos(Vector3 _pos)
    {
        pos = _pos;
    }

    public Vector3 GetPos() {
        return pos;
    }

    public static Land Create() {
        Land result = new Land();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    public boolean IsFront() {
        return isFront;
    }

    public Bitmap GetBmp() {return bmp;}

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
        view = _view;
        isInit = true;
    }

    public void Update(float _dt)
    {
        if (GameData.Instance.isStarted)
            pos.y -= 5f;
        if (pos.z != renderLayer)
            pos.z = renderLayer;
    }

    @Override
    public void Render(Canvas _canvas) {
        Matrix transform = new Matrix();
        transform.postTranslate(-bmp.getWidth() * 0.5f, -bmp.getHeight() * 0.5f);
        transform.postScale(0.2f, 0.2f);
        transform.postTranslate(pos.x, pos.y);

        _canvas.drawBitmap(bmp, transform, null);
    }

    @Override
    public int GetRenderLayer() {
        return renderLayer;
    }

    @Override
    public void SetRenderLayer(int _renderLayer) {
        if (_renderLayer == 1)
            renderLayer = RenderLayer.Instance.GroundLayerFront;
        else if (_renderLayer == -1)
            renderLayer = RenderLayer.Instance.GroundLayerBack;
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }
}

