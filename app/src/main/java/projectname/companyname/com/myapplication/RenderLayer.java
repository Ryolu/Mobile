package projectname.companyname.com.myapplication;

public class RenderLayer {
    public final static RenderLayer Instance = new RenderLayer();
    public int BGLayer = -1000;
    public int TextLayer = -999;
    public int PlayerLayer = 0;
    public int GroundLayerFront = 1;
    public int GroundLayerBack = -1;

    private RenderLayer() {}
}

