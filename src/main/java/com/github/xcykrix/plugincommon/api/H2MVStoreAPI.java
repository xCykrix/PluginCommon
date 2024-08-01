package com.github.xcykrix.plugincommon.api;

import com.github.xcykrix.plugincommon.PluginCommon;
import com.github.xcykrix.plugincommon.extendables.SimpleAPI;
import com.github.xcykrix.plugincommon.extendables.implement.Initialize;
import com.github.xcykrix.plugincommon.extendables.implement.Shutdown;
import org.h2.mvstore.MVStore;

public class H2MVStoreAPI extends SimpleAPI implements Initialize, Shutdown {
    private MVStore store;

    /**
     * Creates an Access API for the H2MVStoreAPI.
     *
     * @param pluginCommon PluginCommon API.
     *
     *                     <br><br>
     *                     <a href="https://www.h2database.com/html/mvstore.html#overview">Access the H2 MVStore Documentation</a>.
     */
    public H2MVStoreAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
    }

    /**
     * Initialize the H2MVStoreAPI
     */
    @Override
    public void initialize() {
        this.pluginCommon.getDataFolder().mkdirs();
        this.store = new MVStore.Builder()
                .fileName(this.pluginCommon.getDataFolder() + "/" + "h2store")
                .open();
    }

    /**
     * Shutdown the H2MVStoreAPI
     */
    @Override
    public void shutdown() {
        this.store.close();
        this.store = null;
    }

    /**
     * Access the H2MVStore MVStore API.
     *
     * @return {@link MVStore}
     */
    @SuppressWarnings({"unused", "This is a implementation-optional API."})
    public MVStore getStore() {
        if (this.store == null)
            throw new IllegalStateException("Unable to access H2MVStoreAPI. MVStore is not initialized.");
        return this.store;
    }

}
