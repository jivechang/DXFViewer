package ui.activity;

import android.app.Activity;
import android.os.Bundle;

import com.elnaggar.autocadviewer.R;
import com.elnaggar.dxfview.views.DXFView;

import java.io.InputStream;

public class MainActivity extends Activity {

    private DXFView dxfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dxfView = (DXFView) findViewById(R.id.dxfView);
        InputStream is = getResources().openRawResource(R.raw.draft2);
        dxfView.displayDxfDocument(is);
    }

}
