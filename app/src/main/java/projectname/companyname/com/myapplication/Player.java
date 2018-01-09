package projectname.companyname.com.myapplication;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.text.style.UpdateAppearance;
import android.util.Log;
import android.view.SurfaceView;

import java.util.Random;

import static java.lang.Math.abs;

public class Player implements EntityBase, Collidable {

    public final static Player Instance = new Player();
    private Player()
    {
    }

    private Bitmap bmp = null;
    private boolean isDone = false;
    private SurfaceView view = null;
    private Vector3 pos = new Vector3();
    private Vector3 dir = new Vector3();
    private boolean isInit = false;
    private boolean jumping = false;
    private float defaultJumpSpeed = -750;
    private float jumpSpeed = -750;
    private float gravity = 1000;
    private float renderLayer = RenderLayer.Instance.PlayerLayer;


    //private float xPos, yPos, xDir, yDir;

    Sprite Character;

    @Override
    public String GetType() {
        return "Player";
    }

    @Override
    public float GetPosX() {
        return pos.x;
    }

    @Override
    public float GetPosY() {
        return pos.y;
    }

    public Vector3 GetPos() {
        return pos;
    }

    @Override
    public float GetRadius() {
        return 0;
    }

    @Override
    public void OnHit(Collidable _other) {
        if (_other.GetType() == "Obstacle")
            SetIsDone(true);

        else if (_other.GetType() == "Obstacle") {
            // TODO: add different things to hit
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
        bmp = ResourceManager.Instance.GetBitmap(R.drawable.player_left);
        Character = new Sprite(bmp, 1, 5, 5);
        Character.SetAnimationFrames(0, 4);

        //xPos = Resources.getSystem().getDisplayMetrics().widthPixels * 0.5f;
        //yPos = Resources.getSystem().getDisplayMetrics().heightPixels * 0.5f;

        pos.x = 0.5f * _view.getWidth();
        pos.y = 0.5f * _view.getHeight();
        view = _view;
        isInit = true;
    }

    @Override
    public void Update(float _dt) {
       //pos.x += dir.x * _dt;

        if (TouchManager.Instance.IsUp()) {
            switch (TouchManager.Instance.IsSwipe())
            {
                case 0:
                    dir.x = -350f;
                    Log.d("Player", "left");
                    TouchManager.Instance.ResetState();
                    break;

                case 1:
                    dir.x = 350f;
                    Log.d("Player", "right");
                    TouchManager.Instance.ResetState();
                    break;

                case 2:
                    SetJump(true);
                    Log.d("Player", "jump");
                    TouchManager.Instance.ResetState();
                    break;
            }
        }

        UpdateJump(_dt);

        if (GameData.Instance.isStarted)
            Character.Update(_dt);
    }

    @Override
    public void Render(Canvas _canvas) {
        //Matrix transform = new Matrix();
        //transform.postTranslate(-bmp.getWidth() * 0.5f, -bmp.getHeight() * 0.5f);
        //transform.postScale(2, 2);
        //transform.postTranslate(pos.x, pos.y);
        //_canvas.drawBitmap(bmp, transform, null);

        Character.Render(_canvas, (int)pos.x, (int)pos.y, 2);
    }

    @Override
    public int GetRenderLayer() {
        return RenderLayer.Instance.PlayerLayer;
    }

    @Override
    public void SetRenderLayer(int _renderLayer) {

    }

    @Override
    public boolean IsInit() {
        return isInit;
    }

    public static Player Create() {
        Player result = new Player();
        EntityManager.Instance.AddEntity(result);
        return result;
    }

    public boolean GetJump() {return jumping;}

    private void SetJump(boolean jump) {jumping = jump;}

    private void UpdateJump(float _dt)
    {
        if (GetJump() == false)
            return;

        pos.y += (float)(jumpSpeed * _dt + 0.5 * gravity * _dt * _dt);
        jumpSpeed += gravity * _dt;

        if (pos.y > 0.5f * view.getHeight())
        {
            jumpSpeed = defaultJumpSpeed;
            pos.y = 0.5f * view.getHeight();
            SetJump(false);
        }
    }
}

