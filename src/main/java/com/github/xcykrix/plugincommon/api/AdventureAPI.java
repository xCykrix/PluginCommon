package com.github.xcykrix.plugincommon.api;

import com.github.xcykrix.plugincommon.PluginCommon;
import com.github.xcykrix.plugincommon.extendables.SimpleAPI;
import com.github.xcykrix.plugincommon.extendables.implement.Initialize;
import com.github.xcykrix.plugincommon.extendables.implement.Shutdown;
import net.kyori.adventure.platform.bukkit.BukkitAudiences;

public class AdventureAPI extends SimpleAPI implements Initialize, Shutdown {
    private BukkitAudiences adventure;

    /**
     * Creates an Access API for the AdventureAPI for Spigot.
     *
     * @param pluginCommon PluginCommon API.
     *
     *                     <br><br>
     *                     <a href="https://docs.advntr.dev/">Access the AdventureAPI Documentation</a>.
     */
    public AdventureAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
    }

    /**
     * Initialize the AdventureAPI
     */
    @Override
    public void initialize() {
        this.adventure = BukkitAudiences.create(this.pluginCommon);
    }

    /**
     * Shutdown the AdventureAPI
     */
    public void shutdown() {
        if (this.adventure == null)
            throw new NullPointerException("Unable to shutdown AdventureAPI. BukkitAudiences is not initialized.");
        this.adventure.close();
        this.adventure = null;
    }

    /**
     * Access the Adventure Audience API.
     *
     * @return {@link BukkitAudiences}
     */
    public BukkitAudiences getAudiences() {
        if (this.adventure == null)
            throw new IllegalStateException("Unable to access AdventureAPI. BukkitAudiences is not initialized.");
        return this.adventure;
    }
}
