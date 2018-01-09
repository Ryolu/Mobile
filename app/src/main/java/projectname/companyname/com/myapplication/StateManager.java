package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.HashMap;

public class StateManager {

    public final static StateManager Instance = new StateManager();
    public HashMap<String, StateBase> StateMap = new HashMap<>();
    private SurfaceView view = null;
    public StateBase currState;
    public StateBase nextState;

    private StateManager () {

    }

    void Init(SurfaceView _view) {
        view = _view;
    }

    void Update(float _dt) {

        if (currState != nextState)
        {
            currState.OnExit();
            nextState.OnEnter(view);
            currState = nextState;
        }

        if (currState == null)
            return;

        currState.Update(_dt);
    }

    void Render(Canvas _canvas) {
        currState.Render(_canvas);
    }

    void AddState(StateBase _newState) {
        StateMap.put(_newState.GetName(), _newState);
    }

    void ChangeState(StateBase _newState) {

    }

    void ChangeState(String _string) {
        nextState = StateMap.get(_string);

        if (nextState == null)
        {
            nextState = currState;
        }
    }

    void Start(String _curr) {
        if (currState != null || nextState != null) {
            return;
        }

        if (_curr == null)
            return;

        currState = StateMap.get(_curr);

        if (currState != null) {
            currState.OnEnter(view);
            nextState = currState;
        }
    }
}


