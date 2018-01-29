package projectname.companyname.com.myapplication;

public class Vector3 {
    float x;
    float y;
    float z;

    Vector3() {
        x = 0;
        y = 0;
        z = 0;
    }

    Vector3(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }

    Vector3(Vector3 _vector) {
        x = _vector.x;
        y = _vector.y;
        z = _vector.z;
    }

    public void SetZero() {
        x = 0;
        y = 0;
        z = 0;
    }

    public void Set(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
    }
}
