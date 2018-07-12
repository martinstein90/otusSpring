package com.martin.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("application")
public class ApplicationSettings {

    private boolean useEnglishLocal;

    public boolean isEnglishLocal() {
        return useEnglishLocal;
    }

    public void setEnglishLocal(boolean useEnglishLocal) {
        this.useEnglishLocal = useEnglishLocal;
    }
}
