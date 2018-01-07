package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.SystemClock;
import android.view.SurfaceView;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class MapGenerator {
    public final static MapGenerator Instance = new MapGenerator();
    private Land land = null;
    private LinkedList<Land> MapList = new LinkedList<>();
    private SurfaceView view = null;
    private boolean isInit = false;
    private Vector3 leftoffset = new Vector3(-117, 60, 0);
    private Vector3 rightoffset = new Vector3(117, 60, 0);

    // RNG
    private Vector3 pos;
    private Random random = new Random(SystemClock.elapsedRealtime() + SystemClock.elapsedRealtimeNanos() * SystemClock.elapsedRealtimeNanos());

    private MapGenerator () { }

    public void Init(SurfaceView _view) {
        if (_view.isLaidOut())
        {
            view = _view;
            land = Land.Create();
            land.SetPos(pos);
            MapList.add(land);

            for (int i = 0; i < 14; i++)
            {
                pos = new Vector3(_view.getWidth() * 0.5f, _view.getHeight() * 0.5f, 0);
                land = Land.Create();
                if (random.nextInt(Math.abs((int)SystemClock.elapsedRealtime())) % 2 == 0 && pos.x > _view.getWidth() * 0.5f - 117 * 3)
                {
                    pos.x += leftoffset.x;
                    pos.y += leftoffset.y;
                    land.SetPos(new Vector3(pos));
                    MapList.add(land);
                }
                else if (random.nextInt(Math.abs((int)SystemClock.elapsedRealtime())) % 2 == 1 && pos.x < _view.getWidth() * 0.5f + 117 * 3)
                {
                    pos.x += rightoffset.x;
                    pos.y += rightoffset.y;
                    land.SetPos(new Vector3(pos));
                    MapList.add(land);
                }
            }
            isInit = true;
        }
    }

    public void Update(float _dt) {
        for (Land land : MapList) {
            land.Update(_dt);
        }
    }

    public void Add() {

    }

    public void Remove() {

    }

    public void Render(Canvas _canvas)
    {
        for (Land land : MapList)
        {
            land.Render(_canvas);
        }
    }

    public boolean IsInit() {
        return isInit;
    }
}

