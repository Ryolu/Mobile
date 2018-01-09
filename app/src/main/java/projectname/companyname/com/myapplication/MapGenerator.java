package projectname.companyname.com.myapplication;

import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceView;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MapGenerator {
    public final static MapGenerator Instance = new MapGenerator();
    private Land land = null;
    private LinkedList<Land> MapList = new LinkedList<>();
    private SurfaceView view = null;
    private boolean isInit = false;
    private int xOffset = 117;
    private int yOffset = 60;
    private int dir = 1;
    private int landNumber;

    // RNG
    private Vector3 pos;
    private Random random = new Random(SystemClock.elapsedRealtime() + SystemClock.elapsedRealtimeNanos() * SystemClock.elapsedRealtimeNanos());

    private MapGenerator () { }

    public void Init(SurfaceView _view){
        if (_view.isLaidOut())
        {
            view = _view;
            pos = new Vector3(_view.getWidth() * 0.5f, _view.getHeight() * 0.5f, 0);
            land = Land.Create();
            land.SetPos(new Vector3(pos));
            MapList.add(land);

            do {
                if (random.nextInt() % 7 == 0)
                    dir *= -1;

                if (random.nextInt() % 2 == 0 && pos.x > _view.getWidth() * 0.5f - 117 * 3 && dir == -1)
                {
                    land = Land.Create();
                    pos.x += xOffset * dir;
                    pos.y += yOffset;
                    land.SetPos(new Vector3(pos));
                    MapList.add(land);
                }
                else if (random.nextInt() % 2 == 1 && pos.x < _view.getWidth() * 0.5f + 117 * 3 && dir == 1)
                {
                    land = Land.Create();
                    pos.x += xOffset * dir;
                    pos.y += yOffset;
                    land.SetPos(new Vector3(pos));
                    MapList.add(land);
                }

            } while (MapList.getLast().GetPos().y < view.getHeight() * 2);

            isInit = true;
        }
    }

    public void Update(float _dt) {
        landNumber = 0;
        LinkedList<Land> removalList = new LinkedList<>();

        for (Land land : MapList) {

            if (land.GetPos().y < -land.GetBmp().getHeight() * 0.5f) {
                removalList.add(land);
                continue;
            }

            landNumber++;
            land.Update(_dt);
        }

        for (Land land : removalList) {
            MapList.remove(land);
        }

        if (MapList.size() < 60)
        {
            if (random.nextInt() % 7 == 0)
                dir *= -1;

            if (random.nextInt() % 2 == 0 && pos.x > view.getWidth() * 0.5f - 117 * 3 && dir == -1)
            {
                land = Land.Create();
                pos.x = MapList.getLast().GetPos().x + xOffset * dir;
                pos.y = MapList.getLast().GetPos().y + yOffset;
                land.SetPos(new Vector3(pos));
                MapList.add(land);
            }
            else if (random.nextInt() % 2 == 1 && pos.x < view.getWidth() * 0.5f + 117 * 3 && dir == 1)
            {
                land = Land.Create();
                pos.x = MapList.getLast().GetPos().x + xOffset * dir;
                pos.y = MapList.getLast().GetPos().y + yOffset;
                land.SetPos(new Vector3(pos));
                MapList.add(land);
            }
        }
    }

    public boolean IsInit() {
        return isInit;
    }
}

