package javastud;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.JCheckBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class EditorDropTarget2 implements DropTargetListener {
	public EditorDropTarget2(JEditorPane pane) {
		this.pane = pane;

		// Create the DropTarget and register
		// it with the JEditorPane.
		dropTarget = new DropTarget(pane, DnDConstants.ACTION_COPY_OR_MOVE, this, true, null);
	}

	// Implementation of the DropTargetListener interface
	public void dragEnter(DropTargetDragEvent dtde) {
		DnDUtils.debugPrintln("dragEnter, drop action = " + DnDUtils.showActions(dtde.getDropAction()));

		// Get the type of object being transferred and determine
		// whether it is appropriate.
		checkTransferType(dtde);

		// Accept or reject the drag.
		acceptOrRejectDrag(dtde);
	}

	public void dragExit(DropTargetEvent dte) {
		DnDUtils.debugPrintln("DropTarget dragExit");
	}

	public void dragOver(DropTargetDragEvent dtde) {
		DnDUtils.debugPrintln("DropTarget dragOver, drop action = " + DnDUtils.showActions(dtde.getDropAction()));

		// Accept or reject the drag
		acceptOrRejectDrag(dtde);
	}

	public void dropActionChanged(DropTargetDragEvent dtde) {
		DnDUtils.debugPrintln(
				"DropTarget dropActionChanged, drop action = " + DnDUtils.showActions(dtde.getDropAction()));

		// Accept or reject the drag
		acceptOrRejectDrag(dtde);
	}

	public void drop(DropTargetDropEvent dtde) {
		DnDUtils.debugPrintln("DropTarget drop, drop action = " + DnDUtils.showActions(dtde.getDropAction()));

		// Check the drop action
		if ((dtde.getDropAction() & DnDConstants.ACTION_COPY_OR_MOVE) != 0) {
			// Accept the drop and get the transfer data
			dtde.acceptDrop(dtde.getDropAction());
			Transferable transferable = dtde.getTransferable();

			try {
				boolean result = false;

				if (draggingFile) {
					result = dropFile(transferable);
				} else {
					result = dropContent(transferable, dtde);
				}

				dtde.dropComplete(result);
				DnDUtils.debugPrintln("Drop completed, success: " + result);
			} catch (Exception e) {
				DnDUtils.debugPrintln("Exception while handling drop " + e);
				dtde.dropComplete(false);
			}
		} else {
			DnDUtils.debugPrintln("Drop target rejected drop");
			dtde.rejectDrop();
		}
	}

	// Internal methods start here

	protected boolean acceptOrRejectDrag(DropTargetDragEvent dtde) {
		int dropAction = dtde.getDropAction();
		int sourceActions = dtde.getSourceActions();
		boolean acceptedDrag = false;

		DnDUtils.debugPrintln("\tSource actions are " + DnDUtils.showActions(sourceActions) + ", drop action is "
				+ DnDUtils.showActions(dropAction));

		// Reject if the object being transferred
		// or the operations available are not acceptable
		if (!acceptableType || (sourceActions & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
			DnDUtils.debugPrintln("Drop target rejecting drag");
			dtde.rejectDrag();
		} else if (!draggingFile && !pane.isEditable()) {
			// Can't drag text to a read-only JEditorPane
			DnDUtils.debugPrintln("Drop target rejecting drag");
			dtde.rejectDrag();
		} else if ((dropAction & DnDConstants.ACTION_COPY_OR_MOVE) == 0) {
			// Not offering copy or move - suggest a copy
			DnDUtils.debugPrintln("Drop target offering COPY");
			dtde.acceptDrag(DnDConstants.ACTION_COPY);
			acceptedDrag = true;
		} else {
			// Offering an acceptable operation: accept
			DnDUtils.debugPrintln("Drop target accepting drag");
			dtde.acceptDrag(dropAction);
			acceptedDrag = true;
		}

		return acceptedDrag;
	}

	protected void checkTransferType(DropTargetDragEvent dtde) {
		// Accept a list of files, or data content that
		// amounts to plain text or a Unicode text string
		acceptableType = false;
		draggingFile = false;

		if (DnDUtils.isDebugEnabled()) {
			DataFlavor[] flavors = dtde.getCurrentDataFlavors();
			for (int i = 0; i < flavors.length; i++) {
				DataFlavor flavor = flavors[i];
				DnDUtils.debugPrintln("Drop MIME type " + flavor.getMimeType() + " is available");
			}
		}

		if (dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			acceptableType = true;
			draggingFile = true;
		} else if (dtde.isDataFlavorSupported(DataFlavor.plainTextFlavor)
				|| dtde.isDataFlavorSupported(DataFlavor.stringFlavor)) {
			acceptableType = true;
		}
		DnDUtils.debugPrintln("File type acceptable - " + acceptableType);
	}

	// This method handles a drop for a list of files
	protected boolean dropFile(Transferable transferable)
			throws IOException, UnsupportedFlavorException, MalformedURLException {
		List fileList = (List) transferable.getTransferData(DataFlavor.javaFileListFlavor);
		File transferFile = (File) fileList.get(0);
		final URL transferURL = transferFile.toURL();
		DnDUtils.debugPrintln("File URL is " + transferURL);

		pane.setPage(transferURL);

		return true;
	}

	// This method handles a drop with data content
	protected boolean dropContent(Transferable transferable, DropTargetDropEvent dtde) {
		if (!pane.isEditable()) {
			// Can't drop content on a read-only text control
			return false;
		}

		try {
			// Check for a match with the current content type
			DataFlavor[] flavors = dtde.getCurrentDataFlavors();

			DataFlavor selectedFlavor = null;

			// Look for either plain text or a String.
			for (int i = 0; i < flavors.length; i++) {
				DataFlavor flavor = flavors[i];

				if (flavor.equals(DataFlavor.plainTextFlavor) || flavor.equals(DataFlavor.stringFlavor)) {
					selectedFlavor = flavor;
					break;
				}
			}

			if (selectedFlavor == null) {
				// No compatible flavor - should never happen
				return false;

			}

			DnDUtils.debugPrintln("Selected flavor is " + selectedFlavor.getHumanPresentableName());

			// Get the transferable and then obtain the data
			Object data = transferable.getTransferData(selectedFlavor);

			DnDUtils.debugPrintln("Transfer data type is " + data.getClass().getName());

			String insertData = null;
			if (data instanceof InputStream) {
				// Plain text flavor
				String charSet = selectedFlavor.getParameter("charset");
				InputStream is = (InputStream) data;
				byte[] bytes = new byte[is.available()];
				is.read(bytes);
				try {
					insertData = new String(bytes, charSet);
				} catch (UnsupportedEncodingException e) {
					// Use the platform default encoding
					insertData = new String(bytes);
				}
			} else if (data instanceof String) {
				// String flavor
				insertData = (String) data;
			}

			if (insertData != null) {
				int selectionStart = pane.getCaretPosition();
				pane.replaceSelection(insertData);
				pane.select(selectionStart, selectionStart + insertData.length());
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}

		final JFrame f = new JFrame("JEditor Pane Drop Target Example 2");

		final JEditorPane pane = new JEditorPane();

		// Add a drop target to the JEditorPane
		EditorDropTarget2 target = new EditorDropTarget2(pane);

		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				System.exit(0);
			}
		});

		JPanel panel = new JPanel();
		final JCheckBox editable = new JCheckBox("Editable");
		editable.setSelected(true);
		panel.add(editable);
		editable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				pane.setEditable(editable.isSelected());
			}
		});

		f.getContentPane().add(new JScrollPane(pane), BorderLayout.CENTER);
		f.getContentPane().add(panel, BorderLayout.SOUTH);
		f.setSize(500, 400);
		f.setVisible(true);
	}

	protected JEditorPane pane;

	protected DropTarget dropTarget;

	protected boolean acceptableType; // Indicates whether data is acceptable

	protected boolean draggingFile; // True if dragging an entire file
}

class DnDUtils {
	public static String showActions(int action) {
		String actions = "";
		if ((action & (DnDConstants.ACTION_LINK | DnDConstants.ACTION_COPY_OR_MOVE)) == 0) {
			return "None";
		}

		if ((action & DnDConstants.ACTION_COPY) != 0) {
			actions += "Copy ";
		}

		if ((action & DnDConstants.ACTION_MOVE) != 0) {
			actions += "Move ";
		}

		if ((action & DnDConstants.ACTION_LINK) != 0) {
			actions += "Link";
		}

		return actions;
	}

	public static boolean isDebugEnabled() {
		return debugEnabled;
	}

	public static void debugPrintln(String s) {
		if (debugEnabled) {
			System.out.println(s);
		}
	}

	private static boolean debugEnabled = (System.getProperty("DnDExamples.debug") != null);
}