package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.SurfaceView;

public class SampleBackground implements EntityBase
{

    private Bitmap bmp = null;
    private Bitmap scaledbmp = null;
    private float yOffset;
    private boolean isDone = false;
    private SurfaceView view = null;
    private float xPos, yPos;
    private boolean isInit;
    private int screenWidth, screenHeight;

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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.background);

        view = _view;
        yOffset = 0.0f;
        isInit = true;

        DisplayMetrics metrics = view.getResources().getDisplayMetrics();
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;
        scaledbmp = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
    }

    @Override
    public void Update(float _dt) {
        yOffset -= 5f;

        if (yOffset <= -scaledbmp.getHeight())
            yOffset = 0;
    }

    @Override
    public void Render(Canvas _canvas) {

        xPos = 0.5f * bmp.getWidth();
        yPos = 0.5f * bmp.getHeight();

        Matrix transform = new Matrix();
        //transform.postScale(view.getWidth() / bmp.getWidth(), view.getHeight() / bmp.getHeight());
        transform.postTranslate(0, yOffset);
        _canvas.drawBitmap(scaledbmp, transform, null);
        transform.postTranslate(0, view.getHeight());
        _canvas.drawBitmap(scaledbmp, transform, null);
        //_canvas.drawBitmap(bmp, 0, view.getHeight() + yOffset, null);
    }

    @Override
    public int GetRenderLayer() {
        return RenderLayer.Instance.BGLayer;
    }

    @Override
    public void SetRenderLayer(int _renderLayer) {

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    public static SampleBackground Create ()
    {
        SampleBackground result = new SampleBackground();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    public void SetOffset(float _offset)
    {
        yOffset = _offset;
    }

    public float GetHeight() {
        return bmp.getHeight();
    }

    public float GetPosY() {return yPos;}
}

