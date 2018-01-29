package projectname.companyname.com.myapplication;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.view.SurfaceView;

import java.util.HashMap;

public class AudioManager {
    public final static AudioManager Instance = new AudioManager();

    private Resources res = null;
    private SurfaceView view = null;
    private boolean isInit = false;
    private HashMap<Integer, MediaPlayer> audioMap = new HashMap<Integer, MediaPlayer>();

    private AudioManager()
    {

    }

    public void Init(SurfaceView _view)
    {
        view = _view;
        res = _view.getResources();

        if (_view != null)
            isInit = true;
    }

    public boolean IsInit()
    {
        return isInit;
    }

    public void PlayAudio(int _id)
    {
        if (audioMap.containsKey(_id))
        {
            audioMap.get(_id).reset();
            audioMap.get(_id).start();
        }

        // Load the audio
        MediaPlayer newAudio = MediaPlayer.create(view.getContext(), _id);
        audioMap.put(_id, newAudio);
        newAudio.start();
    }

    public boolean IsPlaying(int _id)
    {
        if (!audioMap.containsKey(_id))
            return false;

        return audioMap.get(_id).isPlaying();
    }
}

