package others.kryo_practice;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.objenesis.strategy.StdInstantiatorStrategy;

public class KryoUtil {

    private static final String TAG = KryoUtil.class.getSimpleName();
    private static KryoUtil instance = new KryoUtil();
    private ObjectPool<Kryo> pool;

    private KryoUtil() {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setMaxTotal(-1);
        config.setMaxIdle(128);
        config.setBlockWhenExhausted(false);
        pool = new GenericObjectPool<>(new KryoPooledObjectFactory(), config);
    }

    public static KryoUtil getInstance() {
        return instance;
    }

    private static Kryo createKryo() {
        Kryo kryo = new Kryo();
        kryo.setReferences(false);
        kryo.setRegistrationRequired(false);
        kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
        return kryo;
    }

    public <T> byte[] serialize(T t, Class<T> clazz) {
        Kryo kryo = null;
        try {
            kryo = pool.borrowObject();
            return serialize(kryo, t, clazz);
        } catch (Exception e) {
            e.printStackTrace();
//            LogUtil.getWebInfoLogger().infoWithTag(TAG,
//                    "pool active: " + pool.getNumActive() + ", pool idle: " + pool.getNumIdle() +
//                            ", Borrow failed", e);
            Kryo localKryo = createKryo();
            return serialize(localKryo, t, clazz);
        } finally {
            try {
                pool.returnObject(kryo);
            } catch (Exception ignored) {
            }
        }
    }

    private <T> byte[] serialize(Kryo kryo, T t, Class<T> clazz) {
        kryo.register(clazz);
        byte[] buffer = new byte[2048];
        try (Output output = new Output(buffer)) {
            kryo.writeObject(output, t);
            return output.toBytes();
        }
    }

    public <T> T deserialize(byte[] src, Class<T> clazz) {
        Kryo kryo = null;
        try {
            kryo = pool.borrowObject();
            return deserialize(kryo, src, clazz);
        } catch (Exception e) {
            e.printStackTrace();
//            LogUtil.getWebInfoLogger().infoWithTag(TAG,
//                    "pool active: " + pool.getNumActive() + ", pool idle: " + pool.getNumIdle() +
//                            ", Borrow failed", e);
            Kryo localKryo = createKryo();
            return deserialize(localKryo, src, clazz);
        } finally {
            try {
                pool.returnObject(kryo);
            } catch (Exception ignored) {
            }
        }
    }

    private <T> T deserialize(Kryo kryo, byte[] src, Class<T> clazz) {
        kryo.register(clazz);
        try (Input input = new Input(src)) {
            return kryo.readObject(input, clazz);
        }
    }

    private static class KryoPooledObjectFactory extends BasePooledObjectFactory<Kryo> {
        @Override
        public Kryo create() {
            return createKryo();
        }

        @Override
        public PooledObject<Kryo> wrap(Kryo kryo) {
            return new DefaultPooledObject<>(kryo);
        }

        @Override
        public void passivateObject(PooledObject<Kryo> pooledObject) {
            pooledObject.getObject().reset();
        }
    }
}
