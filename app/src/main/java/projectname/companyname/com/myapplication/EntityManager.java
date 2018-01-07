package projectname.companyname.com.myapplication;

import android.graphics.Canvas;
import android.view.SurfaceView;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class EntityManager {
    public final static EntityManager Instance = new EntityManager();
    private LinkedList<EntityBase> entityList = new LinkedList<EntityBase>();
    private SurfaceView view  = null;

    private EntityManager()  {

    }

    public void Init(SurfaceView _view) {
        view = _view;
    }

    public void Update(float _dt) {
        LinkedList<EntityBase> removalList = new LinkedList<EntityBase>();

        for (EntityBase currEntity : entityList)
        {
            if (!currEntity.IsInit())
                currEntity.Init(view);

            currEntity.Update(_dt);

            if (currEntity.IsDone())
            {
                // if it is done, add to removal list
                removalList.add(currEntity);
            }
        }

        //Collision check
        for (int i = 0; i < entityList.size(); ++i)
        {
            EntityBase currEntity = entityList.get(i);

            if (currEntity instanceof Collidable)
            {
                Collidable first = (Collidable) currEntity;
                for (int j = i + 1; j < entityList.size(); ++j)
                {
                    EntityBase otherEntity = entityList.get(j);

                    if (otherEntity instanceof Collidable)
                    {
                        Collidable second = (Collidable) otherEntity;

                        if (Collision.SphereToSPhere(first.GetPosX(), first.GetPosY(), first.GetRadius(), second.GetPosX(), second.GetPosY(), second.GetRadius()))
                        {
                            first.OnHit(second);
                            second.OnHit(first);
                        }
                    }
                }
            }

            if (currEntity.IsDone())
            {
                removalList.add(currEntity);
            }
        }

        for (EntityBase currEntity : removalList)
        {
            entityList.remove(currEntity);
        }
        removalList.clear();
    }

    public void Render(Canvas _canvas) {
        // sort the entities by render layer
        Collections.sort(entityList, new Comparator<EntityBase>() {
            @Override
            public int compare(EntityBase o1, EntityBase o2) {
                return Integer.compare(o1.GetRenderLayer(), o2.GetRenderLayer());
            }
        });
        // All entities rendered here
        for (EntityBase currEntity : entityList)
        {
            currEntity.Render(_canvas);
        }
    }

    public void AddEntity(EntityBase _newEntity) {
        entityList.add(_newEntity);
    }
}
