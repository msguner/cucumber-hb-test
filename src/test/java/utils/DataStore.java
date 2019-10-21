package utils;

import org.apache.log4j.Logger;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DataStore {

    private Logger LOGGER = Logger.getLogger(DataStore.class);
    private HashMap<Object, Object> map = new HashMap<Object, Object>();

    public DataStore() {
        LOGGER.info("DataStore created...");
    }

    /**
     * @param key   - Key of the data entry
     * @param value - value of the Data entry
     */
    public void put(Object key, Object value) {
        try {
            map.put(key, value);
            LOGGER.info("Data added to DataStore | Key : " + key + "  <- Value : " + value);
        } catch (Exception e) {
            Assert.fail("Data can not add to DataStore | Key : " + key + "  <- Value : " + value);
        }
    }

    /**
     * @param key - Key of the data entry to remove
     * @return The value of the entry removed. Null if no entry.
     */
    public Object remove(Object key) {
        return map.remove(key);
    }

    /**
     * @param key - Key of the data entry whose value is needed
     * @return The value corresponding to the key. null if there is no value stored
     */
    public Object get(Object key) {
        return map.get(key);
    }

    void clear() {
        map.clear();
    }

    /**
     * @param key - Key of the data entry whose value is needed
     * @return boolean
     */
    public boolean checkKeyValue(Object key) {
        return get(key) != null;
    }

    public Set<Map.Entry<Object, Object>> entrySet() {
        return map.entrySet();
    }

}
