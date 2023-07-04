package com.github.xcykrix.plugincommon.api;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;
import com.github.xcykrix.plugincommon.PluginCommon;
import com.github.xcykrix.plugincommon.extendables.SimpleAPI;
import com.github.xcykrix.plugincommon.extendables.implement.Initialize;

public class CommandAPI extends SimpleAPI {
    private final PaperCommandManager paperCommandManager;

    /**
     * Creates an Access API for the Annotation Command Framework.
     *
     * @param pluginCommon PluginCommon API.
     *
     *                     <br><br>
     *                     <a href="https://github.com/aikar/commands">Access the Annotation Command Framework Documentation</a>.
     */
    public CommandAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
        this.paperCommandManager = new PaperCommandManager(this.pluginCommon);
        this.paperCommandManager.enableUnstableAPI("help");
        this.paperCommandManager.enableUnstableAPI("brigadier");
    }

    /**
     * Register a BaseCommand to the ACF Command API.
     *
     * @param baseCommand The BaseCommand.
     * @return {@link CommandAPI}
     */
    public CommandAPI register(BaseCommand baseCommand) {
        this.paperCommandManager.registerCommand(baseCommand);
        if (baseCommand instanceof Initialize) {
            ((Initialize) baseCommand).initialize();
        }
        return this;
    }
}
