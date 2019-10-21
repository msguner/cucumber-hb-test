package utils;

public class DataStoreFactory {
    private static ThreadLocal<DataStore> suiteDataStore = new InheritableThreadLocal<DataStore>() {
        @Override
        protected DataStore initialValue() {
            return new DataStore();
        }
    };
    private static ThreadLocal<DataStore> featureDataStore = new InheritableThreadLocal<DataStore>() {
        @Override
        protected DataStore initialValue() {
            return new DataStore();
        }
    };
    private static ThreadLocal<DataStore> scenarioDataStore = new InheritableThreadLocal<DataStore>() {
        @Override
        protected DataStore initialValue() {
            return new DataStore();
        }
    };

    /**
     * @return The current instance of the SuiteDataStore
     */
    public static DataStore getSuiteDataStore() {
        return suiteDataStore.get();
    }

    /**
     * @return The current instance of the FeatureDataStore
     */
    public static DataStore getFeatureDataStore() {
        return featureDataStore.get();
    }

    /**
     * @return The current instance of the ScenarioDataStore
     */
    public static DataStore getScenarioDataStore() {
        return scenarioDataStore.get();
    }

}
