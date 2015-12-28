package threads;

import android.os.AsyncTask;

import org.kabeja.dxf.DXFDocument;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

public class ParseDxfFileAsyncTask extends AsyncTask<Void, Void, DXFDocument> {

    private DXFDocument dxfDoc;
    private Parser dxfParser;

    public ParseDxfFileAsyncTask() {
        dxfParser = ParserBuilder.createDefaultParser();
    }

    @Override
    protected DXFDocument doInBackground(Void... args) {
        /*
		InputStream is = cadSurfaceView.getResources()
				.openRawResource(R.raw.line);
		try {
			dxfParser.parse(is, DXFParser.DEFAULT_ENCODING);
			dxfDoc = dxfParser.getDocument();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		*/
        return dxfDoc;
    }

    @Override
    protected void onPostExecute(DXFDocument dxfDoc) {
        super.onPostExecute(dxfDoc);
        //cadSurfaceView.displayDxfDocument(dxfDoc);
    }
}
