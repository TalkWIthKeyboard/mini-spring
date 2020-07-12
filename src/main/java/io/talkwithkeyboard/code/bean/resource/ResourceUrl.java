package io.talkwithkeyboard.code.bean.resource;

import java.io.InputStream;
import java.net.URL;

public class ResourceUrl implements Resource {

    private final URL url;

    public ResourceUrl(URL url) {
        this.url = url;
    }

    public InputStream getInputStream() throws Exception {
        var connect = url.openConnection();
        connect.connect();
        return connect.getInputStream();
    }
}
