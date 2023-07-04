package com.github.xcykrix.plugincommon;

import com.comphenix.protocol.utility.MinecraftVersion;
import com.github.xcykrix.plugincommon.api.*;
import com.github.xcykrix.plugincommon.extendables.implement.Shutdown;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class PluginCommon extends JavaPlugin implements Shutdown {
    // Public APIs
    public AdventureAPI adventureAPI;
    public H2MVStoreAPI h2MVStoreAPI;
    public ConfigurationAPI configurationAPI;
    public CommandAPI commandAPI;
    public ProtocolLibAPI protocolLibAPI;

    // Private Access
    private MinecraftVersion earliestSupportedVersion = MinecraftVersion.CAVES_CLIFFS_2;

    /**
     * Initialize and Enable Plugin Internally.
     */
    @Override
    public void onEnable() {
        // Initialize Adventure API (Simple)
        this.adventureAPI = new AdventureAPI(this);
        this.adventureAPI.initialize();

        // Initialize H2MVStore API (Simple)
        this.h2MVStoreAPI = new H2MVStoreAPI(this);
        this.h2MVStoreAPI.initialize();

        // Initialize Configuration API (Simple)
        this.configurationAPI = new ConfigurationAPI(this);

        // Initialize Command API (Simple)
        this.commandAPI = new CommandAPI(this);

        // Initialize ProtocolLib API (Simple)
        this.protocolLibAPI = new ProtocolLibAPI(this);
        this.protocolLibAPI.initialize();

        // Verify Supported Version (PluginCommon is 1.18+)
        if (!this.protocolLibAPI.getProtocolManager().getMinecraftVersion().isAtLeast(earliestSupportedVersion)) {
            this.getLogger().severe("Unsupported Version. This plugin is not compatible prior to " + earliestSupportedVersion.getVersion());
            this.getPluginLoader().disablePlugin(this);
            return;
        }

        // Call Child Initialize
        this.initialize();
    }

    /**
     * Shutdown and Disable Plugin Internally.
     */
    @Override
    public void onDisable() {
        // Call Child Shutdown
        this.shutdown();

        // Shutdown the Database API
        this.h2MVStoreAPI.shutdown();

        // Shutdown Adventure API
        this.adventureAPI.shutdown();
    }

    /**
     * Update the earliest supported version for this plugin. Defaults to Caves and Cliffs Part 2 (1.18).
     *
     * @param earliestSupportedVersion The earliest MinecraftVersion that is supported by the plugin.
     */
    public void setEarliestSupportedVersion(MinecraftVersion earliestSupportedVersion) {
        this.earliestSupportedVersion = earliestSupportedVersion;
    }

    /**
     * Called at the end of onEnable(); Required;
     */
    protected abstract void initialize();
}
