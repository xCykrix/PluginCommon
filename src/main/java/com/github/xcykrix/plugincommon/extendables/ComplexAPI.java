package com.github.xcykrix.plugincommon.extendables;

import com.github.xcykrix.plugincommon.PluginCommon;

public abstract class ComplexAPI<T> extends Stateful {
    public ComplexAPI(PluginCommon pluginCommon) {
        super(pluginCommon);
    }

    public abstract void initialize(T configuration);
}
