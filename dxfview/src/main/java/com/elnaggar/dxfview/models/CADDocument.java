package com.elnaggar.dxfview.models;

import android.graphics.Color;
import android.graphics.Point;

import org.kabeja.dxf.DXFCircle;
import org.kabeja.dxf.DXFColor;
import org.kabeja.dxf.DXFConstants;
import org.kabeja.dxf.DXFDimension;
import org.kabeja.dxf.DXFDocument;
import org.kabeja.dxf.DXFHeader;
import org.kabeja.dxf.DXFLayer;
import org.kabeja.dxf.DXFLine;
import org.kabeja.dxf.DXFPolyline;
import org.kabeja.dxf.DXFText;
import org.kabeja.dxf.DXFVariable;
import org.kabeja.dxf.DXFVertex;
import org.kabeja.parser.DXFParser;
import org.kabeja.parser.ParseException;
import org.kabeja.parser.Parser;
import org.kabeja.parser.ParserBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CADDocument {

	private static final int BY_LAYER = 0;
	private Parser dxfParser;
	private List<Layer> shapesLayers;
	private DXFDocument dxfDocument;
	private DXFVariable dxfVariableMin;
	private DXFVariable dxfVariableMax;
	private Header docHeader = null;
	private Double yMinValue;
	private Double xMinValue;

	public CADDocument() {
		dxfParser = ParserBuilder.createDefaultParser();
		shapesLayers = new ArrayList<Layer>();
	}

	/**
	 * takes DXFLayer and get all dxf shapes and convert it to shapes com.elnaggar.dxfview.models and
	 * put it in Layer object
	 * 
	 * @param dxfLayer
	 * @return Layer
	 * 
	 */
	@SuppressWarnings("unused")
	private Layer getShapesFromDXFLayer(DXFLayer dxfLayer) {
		Layer layer = new Layer();
		if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_LINE)) {
			getLinesInDXFLayer(layer, dxfLayer);

		}
		return layer;
	}

	private int getColorFromDxfColor(int dxfColor) {
		String rgbString[] = DXFColor.getRGBString(dxfColor).split(",");
		int color = Color.argb(255, Integer.valueOf(rgbString[0]),
				Integer.valueOf(rgbString[1]), Integer.valueOf(rgbString[2]));
		return color;
	}

	@SuppressWarnings("unchecked")
	private void getLinesInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFLine> dxfLines = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_LINE);

		for (DXFLine dxfLine : dxfLines) {
			Line line = new Line();
			Point startPoint = new Point(
					(int) (dxfLine.getStartPoint().getX() - xMinValue),
					(int) (dxfLine.getStartPoint().getY() - yMinValue));
			line.setStartPoint(startPoint);
			Point endPoint = new Point(
					(int) (dxfLine.getEndPoint().getX() - xMinValue),
					(int) (dxfLine.getEndPoint().getY() - yMinValue));

			int color = 0;
			if (dxfLine.getColor() == BY_LAYER)
				color = getColorFromDxfColor(dxfLayer.getColor());
			else
				color = getColorFromDxfColor(dxfLine.getColor());

			line.setEndPoint(endPoint);
			line.setColor(color);
			line.setThickness((int) dxfLine.getThickness());
			layer.addShape(line);
		}
	}

	private void parseDXFDoc() {
		if (dxfDocument != null) {
			docHeader = getHeader();
			Variable extMinVariable = docHeader.getVariables().get("$EXTMIN");
			// Variable extMaxVariable =
			// docHeader.getVariables().get("$EXTMAX");
			yMinValue = extMinVariable.getValue().get("$YEXTMIN");
			xMinValue = extMinVariable.getValue().get("$XEXTMIN");
			Iterator<?> layerIterator = dxfDocument.getDXFLayerIterator();
			while (layerIterator.hasNext()) {
				DXFLayer dxfLayer = (DXFLayer) layerIterator.next();
				Layer layer = new Layer();

				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_LINE)) {
					getLinesInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_CIRCLE)) {
					getCirclesInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer
						.hasDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE)) {
					getLWPolylinesInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE)) {
					getPolylinesInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_TEXT)) {
					getTextsInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_ELLIPSE)) {
					// getTextsInDXFLayer(layer, dxfLayer);
				}
				if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_DIMENSION)) {
					getDimensionsInDXFLayer(layer, dxfLayer);
				}
				// if (dxfLayer.hasDXFEntities(DXFConstants.ENTITY_TYPE_ARC)) {
				// getDimensionsInDXFLayer(layer, dxfLayer);
				// }
				shapesLayers.add(layer);
			}
		}

	}

	@SuppressWarnings("unchecked")
	private void getDimensionsInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFDimension> dxfDimensions = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_DIMENSION);
		for (DXFDimension dxfDimension : dxfDimensions) {
			Dimension dimensions = new Dimension();

		}
	}

	@SuppressWarnings("unchecked")
	private void getTextsInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFText> dxfTexts = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_TEXT);
		for (DXFText dxfText : dxfTexts) {
			Text text = new Text();
			text.setText(dxfText.getText());

			Point alignPosition = new Point((int) dxfText.getInsertPoint()
					.getX(), (int) dxfText.getInsertPoint().getY());
			text.setAlignPosition(alignPosition);
			int color = getColorFromDxfColor(dxfText.getColor());

			int thickness = (int) dxfText.getThickness();
			text.setThickness(thickness);
			text.setColor(color);
			layer.addShape(text);
		}
	}

	@SuppressWarnings("unchecked")
	private void getLWPolylinesInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFPolyline> dxfPolylines = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_LWPOLYLINE);
		for (DXFPolyline dxfPolyline : dxfPolylines) {
			Polyline polyline = new Polyline();
			float[] points = new float[dxfPolyline.getVertexCount() * 2];
			Iterator vertecisIterator = dxfPolyline.getVertexIterator();
			int index = 0;
			while (vertecisIterator.hasNext()) {
				DXFVertex vertex = (DXFVertex) vertecisIterator.next();
				points[index * 2] = (float) (vertex.getX() - xMinValue);
				points[index * 2 + 1] = (float) (vertex.getY() - yMinValue);
				index++;
			}
			int color = 0;
			if (dxfPolyline.getColor() == BY_LAYER)
				color = getColorFromDxfColor(dxfLayer.getColor());
			else
				color = getColorFromDxfColor(dxfPolyline.getColor());
			polyline.setIsClosed(dxfPolyline.isClosed());
			int thickness = (int) dxfPolyline.getThickness();
			polyline.setThickness(thickness);
			polyline.setPoints(points);
			polyline.setColor(color);
			layer.addShape(polyline);
		}
	}

	@SuppressWarnings("unchecked")
	private void getPolylinesInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFPolyline> dxfPolylines = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_POLYLINE);
		for (DXFPolyline dxfPolyline : dxfPolylines) {
			Polyline polyline = new Polyline();
			float[] points = new float[dxfPolyline.getVertexCount() * 2];
			Iterator vertecisIterator = dxfPolyline.getVertexIterator();
			int index = 0;
			while (vertecisIterator.hasNext()) {
				DXFVertex vertex = (DXFVertex) vertecisIterator.next();
				points[index * 2] = (float) (vertex.getX() - xMinValue);
				points[index * 2 + 1] = (float) (vertex.getY() - yMinValue);
				index++;
			}
			int color = getColorFromDxfColor(dxfPolyline.getColor());

			int thickness = (int) dxfPolyline.getThickness();
			polyline.setThickness(thickness);
			polyline.setPoints(points);
			polyline.setColor(color);
			layer.addShape(polyline);
		}
	}

	@SuppressWarnings("unchecked")
	private void getCirclesInDXFLayer(Layer layer, DXFLayer dxfLayer) {
		List<DXFCircle> dxfCircles = dxfLayer
				.getDXFEntities(DXFConstants.ENTITY_TYPE_CIRCLE);
		for (DXFCircle dxfCircle : dxfCircles) {
			Circle circle = new Circle();
			org.kabeja.dxf.helpers.Point dxfCenterPoint = dxfCircle
					.getCenterPoint();
			double radius = dxfCircle.getRadius();

			int color = getColorFromDxfColor(dxfCircle.getColor());
			int thickness = (int) dxfCircle.getThickness();
			circle.setThickness(thickness);
			circle.setStrokColor(color);
			Point centerPoint = new Point(
					(int) (dxfCenterPoint.getX() - xMinValue),
					(int) (dxfCenterPoint.getY() - yMinValue));
			circle.setCenterPoint(centerPoint);
			circle.setRadius(radius);
			layer.addShape(circle);
		}

	}

	public Header getHeader() {
		if (dxfDocument == null)
			return null;
		DXFHeader dxfHeader = dxfDocument.getDXFHeader();
		dxfVariableMin = dxfHeader.getVariable("$EXTMIN");
		dxfVariableMax = dxfHeader.getVariable("$EXTMAX");
		Variable extMinVariable = new Variable();

		extMinVariable.setName("$EXTMIN");
		Map<String, Double> extMinVariableValue = new HashMap<String, Double>();
		extMinVariableValue
				.put("$XEXTMIN", dxfVariableMin.getDoubleValue("10"));
		extMinVariableValue
				.put("$YEXTMIN", dxfVariableMin.getDoubleValue("20"));
		extMinVariableValue
				.put("$ZEXTMIN", dxfVariableMin.getDoubleValue("30"));
		extMinVariable.setValue(extMinVariableValue);
		Variable extMaxVariable = new Variable();
		extMaxVariable.setName("$EXTMAX");
		Map<String, Double> extMaxVariableValue = new HashMap<String, Double>();
		extMaxVariableValue
				.put("$XEXTMAX", dxfVariableMax.getDoubleValue("10"));
		extMaxVariableValue
				.put("$YEXTMAX", dxfVariableMax.getDoubleValue("20"));
		extMaxVariableValue
				.put("$ZEXTMAX", dxfVariableMax.getDoubleValue("30"));
		extMaxVariable.setValue(extMaxVariable.getValue());
		Map<String, Variable> variables = new HashMap<String, Variable>();
		variables.put("$EXTMIN", extMinVariable);
		variables.put("$EXTMAX", extMaxVariable);
		Header header = new Header();
		header.setVariables(variables);
		return header;
	}

	public List<Layer> parseDXFDoc(final InputStream inputStream)
			throws ParseException {

		List<Layer> shapesLayers = null;

		dxfParser.parse(inputStream, DXFParser.DEFAULT_ENCODING);

		dxfDocument = dxfParser.getDocument();
		parseDXFDoc();
		shapesLayers = this.shapesLayers;

		return shapesLayers;
	}
}
