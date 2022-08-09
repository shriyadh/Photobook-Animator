package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * The File parser class takes in any readable which contains instructions about the shapes to be
 * printed by the view. It uses an adapter class as the model builder for the instructions
 * and returns a new copy of it based on only the instructions in the file. The parser picks up on
 * keywords for parsing like 'shape', 'snapshot', etc.
 */
public class FileParser {

  private static Readable readable;
  private static ShapeBuilder shapeBuilder;


  /**
   * Parse file Readable and append to ShapeBuilder appendable.
   *
   * @param <T>          the type parameter
   * @param readable     the readable
   * @param shapeBuilder the shape builder
   * @return the model copy
   * @throws FileNotFoundException  the file not found exception
   * @throws IllegalStateException  the illegal state exception
   * @throws InputMismatchException the input mismatch exception
   */
  public static <T> T parseFile(Readable readable, ShapeBuilder<T> shapeBuilder) throws
          FileNotFoundException, IllegalStateException, InputMismatchException {
    Objects.requireNonNull(readable, "Must have non-null readable source");
    Objects.requireNonNull(shapeBuilder, "Must provide a non-null Appendable");

    readable = readable;
    shapeBuilder = shapeBuilder;

    Scanner sc = new Scanner(readable);
    try {

      sc.useDelimiter(Pattern.compile("(\\n+|#.*)+"));

      while (sc.hasNext()) {

        String[] lineSplit = null;
        String line = sc.next();
        line = line.strip();

        if (line.substring(0, 4).equalsIgnoreCase("snap")) {
          lineSplit = line.split(" ", 2);
        } else {
          lineSplit = line.split("\\s+");
        }
        lineSplit[0] = lineSplit[0].toLowerCase();

        switch (lineSplit[0]) {
          case "shape":
            readShape(line, shapeBuilder);
            break;
          case "snapshot":
            takeSnapshot(lineSplit, shapeBuilder);
            break;
          case "move":
            moveShape(line, shapeBuilder);
            break;
          case "resize":
            resizeShape(line, shapeBuilder);
            break;
          case "remove":
            removeShape(line, shapeBuilder);
            break;
          case "color":
            changeColor(line, shapeBuilder);
            break;
          default:
            throw new IllegalStateException("Unexpected keyword: " + line + sc.nextLine());
        }
      }
    } catch (IllegalStateException e) {
      throw new IllegalStateException("Unexpected keyword!");
    } catch (IOException e) {
      e.printStackTrace();
    }
    return shapeBuilder.build();
  }

  /**
   * Read shape and get information.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   * @throws IllegalStateException the illegal state exception
   * @throws IOException           the io exception
   */
  private static void readShape(String line, ShapeBuilder shapeBuilder)
          throws IllegalStateException, IOException {
    Scanner scan = new Scanner(line);
    scan.next();

    String name;
    String shapeType;
    int[] val = new int[7];

    if (scan.hasNext()) {
      name = scan.next();
     // System.out.println(name);
    } else {
      throw new IllegalStateException("Shape: Expected a shape name but no input available.");
    }
    if (scan.hasNext()) {
      shapeType = scan.next();
     // System.out.println(shapeType);
    } else {
      throw new IllegalStateException("Shape: Expected a shape type but no input available.");
    }

    String[] fieldNames = {"x_position", "y_position", "width", "height", "red", "green", "blue"};

    for (int i = 0; i < fieldNames.length; i++) {
      val[i] = getInt(scan, fieldNames[i]);
      //System.out.println(String.valueOf(val[i]) + fieldNames[i]);
    }

    shapeBuilder.addShape(name, shapeType, val[0], val[1], val[2], val[3], val[4], val[5], val[6]);

  }

  /**
   * Take snapshot.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   */
  private static void takeSnapshot(String[] line, ShapeBuilder shapeBuilder) {

    String description = "";

    try {

      description = line[1];
      //System.out.println(description);
    } catch (Exception e) {
      // do nothing
    }
    shapeBuilder.takeSnapShot(description);
  }

  /**
   * Move shape.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   * @throws IllegalStateException the illegal state exception
   */
  private static void moveShape(String line, ShapeBuilder shapeBuilder)
          throws IllegalStateException {
    Scanner scan = new Scanner(line);
    scan.next();

    String shapeName = "";
    int[] positions = new int[2];
    String[] fieldNames = {"x_position", "y_position"};

    if (scan.hasNext()) {
      shapeName = scan.next();
     // System.out.println(shapeName);
    } else {
      throw new IllegalStateException("Shape: Expected a shape name but no input available.");
    }

    for (int i = 0; i < fieldNames.length; i++) {
      positions[i] = getInt(scan, fieldNames[i]);
     // System.out.println(String.valueOf(positions[i]) + fieldNames[i]);
    }

    shapeBuilder.moveShape(shapeName, positions[0], positions[1]);
  }

  /**
   * Resize shape.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   * @throws IllegalStateException the illegal state exception
   */
  private static void resizeShape(String line, ShapeBuilder shapeBuilder)
          throws IllegalStateException {
    Scanner scan = new Scanner(line);
    scan.next();

    String shapeName = "";
    int[] positions = new int[2];
    String[] fieldNames = {"width", "height"};

    if (scan.hasNext()) {
      shapeName = scan.next();
      System.out.println(shapeName);
    } else {
      throw new IllegalStateException("Shape: Expected a shape name but no input available.");
    }

    for (int i = 0; i < fieldNames.length; i++) {
      positions[i] = getInt(scan, fieldNames[i]);
      //System.out.println(positions[i] + fieldNames[i]);
    }

    shapeBuilder.resizeShape(shapeName, positions[0], positions[1]);
  }

  /**
   * Change color.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   * @throws IllegalStateException the illegal state exception
   */
  private static void changeColor(String line, ShapeBuilder shapeBuilder)
          throws IllegalStateException {
    Scanner scan = new Scanner(line);
    scan.next();

    String shapeName = "";
    int[] positions = new int[3];
    String[] fieldNames = {"red", "blue", "green"};

    if (scan.hasNext()) {
      shapeName = scan.next();
      //System.out.println(shapeName);
    } else {
      throw new IllegalStateException("Shape: Expected a shape name but no input available.");
    }

    for (int i = 0; i < fieldNames.length; i++) {
      positions[i] = getInt(scan, fieldNames[i]);
     // System.out.println(positions[i] + fieldNames[i]);
    }

    shapeBuilder.changeColorShape(shapeName, positions[0], positions[1], positions[2]);

  }

  /**
   * Remove shape.
   *
   * @param line         the line
   * @param shapeBuilder the shape builder
   * @throws IllegalStateException the illegal state exception
   */
  private static void removeShape(String line, ShapeBuilder shapeBuilder) throws IllegalStateException {

    Scanner scan = new Scanner(line);
    scan.next();

    String shapeName = "";

    if (scan.hasNext()) {
      shapeName = scan.next();
      //System.out.println(shapeName);
    } else {
      throw new IllegalStateException("Shape: Expected a shape name but no input available.");
    }

    shapeBuilder.removeShape(shapeName);
  }

  /*
  Gets the integer values from the readable.
   */
  private static int getInt(Scanner s, String fieldName) {
    if (s.hasNextInt()) {
      return s.nextInt();
    } else if (s.hasNext()) {
      throw new IllegalStateException(
              String.format("Shape: Expected integer for %s, got: %s", fieldName, s.next()));
    } else {
      throw new IllegalStateException(
              String.format("Shape: expected integer for %s, but no more input available",
                      fieldName));
    }
  }

}



