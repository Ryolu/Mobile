package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

public interface EntityBase {
    boolean IsDone();
    void SetIsDone(boolean _isDone);

    void Init(SurfaceView _view);
    void Update(float _dt);
    void Render(Canvas _canvas);

    int GetRenderLayer();
    void SetRenderLayer(int _renderLayer);
    Vector3 GetPos();

    boolean IsInit();
}

