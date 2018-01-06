package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Random;

public class Obstacle implements EntityBase, Collidable {

    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private Vector3 pos;

    @Override
    public String GetType() {
        return "Obstacle";
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
        return 0;
    }

    @Override
    public void OnHit(Collidable _other) {
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void SetIsDone(boolean _isDone) {
        isDone = _isDone;
    }

    @Override
    public void Init(SurfaceView _view) {
        bmp = BitmapFactory.decodeResource(_view.getResources(), R.drawable.dustbin);
    }

    @Override
    public void Update(float _dt) {

    }

    @Override
    public void Render(Canvas _canvas) {
        Random ranGen = new Random();

        _canvas.drawBitmap(bmp, ranGen.nextInt(), ranGen.nextInt(), null);
    }

    @Override
    public boolean IsInit() {
        return view != null && bmp != null;
    }
}

