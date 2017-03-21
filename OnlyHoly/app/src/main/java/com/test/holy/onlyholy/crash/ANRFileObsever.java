package com.test.holy.onlyholy.crash;

import android.os.FileObserver;

/**
 * Created by houlin.jiang on 2017/3/17.
 */

public class ANRFileObsever extends FileObserver{
    public ANRFileObsever(String path) {
        super(path);
    }

    public ANRFileObsever(String path, int mask) {
        super(path, mask);
    }

    @Override
    public void onEvent(int event, String path) {

    }
}
