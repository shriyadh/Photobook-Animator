						Homework 8: Shapes Photo Album Part 1

======================================Brief Overview ===========================================================================

We are making a PhotoAlbum based on instructions passed in by the user. The file parser reads in the instructions from the text file
and uses an adapter class to make the model based off of it. The new model generated returns a list of Snapshot objects to the controller
and that is passed on to the View. The view then renders the album based on snapshot information. The view can be graphical or web based.

=================================================================================================================================



======== PhotoAlbum ========= Main class which would interact with the controller.

The entire model portion of the program is run by our singleton class PhotoAlbum. 
The type Photo album is the singelton class that depicts the canvas/ photo gallery of our
photos / shapes. It maintains the shape and has the major control over all the functions to be
implemented on shapes or on itself. This class demands total control of the model and hence is
designed in a way that the controller of the program would only be allowed to interact with
this class and none of the other classes.
 * The photo album allows the user to create shapes.
 * The photo album allows the user to move shapes.
 * The photo album allows the user to resize shapes.
 * The photo album allows the user to change color of shapes.
 * The photo album allows the user to snapshot the state of album and print it.
 * The photo album allows the user to reset the gallery.
 * It also tracks the history of commands in the program.

====== IPhotoAlbum =====

The interface Photo album contains the contract of methods and attributes that any photo album 
implementing this class should fulfill. The methods interact with all the other classes in the model implementation and provide the first
serve interface for users / controller/ view.

===== Snapshot =====

The Snapshot class creates "selfies" for the photoalbum. The Snapshot depicts the state of the
album at the time the selfie is taken. It creates a Unique ID and time stamp and maintains a
record till reset() is called on.
 


==== The IShape Interface ====

 The interface for the shape class defines the methods and attributes each object instantiated
 from the concrete class should have. IShape would provide the "Contract" for all the shapes
 that can be placed on the canvas.
 Each shape has a Unique name - which would act like a Unique ID to identify that shape.
 It has a type from Enum class Type. Right now the enum is made of but would not be limited to
 Oval and rectangle shapes.
 Each shape has a corner/centre depending on the shape type.
 Each shape has a color defined in terms of R,G,B.
 Each shape has dimensions accounting for their width/height, radii lengths, etc.
 Each shape can move itself to a new position on the cartesian plane.
 Each shape can change its own color based on R,G,B values.
 Each shape can resize itself by passing in new dimensions -> can change its width, height
 or both.
 Each shape can calculate its own area based on its formula and dimensions.

==== AbstractShape =====

 The type AbstractShape implements the IShape interface.
 It implements functionality that have been defined in the IShape interface common to all shapes.
 It takes in certain parameters in its constructor (acts as super constructor for all its
 concrete classes. Takes in shapeName(Unique), Type of shape, corner/centre of the shape,
 color of the shape.
 It implements basic getter/setter methods here which are common to all shapes and hence can
 be implemented in a more generic class like this.

===== Rectangle =====

 The Rectangle class provides attributes and methods for the shape rectangle.
 This class extends the AbstractShape and indirectly implements IShape interface.
 The rectangle object has a unique name, type.Rectangle from enum class, a centre point,
 a specific color and two dimensions width and height.
 

===== Oval =====

 The Oval class provides attributes and methods for the shape oval.
 This class extends the AbstractShape and indirectly implements IShape interface.
 The oval object has a unique name, type.Oval from enum class, a centre point, a specific color
 and two radii (X and Y).

===== Color ======
 
 The Color class is used to define the color attribute. It has three values (R, G, B).
 Red , Green, Blue values are used to represent the spectrum of colors on the scale.
 Takes in a String value denoting the name of the color.
 

====== Dimension ======
 
 The Dimension class describes the dimensions of an object. It has overloaded constructors that
 take one or two dimension measurements depending on the shape.
 Radius -> circle.
 RadiusX and RadiusY -> oval.
 Width and Height -> Rectangle / Square.

===== Point ======

 The Point class contains the x and y coordinates of the center / corner of the defined shapes.
 The point is class is used to represent any point on the cartesian system and shapes can be
 initialized / moved to a new location by defining the point.
 

==== Type =====

 The enum Type to represent any 2D shape. Can be extended to add on more shapes. Has Oval and Rectangle for now.




===================================================== Main =======================================================

The type PhotoAlbum main is the entry point for this entire program.
It parses the command-line arguments and does the required actions requested by the user. 
It makes the view and gets the instance of the model and passes it into the controller.
It then asks the controller to run which renders the view.


===================================================== VIEW ==========================================================




====== ViewFactory =======

 The type View factory generates the type of view object as specified by the user. The command line arguments 
 are parsed in the main and based on the type of view requested by the user, we get the respective view
 object.
 For now, it is limited to generating web or graphical views only but can be extended.

========= IVew =============

The IView interface contains defaulted methods for rendering different types of view. It throws an
UnsupportedOperationException if the type of method is not supported by the rendered view.


========== WebView ==========

The type Web view creates a web view for the user based on instructions in file. Creates a SVG file that
can be opened to view the actual web page. Uses SVG and HTML to implement the web page.

============ GraphicalView ===================

The type Graphical view renders a graphical view for the user based on instructions in the
file/readable. The graphical view uses JAVAs SWING to implement the Graphical User Interface
for the user. The entire view extends JFrame and adds Action Listeners to provide
interactive buttons to the user which allows the viewer to load a particular snapshot, scroll
back to the previous snapshot, move on tp the next snapshot, view the description of snapshots
or exit the program.


============= DrawPanel =======================

The type Draw panel is a JPanel that forms the middle area or the drawing panel for all the
shapes. It uses the paintComponent method to draw the shapes and repaint to actually implement.






======================================================= Controller =================================================





=============== IController ====================

The interface Controller is the contract for the controller to implement methods for proper
handling of instructions between model and view.
The type Controller class returns Controller object that controls the communication between
model and view. It calls the parser class to read the instructions from the file and with the
help of an adapter class 'ShapeBuilder' constructs a new model copy for the view. It then
gets a list of Snapshot objects and passes it into the view depending on the type of view to be
rendered. It controls all operations between view and model and does not allow direct access
to either.


================ Controller ====================

The type Controller class returns Controller object that controls the communication between
model and view. It calls the parser class to read the instructions from the file and with the
help of an adapter class 'ShapeBuilder' constructs a new model copy for the view. It then
gets a list of Snapshot objects and passes it into the view depending on the type of view to be
rendered. It controls all operations between view and model and does not allow direct access
to either.





======================================================== Utility ======================================================



================== FileParser ====================

The File parser class takes in any readable which contains instructions about the shapes to be
printed by the view. It uses an adapter class as the model builder for the instructions
and returns a new copy of it based on only the instructions in the file. The parser picks up on
keywords for parsing like 'shape', 'snapshot', etc.

=================== ShapeBuilder ==================

The interface ShapeBuilder is an adapter class interface that provides pluggability of
user-defined inputs. The parser class uses the concrete implementation of this class to read
instructions and simultaneously pass them to the adapter to add / resize / reposition /
recolor the shapes.