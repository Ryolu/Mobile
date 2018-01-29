package projectname.companyname.com.myapplication;

public class GameData {
    public final static GameData Instance = new GameData();

    private GameData (){

    }

    public boolean isStarted = false;
    public float worldTime = 0f;
    public int coin = 0;
}
