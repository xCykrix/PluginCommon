package com.github.xcykrix.plugincommon.api;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.github.xcykrix.plugincommon.PluginCommon;
import com.github.xcykrix.plugincommon.extendables.SimpleAPI;
import com.github.xcykrix.plugincommon.extendables.implement.Initialize;

public class ProtocolLibAPI extends SimpleAPI implements Initialize {
    private ProtocolManager protocolManager;

    /**
     * Creates an Access API for the ProtocolLibAPI.
     *
     * @param pluginCommon PluginCommon API.
     *
     *                     <br><br>
     *                     <a href="https://www.spigotmc.org/resources/protocollib.1997/">Access the ProtocolLib Documentation</a>.
     */
    public ProtocolLibAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
    }

    /**
     * Initialize the ProtocolLibAPI
     */
    @Override
    public void initialize() {
        this.protocolManager = ProtocolLibrary.getProtocolManager();
    }

    /**
     * Access the ProtocolLibAPI ProtocolManager API.
     *
     * @return {@link ProtocolManager}
     */
    public ProtocolManager getProtocolManager() {
        if (this.protocolManager == null)
            throw new IllegalStateException("Unable to access ProtocolLibAPI. ProtocolManager is not initialized.");
        return this.protocolManager;
    }
}
