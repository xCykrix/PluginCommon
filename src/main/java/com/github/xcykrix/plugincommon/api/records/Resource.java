package com.github.xcykrix.plugincommon.api.records;

import java.io.InputStream;

public record Resource(String id, String parentFolder, InputStream resource) {
}