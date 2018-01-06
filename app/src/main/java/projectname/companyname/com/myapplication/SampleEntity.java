package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class SampleEntity implements EntityBase, Collidable {

    private Bitmap bmp = null;
    private boolean isDone = false;
    private float xPos, yPos, xDir, yDir, lifeTime;
    boolean isInit;

    @Override
    public boolean isDone() {
        return isDone;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.dustbin);

        lifeTime = 5.0f;
        Random ranGen = new Random();

        xPos = ranGen.nextFloat() * _view.getWidth();
        yPos = ranGen.nextFloat() * _view.getHeight();

        xDir = ranGen.nextFloat() * 100.f - 50.f;
        yDir = ranGen.nextFloat() * 100.f - 50.f;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
        lifeTime -= _dt;

        if(lifeTime < 0.0f)
            SetIsDone(true);

        xPos += xDir * _dt;
        yPos += yDir * _dt;

        // if user touches object, remove object
        if (TouchManager.Instance.IsDown())
        {
            // check collision here
            float imgRadius = bmp.getHeight() * 0.5f;
            if (Collision.SphereToSPhere(TouchManager.Instance.GetPosX(), TouchManager.Instance.GetPosY(), 0.0f, xPos, yPos, imgRadius))
            {
                SetIsDone(true);
            }
        }
    }

    @Override
    public void Render(Canvas _canvas) {
        _canvas.drawBitmap(bmp, xPos - bmp.getWidth() * 0.5f, yPos - bmp.getHeight() * 0.5f, null);
    }

    public static SampleEntity Create() {
        SampleEntity result = new SampleEntity();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    @Override
    public String GetType() {
        return "SampleEntity";
    }

    @Override
    public float GetPosX() {
        return xPos;
    }

    @Override
    public float GetPosY() {
        return yPos;
    }

    @Override
    public float GetRadius() {
        return bmp.getHeight() * 0.5f;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "SampleEntity")
            SetIsDone(true);
    }

    @Override
    public boolean IsInit() {
        return isInit;
    }
}

