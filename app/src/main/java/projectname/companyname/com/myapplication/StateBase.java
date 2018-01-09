package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by peebooty on 9/1/2018.
 */

public interface StateBase {
    String GetName();

    void OnEnter(SurfaceView _view);
    void Update(float _dt);
    void Render(Canvas _canvas);
    void OnExit();
}
