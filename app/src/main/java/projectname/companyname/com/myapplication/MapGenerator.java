package projectname.companyname.com.myapplication;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Random;

public class MapGenerator {
    public final static MapGenerator Instance = new MapGenerator();
    private Land land = null;
    private LinkedList MapList = new LinkedList();
    Date currTime = Calendar.getInstance().getTime();
    Random random = new Random(currTime.getHours() + currTime.getMinutes() + currTime.getSeconds());

    private MapGenerator () { }

    public void Init() {

    }
}

