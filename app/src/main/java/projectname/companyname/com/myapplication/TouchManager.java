package projectname.companyname.com.myapplication;

import android.view.MotionEvent;

public class TouchManager {
    public final static TouchManager Instance = new TouchManager();

    private TouchManager () {

    }

    public enum TouchState
    {
        NONE,
        DOWN,
        UP,
        MOVE
    }

    private TouchState status = TouchState.NONE;
    private Vector3 oldPos = new Vector3();
    private Vector3 newPos = new Vector3();

    public boolean HasTouch ()
    {
        return status == TouchState.DOWN || status == TouchState.MOVE;
    }

    public boolean IsDown()
    {
        return status == TouchState.DOWN;
    }

    public boolean IsMove()
    {
        return status == TouchState.MOVE;
    }

    public boolean IsUp() {return status == TouchState.UP; }

    public int IsSwipe() {
        if (Math.abs(oldPos.y - newPos.y) > Math.abs(oldPos.x - newPos.x))
        {
            // swipe up
            if (oldPos.y > newPos.y)
                return 2;

            //swipe down
            else
                return 3;
        }
        else
        {
            // swipe left
            if (oldPos.x > newPos.x)
                return 0;
            // swipe right
            else
                return 1;
        }
    }

    public int GetPosX ()
    {
        return (int)newPos.x;
    }

    public int GetPosY ()
    {
        return (int)newPos.y;
    }

    public void Update(MotionEvent _event)
    {
        switch (_event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                status = TouchState.DOWN;
                oldPos.x = _event.getX();
                oldPos.y = _event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                status = TouchState.MOVE;
                break;
            case MotionEvent.ACTION_UP:
                status = TouchState.UP;
                newPos.x = _event.getX();
                newPos.y = _event.getY();
                break;
        }
    }

    public void ResetState()
    {
        status = TouchState.NONE;
        oldPos.SetZero();
        newPos.SetZero();
    }
}

