package cn.edu.zucc.pb.v3;

public interface IDao<T> {
    public T getEntity(String key, String clazz);
}
