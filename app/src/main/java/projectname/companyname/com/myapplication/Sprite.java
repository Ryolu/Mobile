package projectname.companyname.com.myapplication;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    int row = 0;
    int col = 0;
    int height = 0;
    int width = 0;

    private Bitmap bmp = null;

    private int currentFrame = 0;
    private int startFrame = 0;
    private int endFrame = 0;

    private float timePerFrame = 0f;
    private float timeAcc = 0f;

    public Sprite(Bitmap _bmp, int _row, int _col, int _fps) {
        bmp = _bmp;
        row = _row;
        col = _col;

        width = bmp.getWidth() / _col;
        height = bmp.getHeight() / _row;

        timePerFrame = 1f / (float) _fps;

        endFrame = _col * _row;
    }

    public void Update(float dt) {
        timeAcc += dt;
        if(timeAcc > timePerFrame) {
            currentFrame++;
            if(currentFrame >= endFrame) {
                currentFrame = startFrame;
            }
            timeAcc = 0;
        }
    }

    public void Render(Canvas _canvas, int _x, int _y) {
        int frameX = currentFrame % col;
        int frameY = currentFrame / col;
        int srcX = frameX * width;
        int srcY = frameY * height;

        _x -= 0.5f * width;
        _y -= 0.5f * height;

        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(_x, _y, _x + width, _y + height);
        _canvas.drawBitmap(bmp, src, dst, null);
    }

    public void SetAnimationFrames(int _start, int _end){
        timeAcc = 0.0f;
        currentFrame = _start;
        startFrame = _start;
        endFrame = _end;
    }

    public int GetHeight() {
        return height;
    }

    public int GetWidth() {
        return width;
    }
}
