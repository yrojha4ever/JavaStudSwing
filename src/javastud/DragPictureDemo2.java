package javastud;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;

import javax.accessibility.Accessible;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;

//A version of DragPictureDemo that creates an
//Edit menu with cut/copy/paste actions.
//This demo adds a class called TransferActionDemo
//that transfers the cut/copy/paste menu action
//to the currently focused component.
public class DragPictureDemo2 extends JPanel {

  DTPicture pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9, pic10,
      pic11, pic12;

  static String mayaString = "Maya";

  static String anyaString = "Anya";

  static String laineString = "Laine";

  static String cosmoString = "Cosmo";

  static String adeleString = "Adele";

  static String alexiString = "Alexi";

  PictureTransferHandler picHandler;

  public DragPictureDemo2() {
    super(new BorderLayout());
    picHandler = new PictureTransferHandler();
    //Since we are using keyboard accelerators, we don't
    //need the component to install its own input map
    //bindings.
    DTPicture.setInstallInputMapBindings(false);

    JPanel mugshots = new JPanel(new GridLayout(4, 3));
    pic1 = new DTPicture(createImageIcon("images/" + mayaString + ".jpg",
        mayaString).getImage());
    pic1.setTransferHandler(picHandler);
    mugshots.add(pic1);
    pic2 = new DTPicture(createImageIcon("images/" + anyaString + ".jpg",
        anyaString).getImage());
    pic2.setTransferHandler(picHandler);
    mugshots.add(pic2);
    pic3 = new DTPicture(createImageIcon("images/" + laineString + ".jpg",
        laineString).getImage());
    pic3.setTransferHandler(picHandler);
    mugshots.add(pic3);
    pic4 = new DTPicture(createImageIcon("images/" + cosmoString + ".jpg",
        cosmoString).getImage());
    pic4.setTransferHandler(picHandler);
    mugshots.add(pic4);
    pic5 = new DTPicture(createImageIcon("images/" + adeleString + ".jpg",
        adeleString).getImage());
    pic5.setTransferHandler(picHandler);
    mugshots.add(pic5);
    pic6 = new DTPicture(createImageIcon("images/" + alexiString + ".jpg",
        alexiString).getImage());
    pic6.setTransferHandler(picHandler);
    mugshots.add(pic6);

    //These six components with no pictures provide handy
    //drop targets.
    pic7 = new DTPicture(null);
    pic7.setTransferHandler(picHandler);
    mugshots.add(pic7);
    pic8 = new DTPicture(null);
    pic8.setTransferHandler(picHandler);
    mugshots.add(pic8);
    pic9 = new DTPicture(null);
    pic9.setTransferHandler(picHandler);
    mugshots.add(pic9);
    pic10 = new DTPicture(null);
    pic10.setTransferHandler(picHandler);
    mugshots.add(pic10);
    pic11 = new DTPicture(null);
    pic11.setTransferHandler(picHandler);
    mugshots.add(pic11);
    pic12 = new DTPicture(null);
    pic12.setTransferHandler(picHandler);
    mugshots.add(pic12);

    setPreferredSize(new Dimension(450, 630));
    add(mugshots, BorderLayout.CENTER);
    setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
  }

  //Create an Edit menu to support cut/copy/paste.
  public JMenuBar createMenuBar() {
    JMenuItem menuItem = null;
    JMenuBar menuBar = new JMenuBar();
    JMenu mainMenu = new JMenu("Edit");
    mainMenu.setMnemonic(KeyEvent.VK_E);
    TransferActionListener actionListener = new TransferActionListener();

    menuItem = new JMenuItem("Cut");
    menuItem.setActionCommand((String) TransferHandler.getCutAction()
        .getValue(Action.NAME));
    menuItem.addActionListener(actionListener);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
        ActionEvent.CTRL_MASK));
    menuItem.setMnemonic(KeyEvent.VK_T);
    mainMenu.add(menuItem);
    menuItem = new JMenuItem("Copy");
    menuItem.setActionCommand((String) TransferHandler.getCopyAction()
        .getValue(Action.NAME));
    menuItem.addActionListener(actionListener);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
        ActionEvent.CTRL_MASK));
    menuItem.setMnemonic(KeyEvent.VK_C);
    mainMenu.add(menuItem);
    menuItem = new JMenuItem("Paste");
    menuItem.setActionCommand((String) TransferHandler.getPasteAction()
        .getValue(Action.NAME));
    menuItem.addActionListener(actionListener);
    menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
        ActionEvent.CTRL_MASK));
    menuItem.setMnemonic(KeyEvent.VK_P);
    mainMenu.add(menuItem);

    menuBar.add(mainMenu);
    return menuBar;
  }

  /** Returns an ImageIcon, or null if the path was invalid. */
  protected static ImageIcon createImageIcon(String path, String description) {
    java.net.URL imageURL = DragPictureDemo2.class.getResource(path);

    if (imageURL == null) {
      System.err.println("Resource not found: " + path);
      return null;
    } else {
      return new ImageIcon(imageURL, description);
    }
  }

  /**
   * Create the GUI and show it. For thread safety, this method should be
   * invoked from the event-dispatching thread.
   */
  private static void createAndShowGUI() {
    //Make sure we have nice window decorations.
    JFrame.setDefaultLookAndFeelDecorated(true);

    //Create and set up the window.
    JFrame frame = new JFrame("DragPictureDemo2");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    //Create and set up the menu bar and content pane.
    DragPictureDemo2 demo = new DragPictureDemo2();
    frame.setJMenuBar(demo.createMenuBar());
    demo.setOpaque(true); //content panes must be opaque
    frame.setContentPane(demo);

    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    //Schedule a job for the event-dispatching thread:
    //creating and showing this application's GUI.
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowGUI();
      }
    });
  }
}

/*
 * DTPicture.java is used by the 1.4 DragPictureDemo.java example.
 */

//A subclass of Picture that supports Data Transfer.

class DTPicture extends Picture implements MouseMotionListener {
  private MouseEvent firstMouseEvent = null;

  private static boolean installInputMapBindings = true;

  public DTPicture(Image image) {
    super(image);
    addMouseMotionListener(this);

    //Add the cut/copy/paste key bindings to the input map.
    //Note that this step is redundant if you are installing
    //menu accelerators that cause these actions to be invoked.
    //DragPictureDemo does not use menu accelerators and, since
    //the default value of installInputMapBindings is true,
    //the bindings are installed. DragPictureDemo2 does use
    //menu accelerators and so calls setInstallInputMapBindings
    //with a value of false. Your program would do one or the
    //other, but not both.
    if (installInputMapBindings) {
      InputMap imap = this.getInputMap();
      imap.put(KeyStroke.getKeyStroke("ctrl X"), TransferHandler
          .getCutAction().getValue(Action.NAME));
      imap.put(KeyStroke.getKeyStroke("ctrl C"), TransferHandler
          .getCopyAction().getValue(Action.NAME));
      imap.put(KeyStroke.getKeyStroke("ctrl V"), TransferHandler
          .getPasteAction().getValue(Action.NAME));
    }

    //Add the cut/copy/paste actions to the action map.
    //This step is necessary because the menu's action listener
    //looks for these actions to fire.
    ActionMap map = this.getActionMap();
    map.put(TransferHandler.getCutAction().getValue(Action.NAME),
        TransferHandler.getCutAction());
    map.put(TransferHandler.getCopyAction().getValue(Action.NAME),
        TransferHandler.getCopyAction());
    map.put(TransferHandler.getPasteAction().getValue(Action.NAME),
        TransferHandler.getPasteAction());
  }

  public void setImage(Image image) {
    this.image = image;
    this.repaint();
  }

  public void mousePressed(MouseEvent e) {
    //Don't bother to drag if there is no image.
    if (image == null)
      return;

    firstMouseEvent = e;
    e.consume();
  }

  public void mouseDragged(MouseEvent e) {
    //Don't bother to drag if the component displays no image.
    if (image == null)
      return;

    if (firstMouseEvent != null) {
      e.consume();

      //If they are holding down the control key, COPY rather than MOVE
      int ctrlMask = InputEvent.CTRL_DOWN_MASK;
      int action = ((e.getModifiersEx() & ctrlMask) == ctrlMask) ? TransferHandler.COPY
          : TransferHandler.MOVE;

      int dx = Math.abs(e.getX() - firstMouseEvent.getX());
      int dy = Math.abs(e.getY() - firstMouseEvent.getY());
      //Arbitrarily define a 5-pixel shift as the
      //official beginning of a drag.
      if (dx > 5 || dy > 5) {
        //This is a drag, not a click.
        JComponent c = (JComponent) e.getSource();
        TransferHandler handler = c.getTransferHandler();
        //Tell the transfer handler to initiate the drag.
        handler.exportAsDrag(c, firstMouseEvent, action);
        firstMouseEvent = null;
      }
    }
  }

  public void mouseReleased(MouseEvent e) {
    firstMouseEvent = null;
  }

  public void mouseMoved(MouseEvent e) {
  }

  //This method is necessary because DragPictureDemo and
  //DragPictureDemo2 both use this class and DragPictureDemo
  //needs to have the input map bindings installed for
  //cut/copy/paste. DragPictureDemo2 uses menu accelerators
  //and does not need to have the input map bindings installed.
  //Your program would use one approach or the other, but not
  //both. The default for installInputMapBindings is true.
  public static void setInstallInputMapBindings(boolean flag) {
    installInputMapBindings = flag;
  }

  public static boolean getInstallInputMapBindingds() { //for completeness
    return installInputMapBindings;
  }
}

/*
 * Picture.java is used by the 1.4 TrackFocusDemo.java and DragPictureDemo.java
 * examples.
 */

class Picture extends JComponent implements MouseListener, FocusListener,
    Accessible {
  Image image;

  public Picture(Image image) {

    this.image = image;
    setFocusable(true);
    addMouseListener(this);
    addFocusListener(this);
  }

  public void mouseClicked(MouseEvent e) {
    //Since the user clicked on us, let's get focus!
    requestFocusInWindow();
  }

  public void mouseEntered(MouseEvent e) {
  }

  public void mouseExited(MouseEvent e) {
  }

  public void mousePressed(MouseEvent e) {
  }

  public void mouseReleased(MouseEvent e) {
  }

  public void focusGained(FocusEvent e) {
    //Draw the component with a red border
    //indicating that it has focus.
    this.repaint();
  }

  public void focusLost(FocusEvent e) {
    //Draw the component with a black border
    //indicating that it doesn't have focus.
    this.repaint();
  }

  protected void paintComponent(Graphics graphics) {
    Graphics g = graphics.create();

    //Draw in our entire space, even if isOpaque is false.
    g.setColor(Color.WHITE);
    g.fillRect(0, 0, image == null ? 125 : image.getWidth(this),
        image == null ? 125 : image.getHeight(this));

    if (image != null) {
      //Draw image at its natural size of 125x125.
      g.drawImage(image, 0, 0, this);
    }

    //Add a border, red if picture currently has focus
    if (isFocusOwner()) {
      g.setColor(Color.RED);
    } else {
      g.setColor(Color.BLACK);
    }
    g.drawRect(0, 0, image == null ? 125 : image.getWidth(this),
        image == null ? 125 : image.getHeight(this));
    g.dispose();
  }
}

/*
 * PictureTransferHandler.java is used by the 1.4 DragPictureDemo.java example.
 */

class PictureTransferHandler extends TransferHandler {
  DataFlavor pictureFlavor = DataFlavor.imageFlavor;

  DTPicture sourcePic;

  boolean shouldRemove;

  public boolean importData(JComponent c, Transferable t) {
    Image image;
    if (canImport(c, t.getTransferDataFlavors())) {
      DTPicture pic = (DTPicture) c;
      //Don't drop on myself.
      if (sourcePic == pic) {
        shouldRemove = false;
        return true;
      }
      try {
        image = (Image) t.getTransferData(pictureFlavor);
        //Set the component to the new picture.
        pic.setImage(image);
        return true;
      } catch (UnsupportedFlavorException ufe) {
        System.out.println("importData: unsupported data flavor");
      } catch (IOException ioe) {
        System.out.println("importData: I/O exception");
      }
    }
    return false;
  }

  protected Transferable createTransferable(JComponent c) {
    sourcePic = (DTPicture) c;
    shouldRemove = true;
    return new PictureTransferable(sourcePic);
  }

  public int getSourceActions(JComponent c) {
    return COPY_OR_MOVE;
  }

  protected void exportDone(JComponent c, Transferable data, int action) {
    if (shouldRemove && (action == MOVE)) {
      sourcePic.setImage(null);
    }
    sourcePic = null;
  }

  public boolean canImport(JComponent c, DataFlavor[] flavors) {
    for (int i = 0; i < flavors.length; i++) {
      if (pictureFlavor.equals(flavors[i])) {
        return true;
      }
    }
    return false;
  }

  class PictureTransferable implements Transferable {
    private Image image;

    PictureTransferable(DTPicture pic) {
      image = pic.image;
    }

    public Object getTransferData(DataFlavor flavor)
        throws UnsupportedFlavorException {
      if (!isDataFlavorSupported(flavor)) {
        throw new UnsupportedFlavorException(flavor);
      }
      return image;
    }

    public DataFlavor[] getTransferDataFlavors() {
      return new DataFlavor[] { pictureFlavor };
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
      return pictureFlavor.equals(flavor);
    }
  }
}

/*
 * TransferActionListener.java is used by the 1.4 DragPictureDemo.java example.
 */

/*
 * A class that tracks the focused component. This is necessary to delegate the
 * menu cut/copy/paste commands to the right component. An instance of this
 * class is listening and when the user fires one of these commands, it calls
 * the appropriate action on the currently focused component.
 */

class TransferActionListener implements ActionListener, PropertyChangeListener {
  private JComponent focusOwner = null;

  public TransferActionListener() {
    KeyboardFocusManager manager = KeyboardFocusManager
        .getCurrentKeyboardFocusManager();
    manager.addPropertyChangeListener("permanentFocusOwner", this);
  }

  public void propertyChange(PropertyChangeEvent e) {
    Object o = e.getNewValue();
    if (o instanceof JComponent) {
      focusOwner = (JComponent) o;
    } else {
      focusOwner = null;
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (focusOwner == null)
      return;
    String action = (String) e.getActionCommand();
    Action a = focusOwner.getActionMap().get(action);
    if (a != null) {
      a.actionPerformed(new ActionEvent(focusOwner,
          ActionEvent.ACTION_PERFORMED, null));
    }
  }
}
