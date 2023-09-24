package cuteneko.catsplus;

import cuteneko.catsplus.platform.ICatsPlusPlatform;

public class CatsPlus {
    public static final String MODID = "catsplus";

    private static CatsPlus INSTANCE;

    private ICatsPlusPlatform platform;

    public CatsPlus() {
        INSTANCE = this;
    }

    public static CatsPlus getInstance() {
        return INSTANCE;
    }

    public void setPlatform(ICatsPlusPlatform platform) {
        this.platform = platform;
    }

    public ICatsPlusPlatform getPlatform() {
        return platform;
    }
}
