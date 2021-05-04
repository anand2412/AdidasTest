package com.adidas.adidasproduct;

import android.content.Context;
import androidx.test.core.app.ApplicationProvider;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.*;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "AndroidManifest.xml")
public abstract class BaseTest {
    protected Context context = ApplicationProvider.getApplicationContext();
}
